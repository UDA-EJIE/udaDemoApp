-- CREAR PROCEDURE REL_EXP_REF_TRAM
create or replace PROCEDURE REL_EXP_REF_TRAM  
IS 
-- SELECCIONAMOS LAS REFERENCIAS QUE HAY EN LA TABLA 53 SIN DUPLICADOS; 
CURSOR C_REFERENCIAS IS 
(SELECT REF_TRAMITAGUNE_053 FROM AA79B53S01 WHERE REF_TRAMITAGUNE_053 IS NOT NULL GROUP BY REF_TRAMITAGUNE_053 ); 
BEGIN 
    FOR R_REFERENCIA IN C_REFERENCIAS LOOP 
        MERGE INTO aa79b57t00 USING ( 
         WITH relaciones AS ( 
            SELECT 
                anyo_053, 
                num_exp_053, 
                ORIGEN_051, 
                APLIC_ORIGEN_051, 
                DNI_SOLICITANTE_054 
            FROM 
                Aa79b53t00 
                JOIN AA79B51T00 
                on anyo_053 = anyo_051 and num_exp_053 = num_exp_051 
                join aa79b54t00 
                on anyo_054 = anyo_053 and num_exp_054 = num_exp_053 
            WHERE 
                ref_tramitagune_053 is not null and 
                UPPER(TRIM(ref_tramitagune_053)) LIKE UPPER(TRIM(R_REFERENCIA.REF_TRAMITAGUNE_053)) escape '\' 
        ) SELECT 
            a.anyo_053 anyo, 
            a.num_exp_053 num_exp, 
            b.anyo_053 anyo_rel, 
            b.num_exp_053 num_exp_rel 
        FROM 
            relaciones a 
            CROSS JOIN relaciones b 
         WHERE 
            ( a.anyo_053 != b.anyo_053 
            OR a.num_exp_053 != b.num_exp_053 ) 
            AND  
			/** ORIGEN W Y MISMA APLICACION*/ 
            ( ( a.origen_051 = b.origen_051 
            AND a.origen_051 = 'W' AND a.aplic_origen_051 IS NOT NULL 
            AND b.origen_051 = 'W' AND b.aplic_origen_051 IS NOT NULL 
            AND a.aplic_origen_051 = b.aplic_origen_051 ) 
            OR 
			/** ORIGEN O o S Y MISMO CAMPO */ 
            ( a.origen_051 IN ( 'S', 'O' ) 
            AND b.origen_051 IN ( 'S', 'O' )  
            and a.DNI_SOLICITANTE_054 = b.DNI_SOLICITANTE_054 ))
            ) 
        s ON ( 
            anyo_057 = anyo 
            AND num_exp_057 = num_exp 
            AND anyo_exp_rel_057 = anyo_rel 
            AND num_exp_rel_057 = num_exp_rel 
        ) 
        WHEN NOT MATCHED THEN INSERT ( 
            anyo_057, 
            num_exp_057, 
            anyo_exp_rel_057, 
            num_exp_rel_057 ) VALUES ( 
            anyo, 
            num_exp, 
            anyo_rel, 
            num_exp_rel ); 
    END LOOP;	 
END;
/

EXIT;