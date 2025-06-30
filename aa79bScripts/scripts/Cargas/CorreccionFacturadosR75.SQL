DECLARE

ANYO_AUX NUMBER(4):= '&1';
NUM_AUX NUMBER(6):= '&1';

error_code NUMBER;
err_msg VARCHAR2(4000);

CURSOR C_EXP_R75 is(
SELECT t30_anyo                                                                                      AS ANYO_051,
  t30_N_EXP                                                                                          AS NUM_EXP_051,
  DECODE(T30_COD_TIPO_EXP,'2','R','3','T','4','I') AS ID_TIPO_EXPEDIENTE_051,
  DECODE(t30_VIA_WEB,'1','S','0','O','3','W')                AS ORIGEN_051,
  T30_FEC_SOLI                                                                                       AS FECHA_ALTA_051,
  replace(replace(SUBSTR(T30_DESC,1,149),chr(10),''),chr(13),'')                                     AS TITULO_051,
  T30_DESC AS DESC_R75_051,
  T30_PPTO AS IND_PRESUPUESTO,
  T30_FACTURABLE AS IND_FACTURABLE,
  T30_OBSERV_IZO AS OBSERVACIONES,
  T30_FEC_FIN AS FECHA_ENTREGA,
  T30_COD_APLIC_ORIG as APLIC_ORIGEN, 
  T30_NUM_FAC as NUM_FACTURA,
  T30_ANYO_FAC as ANYO_FACTURA
FROM r75b.r7530t00
JOIN r75b.r7521t00 ON
t30_cod_solicitante=t21_cod_solicitante and T30_COD_CONT_SOLI=T21_COD_CONT_SOLI
JOIN r75b.r7519t00 ON 
t21_cod_solicitante=t19_cod_solicitante
LEFT JOIN r75b.r7532t00 ON 
t30_anyo = t32_anyo and t30_N_EXP=t32_N_EXP AND T30_FASE=T32_FASE
LEFT JOIN (select t39_anyo, t39_N_EXP, T39_TOT_PAL, T39_COD_TIPO_DOCUMENTO from r75b.r7539t00 where t39_anyo||'@'||t39_N_EXP||'@'||t39_FASE  in (select  t39_anyo||'@'||t39_N_EXP||'@'||max(t39_FASE) from r75b.r7539t00 group by T39_anyo,t39_N_EXP)) t39 ON
t30_anyo = t39.t39_anyo and t30_N_EXP=t39.t39_N_EXP
WHERE 
t30_fase = 5
AND T30_NUM_FAC is not null
/**
 * Modificar a partir de aqui, para obtener los expedientes deseados
 */
AND ((T30_COD_TIPO_EXP =3 AND T30_ANYO>=2012 AND T30_ANYO <=2018 AND T19_COD_TIPO_SOLICITANTE not in (8, 9)
AND (
	(T30_COD_SOLICITANTE = 1012 AND t39.T39_TOT_PAL >=1000)
	OR (T30_COD_SOLICITANTE = 533 AND T32_BOPV = 0) 
	OR (T30_COD_SOLICITANTE = 3 AND T21_DPTO <> 'Hautaketa' AND t39.T39_COD_TIPO_DOCUMENTO <> 21)
	OR (T30_COD_SOLICITANTE not in (3,533,1012)  AND ( t39.T39_COD_TIPO_DOCUMENTO IN (1,54,44)   OR (t39.T39_COD_TIPO_DOCUMENTO not in (1, 54, 44, 38, 59)  AND t39.T39_TOT_PAL >=1000)))
)
)
OR (T30_COD_TIPO_EXP =4 AND T30_ANYO =2018)
OR (T30_ANYO =2019))
/**
* Para filtrar por un expediente concreto con variables pasadas
*/
AND 
 1 = (
 CASE WHEN ANYO_AUX <> '0' AND T30_ANYO = ANYO_AUX THEN 1  
 ELSE CASE WHEN ANYO_AUX = '0' THEN 1 
 ELSE 0 END END
 )
AND 
1 = (
 CASE WHEN NUM_AUX <> '0' AND t30_N_EXP = NUM_AUX THEN 1  
 ELSE CASE WHEN NUM_AUX = '0' THEN 1 
 ELSE 0 END END
 )
)ORDER BY 1,2,3 DESC;

 TYPE r_reg_gestor
