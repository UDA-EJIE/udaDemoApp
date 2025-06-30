CREATE OR REPLACE FUNCTION TIENE_OTRAS_TAREAS( DNI_ASIGNADO IN   VARCHAR,
   ID_TAREA IN NUMBER, ID_TAREA_TRABAJO IN NUMBER) RETURN VARCHAR IS

RETORNO VARCHAR2(1):='N';

CONTADOR NUMBER(4);

FECHA_INI DATE;
FECHA_FIN DATE;

ESTADO_ACEPTADA NUMBER(1):=  3;
ESTADO_EJECUTADA NUMBER(1):=  3;


BEGIN
	IF (DNI_ASIGNADO is not null AND ID_TAREA is not null) THEN
		-- Buscamos que no tenga ninguna ausencia entre esas fechas o una interpertación cuya asignación este aceptada por el recurso.
		Select FECHA_INICIO_081, FECHA_FIN_081 into FECHA_INI, FECHA_FIN from AA79B81T00 WHERE ID_TAREA_081 = ID_TAREA;
	END IF;
    IF  (DNI_ASIGNADO is not null AND ID_TAREA_TRABAJO is not null) THEN
		Select FECHA_INICIO_0C7, FECHA_FIN_0C7 into FECHA_INI, FECHA_FIN from AA79BC7T00 WHERE ID_TAREA_0C7 = ID_TAREA_TRABAJO;
	END IF;
	
	IF (DNI_ASIGNADO is not null AND (ID_TAREA is not null OR ID_TAREA_TRABAJO is not null)) THEN
		select count(1) into contador from AA79B81T00 
	    WHERE (ID_TAREA_081 <> ID_TAREA) 
	    AND RECURSO_ASIGNACION_081 = 'I' AND DNI_RECURSO_081 = DNI_ASIGNADO
	    AND ESTADO_EJECUCION_081 <> ESTADO_EJECUTADA -- SIN EJECUTAR
	    AND ((TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INICIO_081,'yyyyMMdd'),'yyyyMMdd') 
		    AND TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN_081,'yyyyMMdd'),'yyyyMMdd'))
	    OR(TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INICIO_081,'yyyyMMdd'),'yyyyMMdd') 
		    AND TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN_081,'yyyyMMdd'),'yyyyMMdd'))
	    OR(TO_DATE(TO_CHAR(FECHA_INICIO_081,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') 
		    AND TO_DATE(TO_CHAR(FECHA_INICIO_081,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd'))
	    OR(TO_DATE(TO_CHAR(FECHA_FIN_081,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') 
		    AND TO_DATE(TO_CHAR(FECHA_FIN_081,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd')));
		if (contador = 0) THEN
			select count(1) into contador from AA79BC7T00 
		    WHERE (ID_TAREA_0C7 <> ID_TAREA_TRABAJO) 
		    AND DNI_RECURSO_0C7 = DNI_ASIGNADO
		    AND ESTADO_EJECUCION_0C7 <> 3 -- SIN EJECUTAR
		    AND ((TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INICIO_0C7,'yyyyMMdd'),'yyyyMMdd') 
			    AND TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN_0C7,'yyyyMMdd'),'yyyyMMdd'))
		    OR(TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INICIO_0C7,'yyyyMMdd'),'yyyyMMdd') 
			    AND TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN_0C7,'yyyyMMdd'),'yyyyMMdd'))
		    OR(TO_DATE(TO_CHAR(FECHA_INICIO_0C7,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') 
			    AND TO_DATE(TO_CHAR(FECHA_INICIO_0C7,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd'))
		    OR(TO_DATE(TO_CHAR(FECHA_FIN_0C7,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') 
			    AND TO_DATE(TO_CHAR(FECHA_FIN_0C7,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd')));
			if (contador > 0) THEN
				RETORNO:='S';
			END IF; 
		else
			RETORNO:='S';
		END IF; 
	END IF;
	RETURN RETORNO;
END;
/
