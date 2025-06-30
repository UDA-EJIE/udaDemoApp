create or replace
FUNCTION HORAS_ENTRE_FECHAS_CALENDAR(
      FECHA_INI IN DATE,
      FECHA_FIN IN DATE)
    RETURN VARCHAR
  IS

    RETORNO VARCHAR2(10):='';
    HORARIO_VERANO AA79B39S01%ROWTYPE;
    HORARIO_INVIERNO AA79B39S01%ROWTYPE;
    HORARIO AA79B39S01%ROWTYPE;
    HORAS_CALENDARIO NUMBER(5,2):= 0.0;
    NUM_DIAS         NUMBER(3)  := 0;
    ES_FESTIVO       NUMBER(1)  := 0;

    CONTADOR            NUMBER(2)  := 0;

    DIA_SEL DATE;
    DIA_PROC DATE;
    DIA_FIN DATE;
    ID_HORARIO      NUMBER(2)   :=1;
    HORAS_ACUM      NUMBER(12,2):=0;

    DIA_SEMANA_SEL NUMBER(1);

    BEGIN
     -- OBTENEMOS LOS HORARIOS DE VERANO Y DE INVIERNO
      SELECT *
      INTO HORARIO_VERANO
      FROM AA79B39S01
      WHERE ID_HORARIO_039=ID_HORARIO
      AND IND_VERANO_039  = 'V';
      SELECT *
      INTO HORARIO_INVIERNO
      FROM AA79B39S01
      WHERE ID_HORARIO_039=ID_HORARIO
      AND IND_VERANO_039    = 'I';

      DIA_FIN        := to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD'),'YYYY/MM/DD');
      DIA_PROC         := FECHA_INI;
      HORAS_ACUM      := 0.0;
        WHILE (to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD'),'YYYY/MM/DD') <=  to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD'),'YYYY/MM/DD'))
        LOOP
          DIA_SEL        := to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD'),'YYYY/MM/DD');
          DIA_SEMANA_SEL := MOD(TO_CHAR(DIA_PROC, 'J'), 7)+1;
          -- COMPROBAMOS QUE NO SEA FIN DE SEMANA
          IF (DIA_SEMANA_SEL < 6) THEN
            --  COMPROBAMOS QUE NO SEA FESTIVO
            SELECT COUNT(1)
            INTO CONTADOR
            FROM AA79B23S01
            WHERE DIA_CALENDARIO_023 = DIA_SEL;
            IF (CONTADOR             = 0) THEN
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
              -- RECUPERAMOS LAS HORAS DISPONIBLES EN ESE DIA
              IF (DIA_SEL = DIA_FIN) THEN
                 IF (DIA_SEMANA_SEL = 5) THEN
	                -- ES VIERNES
	              	 IF (DIA_PROC    <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                    IF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +0.0;
	              	 	ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(FECHA_FIN - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi'))),2);
		                ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                 ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')))+(24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')))+(24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                 	IF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(FECHA_FIN - DIA_PROC)),2);
		                ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
		                 ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC))+(24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC))+(24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                    IF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                      HORAS_ACUM   := HORAS_ACUM +0.0;
	                    ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  	IF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(FECHA_FIN - DIA_PROC)),2);
		                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
		                END IF;
	                END IF;
	             ELSE
	                IF (DIA_PROC    <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                    IF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                      HORAS_ACUM   := HORAS_ACUM + 0.0;
	              	 	ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(FECHA_FIN - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi'))),2);
		                ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                 ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')))+(24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')))+(24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                 	IF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(FECHA_FIN - DIA_PROC)),2);
		                ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
		                 ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC))+(24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC))+(24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                    IF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
			                  HORAS_ACUM   := HORAS_ACUM +0.0;
	                    ELSIF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
		                END IF;
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  	IF (FECHA_FIN <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(FECHA_FIN - DIA_PROC)),2);
		                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
		                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
		                END IF;
	                END IF;
	              END IF;
              ELSE
	              IF (DIA_SEMANA_SEL = 5) THEN
	                -- ES VIERNES
	                IF (DIA_PROC    <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||HORARIO.HORA_INI_V_M_039, ' YYYY/MM/DD hh24:mi')))+ (24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi') - DIA_PROC))+ (24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  HORAS_ACUM   := HORAS_ACUM +HORAS_ACUM + ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
	                END IF;
	              ELSE
	                IF (DIA_PROC    <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||HORARIO.HORA_INI_LJ_M_039, ' YYYY/MM/DD hh24:mi')))+ (24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi') - DIA_PROC))+ (24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  HORAS_ACUM   := HORAS_ACUM +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
	                ELSIF (DIA_PROC <= to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
	                  HORAS_ACUM   := HORAS_ACUM +HORAS_ACUM + ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
	                END IF;
	              END IF;
	          END IF;
            END IF;
          END IF;
          DIA_PROC           := DIA_SEL + 1;
        END LOOP;
      RETORNO:= FORMAT_HORA_MINUTOS(HORAS_ACUM);
     RETURN RETORNO;
    END;
    /