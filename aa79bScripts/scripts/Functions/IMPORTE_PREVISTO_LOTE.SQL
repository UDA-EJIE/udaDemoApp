create or replace FUNCTION IMPORTE_PREVISTO_LOTE(ID_LOTE IN NUMBER, ID_TAREA IN NUMBER) RETURN NUMBER IS

--
  -- OBTIENE EL IMPORTE PREVISTO PARA EL LOTE, esto es, el facturado no pagado y el sin facturar.
  -- El id de tarea se corresponde con el id de tarea de revision de los datos de pago del proveedor que se pudiera estar tratando,
  -- para descartar esa tarea en el cálculo ya que se le sumará en pantalla según los datos introducidos
  -- Se comprobará el importe previsto de las tarea de traducción o revisión externas que han generado
  -- tareas de revisión de datos de pago a proveedores y todavía no han sido pagadas.
  -- Tendremos tareas en las que ya se han revisado los datos de pago al proveedor (ESTADO_EJECUCCION = 'S')
  -- y otras en las que todavía no en las que se calculará el importe previsto según los documentos asociados a la tarea.

	RETORNO NUMBER(9,2):= 0.0;
	TIPO_TAREA_REV_PAGO NUMBER(2):= 11;


BEGIN
	SELECT nvl(SUM(ROUND(CASE
WHEN IMPORTE_TOTAL_094 IS NOT NULL THEN IMPORTE_TOTAL_094
WHEN NUM_PAL_IZO_056 < PAL_PRESUPUESTO_003 OR NUM_TOTAL_PAL_091 IS NULL THEN IMPORTE_PALABRA_029 * NUM_PAL_IZO_056
ELSE (IMPORTE_PALABRA_029 * NUM_PAL_CONCOR_0_84_091)
+ (IMPORTE_PALABRA_029 * NUM_PAL_CONCOR_85_94_091 * POR_PAGO_PAL_CONCOR_85_94_029 / 100)
+ (IMPORTE_PALABRA_029 * NUM_PAL_CONCOR_95_100_091 * POR_PAGO_PAL_CONCOR_95_100_029 / 100)
END, 2)),0) AS TOTAL into RETORNO
	FROM AA79B29T00
    JOIN AA79B03T00 ON ID_003 = 0
    JOIN AA79B81T00 TRADREV ON ID_LOTE_029 = TRADREV.ID_LOTE_081
   LEFT JOIN AA79B81T00 REVPAGO ON  TRADREV.ID_TAREA_081 = REVPAGO.ID_TAREA_REL_081
   LEFT JOIN AA79B94T00 ON REVPAGO.ID_TAREA_081 = ID_TAREA_094 AND IND_PAGADO_094 = 'N'
   LEFT JOIN (SELECT ID_TAREA_083, SUM(nvl(NUM_PAL_IZO_056,0)) NUM_PAL_IZO_056, SUM(nvl(NUM_TOTAL_PAL_091,0)) NUM_TOTAL_PAL_091, SUM(nvl(NUM_PAL_CONCOR_0_84_091,0)) NUM_PAL_CONCOR_0_84_091
        , SUM(nvl(NUM_PAL_CONCOR_85_94_091,0)) NUM_PAL_CONCOR_85_94_091,
			SUM(nvl(NUM_PAL_CONCOR_95_100_091,0))  NUM_PAL_CONCOR_95_100_091
            FROM AA79B56T00
            JOIN AA79B83T00 ON ID_DOC_083 = ID_DOC_056
            LEFT JOIN AA79B91T00 ON id_doc_083 = ID_DOC_ORIG_091
            GROUP BY ID_TAREA_083) ON ID_TAREA_083 = TRADREV.ID_TAREA_081
    WHERE ID_LOTE_029 = ID_LOTE
   AND IND_PAGADO_094 = 'N'
   AND REVPAGO.ID_TIPO_TAREA_081 = TIPO_TAREA_REV_PAGO
   AND TRADREV.ID_TIPO_TAREA_081 IN (2, 4)
   AND TRADREV.ID_TAREA_081 <> NVL(ID_TAREA, 0)
   AND REVPAGO.ID_TAREA_081 <> NVL(ID_TAREA, 0);

	RETURN RETORNO;

END;
/
