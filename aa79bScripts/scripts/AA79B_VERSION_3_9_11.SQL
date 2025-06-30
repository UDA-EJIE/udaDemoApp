DEFINE DIR_SCRIPTS = '/aplic/aa79b/scripts';

UPDATE AA79B15T00 SET DESC_EU_015 = 'Berrikusketa eta bezeroari entregatzea (Itzulpena)', DESC_ES_015 = 'Revisión con entrega a cliente (Traducción)' WHERE ID_015 = 19;
UPDATE AA79B15T00 SET DESC_EU_015 = 'Berrikusketa eta bezeroari entregatzea', DESC_ES_015 = 'Revisión con entrega a cliente' WHERE ID_015 = 20;

@@&DIR_SCRIPTS/Functions/OBTENER_PLAZO_MINIMO_ENTREGA.SQL;
@@&DIR_SCRIPTS/Functions/COMPROBAR_TIPO_TAREA.SQL;
@@&DIR_SCRIPTS/Functions/CREAR_TAREAS_EXPEDIENTE.SQL;
@@&DIR_SCRIPTS/Procedures/CREAR_TAREAS_RELACIONADAS.SQL;
@@&DIR_SCRIPTS/Procedures/ACT_DATOS_DOCUMENTACION.SQL;

exit;