set serveroutput on
create or replace FUNCTION OBTENER_ESTUDIO(
      TIPO_TAREA     IN NUMBER,
      FECHA_INI      IN DATE,
      FECHA_FIN      IN DATE,
      NUM_PALABRAS   IN NUMBER,
      NUM_PAL_0_84   IN NUMBER,
      NUM_PAL_85_94  IN NUMBER,
      NUM_PAL_95_99 IN NUMBER,
      NUM_PAL_100 IN NUMBER,
      ID_TIPO_RELEVANCIA IN NUMBER,
      NIV_USUARIO IN VARCHAR2)
    RETURN ESTUDIO_TABLE
  IS
    l_estudio ESTUDIO_TABLE := ESTUDIO_TABLE();
    HORARIO_INVIERNO AA79B39S01%ROWTYPE;
    HORARIO_VERANO AA79B39S01%ROWTYPE;
    HORARIO AA79B39S01%ROWTYPE;
    ID_HORARIO NUMBER(2)   :=1;
    HORAS_ACUM NUMBER(12,2):=0;
    DIA_SEL DATE;
    DIA_PROC DATE;
    DIA_FIN DATE;
    DIA_SEMANA_SEL NUMBER(1);
    LIMPALCONFIG_INTERNA AA79B02S02%ROWTYPE;
    CONFIG_INTERNA AA79B02S01%ROWTYPE;
    LIMPALCONFIG_EXTERNA AA79B45S02%ROWTYPE;
    CONFIG_EXTERNA AA79B45S01%ROWTYPE;
    CONTADOR      NUMBER(2)  := 0;
    HORAS_TRABAJO NUMBER(5,2):= 0.0;
    NUM_RECURSOS  NUMBER(3)  := 0;
    DIAS_REALES   NUMBER(5)  := 0;
    TRADUCCION    NUMBER(2)  := 2;
    REVISION      NUMBER(2)  := 4 ;
    -- obtenemos los dias entre 2 fechas y indicando si son en verano o no
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
    WHERE ID_HORARIO_039                                         =ID_HORARIO
    AND IND_VERANO_039                                           = 'I';
    DIA_FIN                                                     := to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD'),'YYYY/MM/DD');
    DIA_PROC                                                    := FECHA_INI;
    HORAS_ACUM                                                  := 0.0;
    WHILE (to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD'),'YYYY/MM/DD') <= to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD'),'YYYY/MM/DD'))
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
          AND VERANO_DESDE_022     <= DIA_SEL
          AND VERANO_HASTA_022     >= DIA_SEL;
          IF (CONTADOR              = 1) THEN
            -- ES VERANO
            HORARIO:=HORARIO_VERANO;
          ELSE
            HORARIO:=HORARIO_INVIERNO;
          END IF;
          -- RECUPERAMOS LAS HORAS DISPONIBLES EN ESE DIA
          IF (DIA_SEL          = DIA_FIN) THEN
            IF (DIA_SEMANA_SEL = 5) THEN
              -- ES VIERNES
              IF (DIA_PROC       < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                IF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +0.0;
                  DIAS_REALES   := DIAS_REALES + 1;
              	ELSIF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(FECHA_FIN - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')))+(24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')))+(24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                END IF;
              ELSIF (DIA_PROC    < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                IF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(FECHA_FIN - DIA_PROC)),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC))+(24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC))+(24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                END IF;
              ELSIF (DIA_PROC    < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                IF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +0.0;
                  DIAS_REALES   := DIAS_REALES + 1;
              	ELSIF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                END IF;
              ELSIF (DIA_PROC    < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                IF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(FECHA_FIN - DIA_PROC)),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                END IF;
              END IF;
            ELSE
              IF (DIA_PROC       < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                IF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +0.0;
                  DIAS_REALES   := DIAS_REALES + 1;
              	ELSIF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(FECHA_FIN - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')))+(24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi')))+(24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                END IF;
              ELSIF (DIA_PROC    < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                IF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(FECHA_FIN - DIA_PROC)),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC))+(24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_M_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC))+(24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                END IF;
              ELSIF (DIA_PROC    < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                IF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +0.0;
                  DIAS_REALES   := DIAS_REALES + 1;
              	ELSIF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(FECHA_FIN - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                END IF;
              ELSIF (DIA_PROC    < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                IF (FECHA_FIN    < to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(FECHA_FIN - DIA_PROC)),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                ELSIF (FECHA_FIN > to_date(TO_CHAR(FECHA_FIN,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                  HORAS_ACUM    := HORAS_ACUM  +ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
                  DIAS_REALES   := DIAS_REALES + 1;
                END IF;
              END IF;
            END IF;
          ELSE
            IF (DIA_SEMANA_SEL = 5) THEN
              -- ES VIERNES
              IF (DIA_PROC    < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                HORAS_ACUM   := ROUND((24   *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||HORARIO.HORA_INI_V_M_039, ' YYYY/MM/DD hh24:mi')))+ (24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                DIAS_REALES  := DIAS_REALES + 1;
              ELSIF (DIA_PROC < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                HORAS_ACUM   := ROUND((24   *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi') - DIA_PROC))+ (24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                DIAS_REALES  := DIAS_REALES + 1;
              ELSIF (DIA_PROC < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                HORAS_ACUM   := ROUND((24   *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                DIAS_REALES  := DIAS_REALES + 1;
              ELSIF (DIA_PROC < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                HORAS_ACUM   := HORAS_ACUM  + ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
                DIAS_REALES  := DIAS_REALES + 1;
              END IF;
            ELSE
              IF (DIA_PROC    < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                HORAS_ACUM   := ROUND((24   *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||HORARIO.HORA_INI_LJ_M_039, ' YYYY/MM/DD hh24:mi')))+ (24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                DIAS_REALES  := DIAS_REALES + 1;
              ELSIF (DIA_PROC < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                HORAS_ACUM   := ROUND((24   *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi') - DIA_PROC))+ (24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                DIAS_REALES  := DIAS_REALES + 1;
              ELSIF (DIA_PROC < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                HORAS_ACUM   := ROUND((24   *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                DIAS_REALES  := DIAS_REALES + 1;
              ELSIF (DIA_PROC < to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                HORAS_ACUM   := HORAS_ACUM  + ROUND((24 *(to_date(TO_CHAR(DIA_PROC,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_PROC)),2);
                DIAS_REALES  := DIAS_REALES + 1;
              END IF;
            END IF;
          END IF;
        END IF;
      END IF;
      DIA_PROC           := DIA_SEL + 1;
    END LOOP;
    -- calculamos las horas necesarias para realizar el trabajo de manera interna o externa
    -- interna
    -- recuperamos de configuración general los datos correspondientes.
    BEGIN
      SELECT * INTO LIMPALCONFIG_INTERNA FROM AA79B02S02 WHERE ID_002 = 0;
      -- nivel usuario y tipo relevancia
      SELECT * INTO CONFIG_INTERNA FROM AA79B02S01 WHERE ID_002 = 0 AND NIVEL_USUARIO_002 = NIV_USUARIO AND ID_TIPO_RELEVANCIA_002  = ID_TIPO_RELEVANCIA;
      IF (TIPO_TAREA        = TRADUCCION) THEN
        IF (NUM_PALABRAS   >= LIMPALCONFIG_INTERNA.LIM_PAL_CONCOR_002) THEN
          HORAS_TRABAJO    := ROUND(NUM_PAL_0_84/CONFIG_INTERNA.PAL_HORA_CONCOR_0_84_002,2) + ROUND(NUM_PAL_85_94/CONFIG_INTERNA.PAL_HORA_CONCOR_85_94_002,2)+ ROUND(NUM_PAL_95_99/CONFIG_INTERNA.PAL_HORA_CONCOR_95_99_002,2)+ ROUND(NUM_PAL_100/CONFIG_INTERNA.PAL_HORA_CONCOR_100_002,2);
          IF (HORAS_TRABAJO = 0) THEN
            HORAS_TRABAJO  := ROUND(NUM_PALABRAS/CONFIG_INTERNA.PAL_TRAD_HORA_002,2);
          END IF;
        ELSE
          HORAS_TRABAJO:= ROUND(NUM_PALABRAS/CONFIG_INTERNA.PAL_TRAD_HORA_002,2);
        END IF;
      ELSE
        HORAS_TRABAJO:= ROUND(NUM_PALABRAS/CONFIG_INTERNA.PAL_REV_HORA_002,2);
      END IF;
      NUM_RECURSOS:= CEIL(HORAS_TRABAJO/HORAS_ACUM);
      l_estudio.EXTEND;
      l_estudio(l_estudio.count) := (STRUCT_ESTUDIO('I', NUM_RECURSOS ,FORMAT_HORA_MINUTOS(HORAS_TRABAJO), FORMAT_HORA_MINUTOS(ROUND((HORAS_TRABAJO/DIAS_REALES)/NUM_RECURSOS,2)))) ;
      -- externa
      SELECT * INTO LIMPALCONFIG_EXTERNA FROM AA79B45S02 WHERE ID_045 = 0;
      --nivel de usuario
      SELECT * INTO CONFIG_EXTERNA FROM AA79B45S01 WHERE ID_045 = 0 AND NIVEL_USUARIO_045 = NIV_USUARIO;
      IF (TIPO_TAREA        = TRADUCCION) THEN
        IF (NUM_PALABRAS   >= LIMPALCONFIG_EXTERNA.LIM_PAL_CONCOR_045) THEN
          HORAS_TRABAJO    := ROUND(NUM_PAL_0_84/CONFIG_EXTERNA.PAL_HORA_CONCOR_0_84_045,2) + ROUND(NUM_PAL_85_94/CONFIG_EXTERNA.PAL_HORA_CONCOR_85_94_045,2)+ ROUND(NUM_PAL_95_99/CONFIG_EXTERNA.PAL_HORA_CONCOR_95_99_045,2)+ ROUND(NUM_PAL_100/CONFIG_EXTERNA.PAL_HORA_CONCOR_100_045,2);
          IF (HORAS_TRABAJO = 0) THEN
            HORAS_TRABAJO  := ROUND(NUM_PALABRAS/CONFIG_EXTERNA.PAL_TRAD_HORA_045,2);
          END IF;
        ELSE
          HORAS_TRABAJO:= ROUND(NUM_PALABRAS/CONFIG_EXTERNA.PAL_TRAD_HORA_045,2);
        END IF;
      ELSE
        HORAS_TRABAJO:= ROUND(NUM_PALABRAS/CONFIG_EXTERNA.PAL_REV_HORA_045,2);
      END IF;
      NUM_RECURSOS:= CEIL(HORAS_TRABAJO/HORAS_ACUM);
      l_estudio.EXTEND;
      l_estudio(l_estudio.count) := (STRUCT_ESTUDIO('E', NUM_RECURSOS ,FORMAT_HORA_MINUTOS(HORAS_TRABAJO), FORMAT_HORA_MINUTOS(ROUND((HORAS_TRABAJO/DIAS_REALES)/NUM_RECURSOS,2)))) ;
    EXCEPTION
    WHEN OTHERS THEN
      l_estudio:= ESTUDIO_TABLE();
      l_estudio.EXTEND;
      l_estudio(l_estudio.count) := (STRUCT_ESTUDIO('I', 0 ,'00:00', '00:00')) ;
      l_estudio.EXTEND;
      l_estudio(l_estudio.count) := (STRUCT_ESTUDIO('E', 0 ,'00:00', '00:00')) ;
    END;
    -- fin
    RETURN l_estudio;
  END;
  /