IS
  RECORD
  (
   	ANYO_054  NUMBER(4),
    NUM_EXP_054  NUMBER(6),
	DNI_SOLICITANTE_054 VARCHAR2(10),
	PRE_DNI_SOLICITANTE_054 VARCHAR2(1),
	SUF_DNI_SOLICITANTE_054 VARCHAR2(1),
    TIPO_ENTIDAD_054 VARCHAR2(1),
    ID_ENTIDAD_054 NUMBER(8),
    IND_VIP_054 VARCHAR2(1),
    DNI_REPRESENTANTE_054 VARCHAR2(10),
    NOMBRE_077	VARCHAR2(20),	
	APEL1_077	VARCHAR2(40),	
	APEL2_077	VARCHAR2(40)
  );
  GESTOR r_reg_gestor;
  
  type dyncursor is ref cursor;
  
  TYPE r_reg_contacto
IS
  RECORD
  (
   	ANYO_058  NUMBER(4),
    NUM_EXP_058  NUMBER(6),
    DNI_CONTACTO_058 VARCHAR2(10),
    PRE_DNI_CONTACTO_058 VARCHAR2(1),
    SUF_DNI_CONTACTO_058 VARCHAR2(1),
    TIPO_ENTIDAD_ASOC_058 VARCHAR2(1),
    ID_ENTIDAD_ASOC_058 NUMBER(8),
    NOMBRE_077	VARCHAR2(20),	
	APEL1_077	VARCHAR2(40),	
	APEL2_077	VARCHAR2(40)
  );
  CONTACTO r_reg_contacto;
  
NUM_EXP_ACTUAL NUMBER(6);
ANYO_ACTUAL NUMBER(4);
CONTADOR NUMBER(1):= 0;
ID_CONT_FACT NUMBER(10);

