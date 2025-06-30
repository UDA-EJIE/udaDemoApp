create or replace FUNCTION COMPROBAR_ORDEN_TAREAS(
      ANYO          IN NUMBER,
      NUM_EXP       IN NUMBER,
      ID_TIPO_TAREA IN NUMBER,
      ORDEN         IN NUMBER,
      ID_TAREA      IN NUMBER)
    RETURN NUMBER
    IS
      RETORNO                      NUMBER(1):=0; -- 0 orden correcto; 1 orden incorrecto
      TIPO_EXPEDIENTE              VARCHAR2(1);
      TIPO_INTERPRETACION          VARCHAR2(1):= 'I';
      TIPO_TRADUCCION              VARCHAR2(1):= 'T';
      TIPO_REVISION                VARCHAR2(1):= 'R';
      IND_REQ_CIERRE               VARCHAR2(1);
      IND_GESTION_ASOC               VARCHAR2(1);
      TIPO_TAREA_TRADOS            NUMBER(2) := 1;
      TIPO_TAREA_TRADUCCION        NUMBER(2) := 2;
      TIPO_TAREA_CORREDACCION      NUMBER(2) := 3;
      TIPO_TAREA_REVISAR           NUMBER(2) := 4;
      TIPO_TAREA_NO_CONF_PROVEEDOR NUMBER(2) := 5;
      TIPO_TAREA_GESTION_INTER     NUMBER(2) := 6;
      TIPO_TAREA_PREP_INTER        NUMBER(2) := 7;
      TIPO_TAREA_INTERPRETACION    NUMBER(2) := 8;
      TIPO_TAREA_NO_CONF_CLIENTE   NUMBER(2) := 9;
      TIPO_TAREA_POST_BOE          NUMBER(2) := 10;
      TIPO_TAREA_REV_PAGO_PROV     NUMBER(2) := 11;
      TIPO_TAREA_PAGO_PROF_INTER   NUMBER(2) := 12;
      TIPO_TAREA_NOT_CORRE_PROV    NUMBER(2) := 13;
      TIPO_TAREA_ESTUDIAR_SUBS     NUMBER(2) := 14;
      TIPO_TAREA_ALINEAR           NUMBER(2) := 15;
      TIPO_TAREA_ACT_MEMORIAS      NUMBER(2) := 16;
      TIPO_TAREA_ACT_IDABA         NUMBER(2) := 17;
      TIPO_TAREA_TERMINOLOGIA      NUMBER(2) := 18;
      TIPO_TAREA_ENTREGA_TRAD      NUMBER(2) := 19;
      TIPO_TAREA_REV_ENTREGA       NUMBER(2) := 20;
      TIPO_TAREA_REVISAR_TRAD      NUMBER(2) := 21;
      TIPO_TAREA_TRAD_ENTREGA      NUMBER(2) := 22;
      TIPO_TAREA_REVISAR_TRAD_INT      NUMBER(2) := 24;
      TIPO_TAREA_NO_CONF_TRABAJO NUMBER(2) := 23;
      ESTADO_TAREA_EJECUTADA       NUMBER(1) :=3;
      ID_TAREA_REL                 NUMBER(20):= NULL;
      ID_TAREA_AUX				   NUMBER(20):= NULL;
      CURSOR C_TAREAS
      IS
        (SELECT ID_TAREA_081,
          ID_TIPO_TAREA_081,
          ORDEN_081,
          ESTADO_EJECUCION_081,
          IND_REQ_CIERRE_015,
          IND_GESTION_ASOC_015,
          ID_TAREA_REL_081
        FROM AA79B81T00,
          AA79B15T00
        WHERE ANYO_081        = ANYO
        AND NUM_EXP_081       = NUM_EXP
        AND ID_TIPO_TAREA_081 = ID_015
        AND ID_TAREA_081 <> nvl(ID_TAREA,0)
        );
        
        ESTADO NUMBER(2);
        ESTADO_CERRADO NUMBER(2):= 6;
        CONTADOR NUMBER(2):=0;
        ORDEN_TAREAS NUMBER(2);
        ORDEN_TAREA_ACTUAL NUMBER(2);
        IS_TAREA_FOUND boolean := false; 
       
    BEGIN
      BEGIN
        SELECT ID_TIPO_EXPEDIENTE_051
        INTO TIPO_EXPEDIENTE
        FROM AA79B51T00
        WHERE NUM_EXP_051 = NUM_EXP
        AND ANYO_051      = ANYO;
        IF (ORDEN        IS NULL) THEN
          RETORNO        := 1;
        ELSIF (ORDEN      = 0 AND TIPO_EXPEDIENTE = TIPO_REVISION) THEN
          RETORNO        := 1;
        ELSIF (ORDEN      = 0 AND TIPO_EXPEDIENTE <> TIPO_REVISION AND ID_TIPO_TAREA NOT IN (TIPO_TAREA_TRADOS, TIPO_TAREA_GESTION_INTER)) THEN
          RETORNO        := 1;
        ELSE
          -- CONSULTAMOS SI EL TIPO DE TAREA ES REQUERIDA PARA EL CIERRE, SI NO LO ES SE LE PERMITE CUALQUIER ORDEN.
          SELECT IND_REQ_CIERRE_015, IND_GESTION_ASOC_015
          INTO IND_REQ_CIERRE, IND_GESTION_ASOC
          FROM AA79B15T00
          WHERE ID_015 = ID_TIPO_TAREA;
          -- MIRAMOS SI TIENE TAREA RELACIONADA
          BEGIN
            SELECT ID_TAREA_REL_081
            INTO ID_TAREA_REL
            FROM AA79B81T00
            WHERE ID_TAREA_081 = ID_TAREA;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
            ID_TAREA_REL := NULL;
          END;
          IF (IND_GESTION_ASOC = 'S') THEN
            -- NOS RECORREMOS EL CURSOR CON LAS TAREAS DEL EXPEDIENTE
            FOR R_TAREAS IN C_TAREAS
            LOOP
                IF (ID_TIPO_TAREA                      = TIPO_TAREA_TRADUCCION OR ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_PROVEEDOR OR ID_TIPO_TAREA = TIPO_TAREA_REVISAR_TRAD OR ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_TRABAJO OR ID_TIPO_TAREA = TIPO_TAREA_REVISAR_TRAD_INT) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081        = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  = TIPO_TAREA_TRAD_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_REVISAR OR ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_PROVEEDOR) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_REV_PAGO_PROV) THEN
                  	-- esta tarea no es requerida para cierre por lo que COMPROBAMOS EL ESTADO DEL EXPEDIENTE Y SI ES CERRADO COMPROBAMOS QUE EL ORDEN SEA SUPERIOR A LAS TAREAS DE ENTREGA
	            	SELECT ID_ESTADO_EXP_059 into ESTADO FROM AA79B51T00, AA79B59T00 WHERE NUM_EXP_051 = NUM_EXP AND ANYO_051 = ANYO 
	            	AND NUM_EXP_051 = NUM_EXP_059 AND ANYO_051 = ANYO_059 AND ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059;
	            	IF (ESTADO = ESTADO_CERRADO) THEN
	            		SELECT COUNT(1) into CONTADOR FROM AA79B81T00 WHERE ID_TIPO_TAREA_081 in (TIPO_TAREA_ENTREGA_TRAD,TIPO_TAREA_REV_ENTREGA, TIPO_TAREA_TRAD_ENTREGA, TIPO_TAREA_INTERPRETACION) 
	            		AND ORDEN_081 >= ORDEN AND NUM_EXP_081 = NUM_EXP AND ANYO_081 = ANYO;
	            		IF (CONTADOR > 0) THEN
	            			RETORNO:= 1;
	            		END IF;
	            	ELSE
	            		-- recupero la primera tarea de entrega debajo de la relacionada
	            		SELECT ID_TAREA_081 into ID_TAREA_AUX from AA79B81T00 WHERE ID_TIPO_TAREA_081 in (TIPO_TAREA_ENTREGA_TRAD,TIPO_TAREA_REV_ENTREGA, TIPO_TAREA_TRAD_ENTREGA, TIPO_TAREA_INTERPRETACION) 
	            		AND ORDEN_081 > (select t2.ORDEN_081 from AA79B81T00 t2 where t2.ID_TAREA_081 = ID_TAREA_REL) AND NUM_EXP_081 = NUM_EXP AND ANYO_081 = ANYO ORDER BY ORDEN_081 FETCH FIRST ROW ONLY;
	            		
	            		IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  =  TIPO_TAREA_TRAD_ENTREGA)  THEN
	                    	IF (R_TAREAS.ID_TAREA_081 = ID_TAREA_AUX AND ORDEN <= R_TAREAS.ORDEN_081) THEN
		                      RETORNO                         := 1;
		                    END IF; 
                  		END IF;
	                  	IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_REV_ENTREGA) THEN
		                    IF (R_TAREAS.ID_TAREA_081 = ID_TAREA_AUX AND ORDEN <= R_TAREAS.ORDEN_081) THEN
		                      RETORNO                         := 1;
		                    END IF; 
	                 	 END IF;
		           END IF; 
                
               ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_POST_BOE) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  =  TIPO_TAREA_TRAD_ENTREGA)  THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                  --
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_NO_CONF_PROVEEDOR) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  = TIPO_TAREA_TRAD_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_NO_CONF_TRABAJO) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  = TIPO_TAREA_TRAD_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA                  = TIPO_TAREA_NO_CONF_CLIENTE) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  =  TIPO_TAREA_TRAD_ENTREGA)  THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 = ESTADO_TAREA_EJECUTADA AND ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO                        := 1;
                    END IF;
                  END IF;
                  IF (R_TAREAS.ID_TIPO_TAREA_081      = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 = ESTADO_TAREA_EJECUTADA AND ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO                        := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA       = TIPO_TAREA_ENTREGA_TRAD OR ID_TIPO_TAREA  = TIPO_TAREA_TRAD_ENTREGA)  THEN
                  IF (R_TAREAS.IND_REQ_CIERRE_015      = 'S') THEN
                    IF (ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  ELSIF (R_TAREAS.ID_TIPO_TAREA_081      = TIPO_TAREA_REV_PAGO_PROV) THEN
                    	-- recupero la primera tarea de entrega debajo de la relacionada con la de pago
	            		SELECT ID_TAREA_081 into ID_TAREA_AUX from AA79B81T00 WHERE ID_TIPO_TAREA_081 in (TIPO_TAREA_ENTREGA_TRAD,TIPO_TAREA_REV_ENTREGA, TIPO_TAREA_TRAD_ENTREGA, TIPO_TAREA_INTERPRETACION) 
	            		AND ORDEN_081 > (select t2.ORDEN_081 from AA79B81T00 t2 where t2.ID_TAREA_081 = R_TAREAS.ID_TAREA_REL_081) AND NUM_EXP_081 = NUM_EXP AND ANYO_081 = ANYO ORDER BY ORDEN_081 FETCH FIRST ROW ONLY;
	            		
	            		IF (ID_TAREA = ID_TAREA_AUX AND ORDEN >= R_TAREAS.ORDEN_081) THEN
	                      RETORNO                         := 1;
	                    END IF;
                    	
                  END IF;
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_REV_ENTREGA) THEN
                  IF (R_TAREAS.IND_REQ_CIERRE_015      = 'S') THEN
                    IF (ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  ELSIF (R_TAREAS.ID_TIPO_TAREA_081      = TIPO_TAREA_REV_PAGO_PROV) THEN
                    	-- recupero la primera tarea de entrega debajo de la relacionada con la de pago
	            		SELECT ID_TAREA_081 into ID_TAREA_AUX from AA79B81T00 WHERE ID_TIPO_TAREA_081 in (TIPO_TAREA_ENTREGA_TRAD,TIPO_TAREA_REV_ENTREGA, TIPO_TAREA_TRAD_ENTREGA, TIPO_TAREA_INTERPRETACION) 
	            		AND ORDEN_081 > (select t2.ORDEN_081 from AA79B81T00 t2 where t2.ID_TAREA_081 = R_TAREAS.ID_TAREA_REL_081) AND NUM_EXP_081 = NUM_EXP AND ANYO_081 = ANYO ORDER BY ORDEN_081 FETCH FIRST ROW ONLY;
	            		
	            		IF (ID_TAREA = ID_TAREA_AUX AND ORDEN >= R_TAREAS.ORDEN_081) THEN
	                      RETORNO                         := 1;
	                    END IF;
                  END IF;
                END IF;
                IF (ID_TAREA_REL   IS NOT NULL AND R_TAREAS.ID_TAREA_081 = ID_TAREA_REL) THEN
                  IF (ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_PROVEEDOR OR ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_TRABAJO) THEN
                    IF (ORDEN       < R_TAREAS.ORDEN_081) THEN
                      RETORNO      := 1;
                    END IF;
                  ELSE
                    IF (ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO := 1;
                    END IF;
                  END IF;
                END IF;
                IF (ID_TAREA_REL   IS NOT NULL AND R_TAREAS.ID_TAREA_REL_081 = ID_TAREA_REL) THEN
                  IF (ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_PROVEEDOR OR ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_TRABAJO) THEN
                    IF (ORDEN      >= R_TAREAS.ORDEN_081) THEN
                      RETORNO      := 1;
                    END IF;
                  ELSIF (ID_TIPO_TAREA   = TIPO_TAREA_REV_PAGO_PROV AND ( R_TAREAS.ID_TIPO_TAREA_081 = TIPO_TAREA_REVISAR_TRAD OR R_TAREAS.ID_TIPO_TAREA_081 = TIPO_TAREA_REVISAR_TRAD_INT)) THEN
                    IF (ORDEN      <= R_TAREAS.ORDEN_081) THEN
                      RETORNO      := 1;
                    END IF;
                    
                  ELSIF (ID_TIPO_TAREA   = TIPO_TAREA_REVISAR_TRAD AND R_TAREAS.ID_TIPO_TAREA_081 = TIPO_TAREA_REV_PAGO_PROV ) THEN
					IF (ORDEN      >= R_TAREAS.ORDEN_081) THEN
                      RETORNO      := 1;
                    END IF;
                  END IF;
                END IF;
            END LOOP;
            /*IF NOT IS_TAREA_FOUND THEN 
		    	SELECT NVL(MAX(ORDEN_081),0)
		    	INTO ORDEN_TAREAS
		        FROM AA79B81T00
		        WHERE ANYO_081        = ANYO
		        AND NUM_EXP_081       = NUM_EXP
		        AND ID_TAREA_081 <> nvl(ID_TAREA,0);
		        
		        IF (ID_TAREA IS NOT NULL) THEN
	              SELECT ORDEN_081
	              INTO ORDEN_TAREA_ACTUAL
	              FROM AA79B81T00
	              WHERE ID_TAREA_081 = ID_TAREA;
	              
	              IF (ORDEN != ORDEN_TAREA_ACTUAL AND ORDEN <= ORDEN_TAREAS) THEN
	                RETORNO      := 1;
	              END IF;
	            ELSE 
	            	IF (ORDEN <= ORDEN_TAREAS) THEN
	              		RETORNO      := 1;
	              	END IF;
	            END IF;
			END IF;*/
          ELSE
            
          	IF (ORDEN = 0) THEN
              RETORNO:= 1;
            ELSE
            	-- COMPROBAMOS EL ESTADO DEL EXPEDIENTE Y SI ES CERRADO COMPROBAMOS QUE EL ORDEN SEA SUPERIOR A LAS TAREAS DE ENTREGA
            	SELECT ID_ESTADO_EXP_059 into ESTADO FROM AA79B51T00, AA79B59T00 WHERE NUM_EXP_051 = NUM_EXP AND ANYO_051 = ANYO 
            	AND NUM_EXP_051 = NUM_EXP_059 AND ANYO_051 = ANYO_059 AND ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059;
            	IF (ESTADO = ESTADO_CERRADO) THEN
            		SELECT COUNT(1) into CONTADOR FROM AA79B81T00 WHERE ID_TIPO_TAREA_081 in (TIPO_TAREA_ENTREGA_TRAD,TIPO_TAREA_REV_ENTREGA, TIPO_TAREA_TRAD_ENTREGA, TIPO_TAREA_INTERPRETACION) 
            		AND ORDEN_081 >= ORDEN AND NUM_EXP_081 = NUM_EXP AND ANYO_081 = ANYO;
            		IF (CONTADOR > 0) THEN
            			RETORNO:= 1;
            		END IF;
            	END IF;
            END IF;
          END IF;
        END IF;
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETORNO:= 1;
      END;
      RETURN RETORNO;
    END;
    /