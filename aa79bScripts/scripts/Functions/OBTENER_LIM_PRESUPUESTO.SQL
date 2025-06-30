CREATE OR REPLACE
  FUNCTION OBTENER_LIM_PRESUPUESTO(
      ANYO_EXP IN NUMBER, NUM_EXP IN NUMBER)
    RETURN LIM_PRESUPUESTO_TABLE
  IS
  
  CONTADOR            NUMBER(2)  := 0;
  HORAS_ACEPTACION NUMBER(3);
  l_limite LIM_PRESUPUESTO_TABLE := LIM_PRESUPUESTO_TABLE();
  
  HORARIO_VERANO AA79B39S01%ROWTYPE;
  HORARIO_INVIERNO AA79B39S01%ROWTYPE;
  HORARIO AA79B39S01%ROWTYPE;
  
  DIA_SEL DATE;
  DIA_PROC DATE;
  ID_HORARIO      NUMBER(2)   :=1;
  HORAS_ACUM      NUMBER(12,2):=0;

  DIA_SEMANA_SEL NUMBER(1);
  
  FECHA_PROPUESTA DATE;
  FECHA_LIMITE DATE;
  
  TIPO_EXPEDIENTE VARCHAR2(1);
  TIPO_EXP_TRADUCCION VARCHAR2(1):= 'T';
  TIPO_EXP_INTERPRETACION VARCHAR2(1):= 'I';
  
  TIPO_TAREA_INTERPRETACION NUMBER(2):= 8;
  
    -- obtenemos los dias entre 2 fechas y indicando si son en verano o no
  BEGIN
	  -- OBTENEMOS EL PLAZO PARA LA APROBACION DEL PRESUPUESTO DEPENDIENDO DE SI ES UN GESTOR VIP o NO.
	  SELECT HORAS_ACEPT_PRESUPUESTO_001 into HORAS_ACEPTACION from AA79B01T00 where ID_001 = 0
	  AND CASE WHEN (SELECT IND_VIP_054 FROM AA79B54T00 WHERE ANYO_054 = ANYO_EXP AND NUM_EXP_054 = NUM_EXP)='S' THEN
	    'V'
	    ELSE
	    'N'
	   END = NIVEL_USUARIO_001;
	  
	  
	  -- OBTENEMOS EL TIPO DEL EXPEDIENTE
	  SELECT ID_TIPO_EXPEDIENTE_051 INTO TIPO_EXPEDIENTE FROM AA79B51T00 WHERE ANYO_051 = ANYO_EXP AND NUM_EXP_051 = NUM_EXP;
	  
	  -- OBTENEMOS LOS HORARIOS DE VERANO Y DE INVIERNO
	  SELECT *
      INTO HORARIO_VERANO
      FROM AA79B39S01
      WHERE ID_HORARIO_039=ID_HORARIO
      AND IND_VERANO_039  = 'V';
      SELECT *
      INTO HORARIO_INVIERNO
      FROM AA79B21S01
      WHERE ID_HORARIO_021  =ID_HORARIO
      AND IND_VERANO_021    = 'I';
	  
      DIA_PROC         := SYSDATE;
      WHILE (HORAS_ACEPTACION > 0.0)
      LOOP
      	DIA_SEL        := to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD'),'YYYY/MM/DD');
        DIA_SEMANA_SEL := MOD(TO_CHAR(DIA_PROC, 'J'), 7)+1;
      	--  COMPROBAMOS QUE NO SEA FESTIVO
        SELECT COUNT(1)
            INTO CONTADOR
            FROM AA79B23S01
            WHERE DIA_CALENDARIO_023 = DIA_SEL;
        IF (CONTADOR = 0) THEN
              -- COMPROBAMOS SI ES UN DIA DE VERANO O DE INVIERNO.
              SELECT COUNT(1)
              INTO CONTADOR
              FROM AA79B22S01
              WHERE ANYO_CALENDARIO_022 = TO_CHAR(DIA_SEL,'YYYY')
              AND VERANO_DESDE_022      < =DIA_SEL
              AND VERANO_HASTA_022      >= DIA_SEL;
              IF (CONTADOR              = 1) THEN
                -- ES VERANO
                HORARIO:=HORARIO_VERANO;
              ELSE
                HORARIO:=HORARIO_INVIERNO;
              END IF;
              -- SEGUIMOS AQUI EL RECORRIDO DE LOS DIAS.
              IF (DIA_SEMANA_SEL = 5) THEN
	                -- ES VIERNES
	              	IF (DIA_PROC    <= to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                    	HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);	
	              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
	              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
	              			-- COMPROBAMOS CON LAS DE LA TARDE
	              			HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);	
		              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
		              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
		              		ELSE
		              			FECHA_PROPUESTA:= to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi')+ (HORAS_ACEPTACION/24);
		              			HORAS_ACEPTACION:=0.0;
		              		END IF;
	              		ELSE
	              			FECHA_PROPUESTA:= to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')+ (HORAS_ACEPTACION/24);
	              			HORAS_ACEPTACION:=0.0;
	              		END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                 	HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);	
	              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
	              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
	              			-- COMPROBAMOS CON LAS DE LA TARDE
	              			HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);	
		              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
		              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
		              		ELSE
		              			FECHA_PROPUESTA:= to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi')+ (HORAS_ACEPTACION/24);
		              			HORAS_ACEPTACION:=0.0;
		              		END IF;
	              		ELSE
	              			FECHA_PROPUESTA:= DIA_PROC + (HORAS_ACEPTACION/24);
	              			HORAS_ACEPTACION:=0.0;
	              		END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                    HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);	
	              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
	              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
	              		ELSE
	              			FECHA_PROPUESTA:= DIA_PROC + (HORAS_ACEPTACION/24);
	              			HORAS_ACEPTACION:=0.0;
	              		END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  	HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);	
	              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
	              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
	              		ELSE
	              			FECHA_PROPUESTA:= DIA_PROC + (HORAS_ACEPTACION/24);
	              			HORAS_ACEPTACION:=0.0;
	              		END IF;
	                END IF;
	             ELSE
	                IF (DIA_PROC    <= to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                    	HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);	
	              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
	              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
	              			-- COMPROBAMOS CON LAS DE LA TARDE
	              			HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);	
		              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
		              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
		              		ELSE
		              			FECHA_PROPUESTA:= to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi')+ (HORAS_ACEPTACION/24);
		              			HORAS_ACEPTACION:=0.0;
		              		END IF;
	              		ELSE
	              			FECHA_PROPUESTA:= to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')+ (HORAS_ACEPTACION/24);
	              			HORAS_ACEPTACION:=0.0;
	              		END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                 	HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);	
	              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
	              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
	              			-- COMPROBAMOS CON LAS DE LA TARDE
	              			HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);	
		              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
		              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
		              		ELSE
		              			FECHA_PROPUESTA:= to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi')+ (HORAS_ACEPTACION/24);
		              			HORAS_ACEPTACION:=0.0;
		              		END IF;
	              		ELSE
	              			FECHA_PROPUESTA:= DIA_PROC + (HORAS_ACEPTACION/24);
	              			HORAS_ACEPTACION:=0.0;
	              		END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                    HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);	
	              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
	              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
	              		ELSE
	              			FECHA_PROPUESTA:= DIA_PROC + (HORAS_ACEPTACION/24);
	              			HORAS_ACEPTACION:=0.0;
	              		END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  	HORAS_ACUM:= ROUND((24 *(to_date(TO_CHAR(DIA_SEL,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);	
	              		IF (HORAS_ACUM < HORAS_ACEPTACION) THEN
	              			HORAS_ACEPTACION:= HORAS_ACEPTACION - HORAS_ACUM;
	              		ELSE
	              			FECHA_PROPUESTA:= DIA_PROC + (HORAS_ACEPTACION/24);
	              			HORAS_ACEPTACION:=0.0;
	              		END IF;
	                END IF;
	              END IF;
      	END IF;
      	DIA_PROC           := DIA_SEL + 1;
      END LOOP;
      
      -- TENEMOS QUE CALCULAR LA FECHA LIMITE
      IF (TIPO_EXPEDIENTE = TIPO_EXP_TRADUCCION) THEN
      		SELECT FECHA_FINAL_IZO_053 into FECHA_LIMITE from AA79B53T00 WHERE ANYO_053 = ANYO_EXP AND NUM_EXP_053 = NUM_EXP;
      ELSIF (TIPO_EXPEDIENTE = TIPO_EXP_INTERPRETACION) THEN
      		BEGIN
      			select Min (FECHA_INICIO_081) into FECHA_LIMITE from AA79B81T00 WHERE ANYO_081 = ANYO_EXP AND NUM_EXP_081 = NUM_EXP AND ID_TIPO_TAREA_081 = TIPO_TAREA_INTERPRETACION;
      		EXCEPTION
          WHEN NO_DATA_FOUND THEN
		        FECHA_LIMITE:= NULL;
          END;
          IF (FECHA_LIMITE is NULL) THEN
            SELECT FECHA_INI_052 into FECHA_LIMITE from AA79B52T00 WHERE ANYO_052 = ANYO_EXP AND NUM_EXP_052 = NUM_EXP;
         END IF;
      END IF;
      
      IF (FECHA_PROPUESTA > FECHA_LIMITE) THEN
      	FECHA_PROPUESTA:= FECHA_LIMITE;
      END IF;
      
      l_limite.EXTEND;
      l_limite(l_limite.count) := (STRUCT_LIM_PRESUPUESTO(FECHA_PROPUESTA, FECHA_LIMITE)) ;
    -- fin
    	RETURN l_limite;
  END;
  /