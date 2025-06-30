CREATE OR REPLACE FUNCTION COMPROBAR_FECHA_FIN_TAREA ( ANYO IN   NUMBER,
   NUM_EXP IN NUMBER, ID_TIPO_TAREA IN NUMBER, FECHA_FIN IN DATE) RETURN NUMBER IS

RETORNO NUMBER(1):=0;
CONTADOR NUMBER(1):=0;

TAREA_PAGO_PROV_INTER NUMBER(3):= 12;
TAREA_NO_CONF_CLIENTE NUMBER(3):= 9;
ESTADO_TAREA_EJECUTADA NUMBER(1):= 3;

BEGIN
	-- Si la tarea es de no conformidad seguramente su fecha será posterior a la fecha de entrega del expediente.
	IF (ID_TIPO_TAREA <> TAREA_NO_CONF_CLIENTE) THEN
		-- comprobamos si hay una no conformidad por parte del cliente, ya que en ese caso es posible que las tareas pasen la fecha límite de entrega
		SELECT COUNT(1) into CONTADOR from AA79B81T00, AA79B82T00 where ANYO_081 = ANYO AND NUM_EXP_081 = NUM_EXP AND ID_TIPO_TAREA_081 = TAREA_NO_CONF_CLIENTE 
		AND ID_TAREA_081 = ID_TAREA_082 AND IND_REALIZADA_082 = 'S' AND ESTADO_EJECUCION_081 = ESTADO_TAREA_EJECUTADA;
		
		IF (CONTADOR = 0) THEN
			-- recuperamos si la tarea es requerida para el cierre del expediente 
			-- y en tal caso la fecha de fin de la tarea es posterior a la de entrega del expediente 
			-- descartamos la tarea de revision de datos de pago a proveedor por trabajos de interpretación, 
			-- ya que aunque se requiera para el cierre se puede ejecutar tras la interpretación
			SELECT count(1) into RETORNO FROM AA79B51T00, AA79B52T00, AA79B53T00 
			WHERE ANYO_051 = ANYO AND NUM_EXP_051 = NUM_EXP AND ANYO_051 = ANYO_052 (+) AND NUM_EXP_051 = NUM_EXP_052 (+)
			AND ANYO_051 = ANYO_053 (+) AND NUM_EXP_051 = NUM_EXP_053 (+)
			AND nvl(FECHA_FIN_052, FECHA_FINAL_IZO_053) < FECHA_FIN
			AND 'S' = (SELECT DECODE(ID_TIPO_TAREA,TAREA_PAGO_PROV_INTER,'N',IND_REQ_CIERRE_015) FROM AA79B15T00 WHERE ID_015 = ID_TIPO_TAREA);
		
		END IF;
	END IF;
	
	return RETORNO;
END;
/