BEGIN
	FOR R_EXP_R75 IN C_EXP_R75
    LOOP
    	BEGIN
      	NUM_EXP_ACTUAL := R_EXP_R75.NUM_EXP_051;
     	ANYO_ACTUAL:= R_EXP_R75.ANYO_051;
    	
		-- RECUPERAMOS EL GESTOR
		select t30_anyo as anyo_054,t30_n_exp as NUM_EXP_054, 
		decode(t21_dni, null,'00000000',trim(translate(t21_dni,translate(t21_dni, '1234567890', ' '),' '))) as DNI_SOLICITANTE_054,
		trim(translate(substr(t21_dni,0,1), '1234567890', ' ')) as PRE_DNI_SOLICITANTE_054, 
		decode(t21_dni, null, 'T',trim(translate(substr(t21_dni,length(t21_dni),1), '1234567890', ' ')))  as SUF_DNI_SOLICITANTE_054, 
   	 	nvl(TIPO_X54,'B') as TIPO_ENTIDAD_054,
   	 	nvl(ID_X54,570) as ID_ENTIDAD_054,
    	DECODE(t21_vips,'',0,t21_vips) as IND_VIP_054,null as DNI_REPRESENTANTE_054, T21_NOMBRE, T21_APEL1, T21_APEL2 into GESTOR from r75b.r7530t00,r75b.r7521t00,r75b.r7519t00, CODR75X54J  
    	where  t30_cod_solicitante=t21_cod_solicitante and  t21_cod_solicitante=t19_cod_solicitante  
		and T30_COD_CONT_SOLI=T21_COD_CONT_SOLI and t30_fase=5 and t30_anyo = R_EXP_R75.ANYO_051 AND t30_n_exp =R_EXP_R75.NUM_EXP_051 
		and t30_cod_solicitante = ID_R75 (+);
		
		-- Recuperamos el contacto de facturación
        select t30_anyo as anyo_058,t30_n_exp as NUM_EXP_058, 
        decode(t21_dni, null,null,trim(translate(t21_dni,translate(t21_dni, '1234567890', ' '),' '))) as DNI_CONTACTO_058,
        trim(translate(substr(t21_dni,0,1), '1234567890', ' ')) as PRE_DNI_CONTACTO_058, 
        decode(t21_dni, null, null,trim(translate(substr(t21_dni,length(t21_dni),1), '1234567890', ' ')))  as SUF_DNI_CONTACTO_058, 
        nvl(TIPO_X54,'B') as TIPO_ENTIDAD_ASOC_058,
        nvl(ID_X54,570) as ID_ENTIDAD_ASOC_058,
        T21_NOMBRE, T21_APEL1, T21_APEL2 into CONTACTO from r75b.r7530t00,r75b.r7524t00, r75b.r7521t00,r75b.r7519t00, CODR75X54J  
        where  
        T30_NUM_FAC = T24_NUM_FACTURA AND  T30_ANYO_FAC = T24_ANYO
        AND T24_COD_SOLICITANTE =t21_cod_solicitante (+)
        and T24_COD_SOLICITANTE =t19_cod_solicitante  
        and T24_COD_CONT_SOLI=T21_COD_CONT_SOLI (+) and t30_fase=5 and t30_anyo = R_EXP_R75.ANYO_051 AND t30_n_exp =R_EXP_R75.NUM_EXP_051
        and T24_COD_SOLICITANTE = ID_R75 (+);

		--ACTUALIZAMOS EL CONTACTO
		SELECT count(1) into CONTADOR FROM AA79B58T00 WHERE ANYO_058 = R_EXP_R75.ANYO_051 AND NUM_EXP_058= R_EXP_R75.NUM_EXP_051;
        
	    IF (CONTACTO.DNI_CONTACTO_058 is null) then  
	    	if (contador > 0) THEN
	    		UPDATE AA79B58T00 set TIPO_ENTIDAD_ASOC_058 = GESTOR.TIPO_ENTIDAD_054, ID_ENTIDAD_ASOC_058 = GESTOR.ID_ENTIDAD_054 , DNI_CONTACTO_058 = GESTOR.DNI_SOLICITANTE_054   
	    		WHERE ANYO_058 = R_EXP_R75.ANYO_051 AND NUM_EXP_058= R_EXP_R75.NUM_EXP_051;
	    	ELSE
	    		Select AA79B58Q00.nextval into ID_CONT_FACT from dual;
				INSERT INTO AA79B58T00 (ID_058, ANYO_058, NUM_EXP_058, TIPO_ENTIDAD_ASOC_058, ID_ENTIDAD_ASOC_058, DNI_CONTACTO_058, POR_FACTURA_058)
				values (ID_CONT_FACT, R_EXP_R75.ANYO_051, R_EXP_R75.NUM_EXP_051, GESTOR.TIPO_ENTIDAD_054, GESTOR.ID_ENTIDAD_054, GESTOR.DNI_SOLICITANTE_054, 100);
	    	END IF;
	    ELSE
	    	if (contador > 0) THEN
		    	UPDATE AA79B58T00 set TIPO_ENTIDAD_ASOC_058 = CONTACTO.TIPO_ENTIDAD_ASOC_058, ID_ENTIDAD_ASOC_058 = CONTACTO.ID_ENTIDAD_ASOC_058 , DNI_CONTACTO_058 = CONTACTO.DNI_CONTACTO_058   
		    	WHERE ANYO_058 = R_EXP_R75.ANYO_051 AND NUM_EXP_058= R_EXP_R75.NUM_EXP_051;
	    	ELSE
	    		Select AA79B58Q00.nextval into ID_CONT_FACT from dual;
				INSERT INTO AA79B58T00 (ID_058, ANYO_058, NUM_EXP_058, TIPO_ENTIDAD_ASOC_058, ID_ENTIDAD_ASOC_058, DNI_CONTACTO_058, POR_FACTURA_058)
				values (ID_CONT_FACT, R_EXP_R75.ANYO_051, R_EXP_R75.NUM_EXP_051, CONTACTO.TIPO_ENTIDAD_ASOC_058, CONTACTO.ID_ENTIDAD_ASOC_058, CONTACTO.DNI_CONTACTO_058, 100);
	    	END IF;
	    END IF;
		    
		DELETE ERRORES_MIG WHERE ANYO_EXP = ANYO_ACTUAL AND NUM_EXP = NUM_EXP_ACTUAL;
		
		COMMIT;
		EXCEPTION  -- exception handlers begin
      	WHEN OTHERS THEN  -- handles all other errors
      	ROLLBACK;
      	error_code := SQLCODE;
      	err_msg := SUBSTR(SQLERRM, 1, 4000);

      	select count(1) into contador from ERRORES_MIG WHERE ANYO_EXP = ANYO_ACTUAL AND NUM_EXP = NUM_EXP_ACTUAL;
      	if (contador > 0) THEN
      		update ERRORES_MIG set ERROR_DET = DBMS_UTILITY.format_error_backtrace || '->' || err_msg, FECHA = sysdate WHERE ANYO_EXP = ANYO_ACTUAL AND NUM_EXP = NUM_EXP_ACTUAL;
      	ELSE
      		insert into ERRORES_MIG (ANYO_EXP, NUM_EXP, ERROR_DET, FECHA) values (ANYO_ACTUAL, NUM_EXP_ACTUAL, DBMS_UTILITY.format_error_backtrace || '->' || err_msg, sysdate);
      	END IF;
      	COMMIT;
      	
      END;
		
    END LOOP;
    
   EXCEPTION  -- exception handlers begin
      WHEN OTHERS THEN  -- handles all other errors
       ROLLBACK;
           
END;
/

