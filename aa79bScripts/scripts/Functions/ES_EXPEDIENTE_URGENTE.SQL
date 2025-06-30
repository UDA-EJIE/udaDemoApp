CREATE OR REPLACE
  FUNCTION ES_EXPEDIENTE_URGENTE(
      ANYO              IN NUMBER,
      NUM_EXP           IN NUMBER,
      TIPO_EXP          IN VARCHAR2,
      NUM_PALABRAS_IZO  IN NUMBER,
      FECHA_ENTREGA_IZO IN DATE,
      IND_VIP           IN VARCHAR2 )
    RETURN VARCHAR
  IS
    CONFIGURACION AA79B01S01%ROWTYPE;
    HORARIO_VERANO AA79B39S01%ROWTYPE;
    HORARIO_INVIERNO AA79B39S01%ROWTYPE;
    HORARIO AA79B39S01%ROWTYPE;
    RETORNO             VARCHAR2(1):='N';
    CONTADOR            NUMBER(2)  := 0;
    TIPO_INTERPRETACION VARCHAR2(1):= 'I';
    TIPO_TRADUCCION     VARCHAR2(1):= 'T';
    TIPO_REVISION       VARCHAR2(1):= 'R';
    NUM_PALABRAS_CONFIG NUMBER(6);
    HORAS_TOTALES       NUMBER(12,2):=0;
    DIA_SEL DATE;
    ID_HORARIO      NUMBER(2)   :=1;
    HORAS_RESTANTES NUMBER(12,2):=0;
    DIA_INI DATE;
    HORAS_DIA      NUMBER(12,2):=0;
    DIA_SEMANA_SEL NUMBER(1);
    HORAS_MANANA   NUMBER(12,2):=0;
    HORAS_TARDE    NUMBER(12,2):=0;
    DIA_DEFINITIVO DATE;
  BEGIN
    IF (TIPO_EXP <> TIPO_INTERPRETACION) THEN
      -- Comprobamos si tiene tareas.
      SELECT COUNT(1)
      INTO contador
      FROM AA79B81T00
      WHERE ANYO_081  = ANYO
      AND NUM_EXP_081 = NUM_EXP;
      IF (contador    = 0) THEN
        -- CALCULAMOS LAS HORAS NECESARIAS PARA EL TRABAJO SOLICITADO
        IF (IND_VIP = 'S') THEN
          SELECT *
          INTO CONFIGURACION
          FROM AA79B01S01
          WHERE ID_001          = 0
          AND NIVEL_USUARIO_001 = 'V';
        ELSE
          SELECT *
          INTO CONFIGURACION
          FROM AA79B01S01
          WHERE ID_001          = 0
          AND NIVEL_USUARIO_001 = 'N';
        END IF;
        -- OBTENEMOS LOS HORARIOS DE VERANO Y DE INVIERNO
        SELECT *
        INTO HORARIO_VERANO
        FROM AA79B39S01
        WHERE ID_HORARIO_039=ID_HORARIO
        AND IND_VERANO_039  = 'V';
        SELECT *
        INTO HORARIO_INVIERNO
        FROM AA79B39S01
        WHERE ID_HORARIO_039  =ID_HORARIO
        AND IND_VERANO_039    = 'I';
        IF (TIPO_EXP          = TIPO_TRADUCCION) THEN
          NUM_PALABRAS_CONFIG:= CONFIGURACION.PALABRAS_TRAD_HORA_001;
        ELSE
          NUM_PALABRAS_CONFIG:= CONFIGURACION.PALABRAS_REV_HORA_001;
        END IF;
        -- SUMAMOS LAS HORAS CORRESPONDIENTES A LAS PALABRAS
        IF (NUM_PALABRAS_IZO <= NUM_PALABRAS_CONFIG) THEN
          HORAS_TOTALES      := HORAS_TOTALES + 1;
        ELSE
          HORAS_TOTALES := HORAS_TOTALES + ceil(NUM_PALABRAS_IZO/NUM_PALABRAS_CONFIG);
        END IF;
        -- VAMOS RECORRIENDO LOS DIAS PARA VER SI SE PUEDE HACER ANTES DE LA FECHA DE ENTREGA
        HORAS_RESTANTES := HORAS_TOTALES;
        DIA_INI         := SYSDATE;
        IF DIA_INI       > FECHA_ENTREGA_IZO THEN
          RETORNO       :='S';
        ELSE
          WHILE HORAS_RESTANTES > 0
          LOOP
            DIA_SEL        := to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD'),'YYYY/MM/DD');
            HORAS_DIA      := 0.0;
            DIA_SEMANA_SEL := MOD(TO_CHAR(DIA_INI, 'J'), 7)+1;
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
                IF (DIA_SEMANA_SEL = 5) THEN
                  -- ES VIERNES
                  IF (DIA_INI    <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                    HORAS_DIA    := HORAS_DIA + ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||HORARIO.HORA_INI_V_M_039, ' YYYY/MM/DD hh24:mi')))+ (24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  ELSIF (DIA_INI <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                    HORAS_DIA    := HORAS_DIA + ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi') - DIA_INI))+ (24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  ELSIF (DIA_INI <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                    HORAS_DIA    := HORAS_DIA + ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  ELSIF (DIA_INI <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                    HORAS_DIA    := HORAS_DIA + ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_V_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_INI)),2);
                  END IF;
                ELSE
                  IF (DIA_INI    <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                    HORAS_DIA    := HORAS_DIA + ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||HORARIO.HORA_INI_LJ_M_039, ' YYYY/MM/DD hh24:mi')))+ (24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  ELSIF (DIA_INI <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                    HORAS_DIA    := HORAS_DIA + ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi') - DIA_INI))+ (24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  ELSIF (DIA_INI <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                    HORAS_DIA    := HORAS_DIA + ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_INI_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi'))),2);
                  ELSIF (DIA_INI <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                    HORAS_DIA    := HORAS_DIA + ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||NVL(HORARIO.HORA_FIN_LJ_T_039,'08:00'), 'YYYY/MM/DD hh24:mi') - DIA_INI)),2);
                  END IF;
                END IF;
                IF (HORAS_RESTANTES < HORAS_DIA) THEN
                  -- HEMOS ENCONTRADO EL DIA TENEMOS QUE BUSCAR LA HORA CONCRETA TENIENDO EN CUENTA QUE EL HORARIO ESTA PARTIDO.
                  IF (DIA_SEMANA_SEL = 5) THEN
                    -- ES VIERNES
                    IF (DIA_INI       <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                      HORAS_MANANA    := ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||HORARIO.HORA_INI_V_M_039, ' YYYY/MM/DD hh24:mi'))),2);
                      IF (HORAS_MANANA > HORAS_RESTANTES) THEN
                        DIA_DEFINITIVO:= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_M_039, 'YYYY/MM/DD hh24:mi') + (HORAS_RESTANTES)/24;
                      ELSE
                        DIA_DEFINITIVO:= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi') + (HORAS_RESTANTES-HORAS_MANANA)/24;
                      END IF;
                    ELSIF (DIA_INI    <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                      HORAS_MANANA    := ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_M_039, 'YYYY/MM/DD hh24:mi') - DIA_INI)),2);
                      IF (HORAS_MANANA > HORAS_RESTANTES) THEN
                        DIA_DEFINITIVO:= DIA_INI + (HORAS_RESTANTES)/24;
                      ELSE
                        DIA_DEFINITIVO:= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi') + (HORAS_RESTANTES-HORAS_MANANA)/24;
                      END IF;
                    ELSIF (DIA_INI  <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                      DIA_DEFINITIVO:= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_V_T_039, 'YYYY/MM/DD hh24:mi') + (HORAS_RESTANTES)/24;
                    ELSIF (DIA_INI  <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_V_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                      DIA_DEFINITIVO:= DIA_INI + (HORAS_RESTANTES)/24;
                    END IF;
                  ELSE
                    IF (DIA_INI       <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                      HORAS_MANANA    := ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi') - to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' ||HORARIO.HORA_INI_LJ_M_039, ' YYYY/MM/DD hh24:mi'))),2);
                      IF (HORAS_MANANA > HORAS_RESTANTES) THEN
                        DIA_DEFINITIVO:= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_M_039, 'YYYY/MM/DD hh24:mi') + (HORAS_RESTANTES)/24;
                      ELSE
                        DIA_DEFINITIVO:= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi') + (HORAS_RESTANTES-HORAS_MANANA)/24;
                      END IF;
                    ELSIF (DIA_INI    <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi')) THEN
                      HORAS_MANANA    := ROUND((24 *(to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_M_039, 'YYYY/MM/DD hh24:mi') - DIA_INI)),2);
                      IF (HORAS_MANANA > HORAS_RESTANTES) THEN
                        DIA_DEFINITIVO:= DIA_INI + (HORAS_RESTANTES)/24;
                      ELSE
                        DIA_DEFINITIVO:= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi') + (HORAS_RESTANTES-HORAS_MANANA)/24;
                      END IF;
                    ELSIF (DIA_INI  <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                      DIA_DEFINITIVO:= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_INI_LJ_T_039, 'YYYY/MM/DD hh24:mi') + (HORAS_RESTANTES)/24;
                    ELSIF (DIA_INI  <= to_date(TO_CHAR(DIA_INI,'YYYY/MM/DD') || ' ' || HORARIO.HORA_FIN_LJ_T_039, 'YYYY/MM/DD hh24:mi')) THEN
                      DIA_DEFINITIVO:= DIA_INI + (HORAS_RESTANTES)/24;
                    END IF;
                  END IF;
                  IF DIA_DEFINITIVO > FECHA_ENTREGA_IZO THEN
                    RETORNO        :='S';
                  END IF;
                  HORAS_RESTANTES := 0;
                ELSE
                  HORAS_RESTANTES   := HORAS_RESTANTES - HORAS_DIA;
                  DIA_INI           := DIA_SEL         + 1;
                  IF DIA_INI         > FECHA_ENTREGA_IZO THEN
                    RETORNO         :='S';
                    HORAS_RESTANTES := 0;
                  END IF;
                END IF;
              ELSE
                DIA_INI           := DIA_SEL + 1;
                IF DIA_INI         > FECHA_ENTREGA_IZO THEN
                  RETORNO         :='S';
                  HORAS_RESTANTES := 0;
                END IF;
              END IF;
            ELSE
              DIA_INI           := DIA_SEL + 1;
              IF DIA_INI         > FECHA_ENTREGA_IZO THEN
                RETORNO         :='S';
                HORAS_RESTANTES := 0;
              END IF;
            END IF;
          END LOOP;
        END IF;
      END IF;
    END IF;
    RETURN RETORNO;
  END;
  /