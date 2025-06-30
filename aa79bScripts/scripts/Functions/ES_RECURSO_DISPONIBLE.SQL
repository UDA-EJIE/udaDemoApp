CREATE OR REPLACE FUNCTION ES_RECURSO_DISPONIBLE( DNI_ASIGNADO IN   VARCHAR,
   ID_TAREA IN NUMBER, ID_TAREA_TRABAJO IN NUMBER) RETURN VARCHAR IS

RETORNO VARCHAR2(1):='S';

CONTADOR NUMBER(4);

FECHA_INI DATE;
FECHA_FIN DATE;

ESTADO_ACEPTADA NUMBER(1):=  3;


BEGIN
	IF (DNI_ASIGNADO is not null AND ID_TAREA is not null) THEN
		-- Buscamos que no tenga ninguna ausencia entre esas fechas o una interpertación cuya asignación este aceptada por el recurso.
		Select FECHA_INICIO_081, FECHA_FIN_081 into FECHA_INI, FECHA_FIN from AA79B81T00 WHERE ID_TAREA_081 = ID_TAREA;
	END IF;
    IF  (DNI_ASIGNADO is not null AND ID_TAREA_TRABAJO is not null) THEN
		Select FECHA_INICIO_0C7, FECHA_FIN_0C7 into FECHA_INI, FECHA_FIN from AA79BC7T00 WHERE ID_TAREA_0C7 = ID_TAREA_TRABAJO;
	END IF;
	
	IF (DNI_ASIGNADO is not null AND (ID_TAREA is not null OR ID_TAREA_TRABAJO is not null)) THEN
	select count(1) into contador from AA79B25T00 
    WHERE (ID_TAREA_INTER_025 is null OR ID_TAREA_INTER_025 <> ID_TAREA) AND DNI_025 = DNI_ASIGNADO
    AND ((TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_DESDE_025,'yyyyMMdd'),'yyyyMMdd') 
	    AND TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_HASTA_025,'yyyyMMdd'),'yyyyMMdd'))
    OR(TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_DESDE_025,'yyyyMMdd'),'yyyyMMdd') 
	    AND TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_HASTA_025,'yyyyMMdd'),'yyyyMMdd'))
    OR(TO_DATE(TO_CHAR(FECHA_DESDE_025,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') 
	    AND TO_DATE(TO_CHAR(FECHA_DESDE_025,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd'))
    OR(TO_DATE(TO_CHAR(FECHA_HASTA_025,'yyyyMMdd'),'yyyyMMdd') >= TO_DATE(TO_CHAR(FECHA_INI,'yyyyMMdd'),'yyyyMMdd') 
	    AND TO_DATE(TO_CHAR(FECHA_HASTA_025,'yyyyMMdd'),'yyyyMMdd') <= TO_DATE(TO_CHAR(FECHA_FIN,'yyyyMMdd'),'yyyyMMdd')));
	
	if (contador > 0) THEN
		RETORNO:='N';
	END IF; 
	END IF;
	RETURN RETORNO;
END;
/
