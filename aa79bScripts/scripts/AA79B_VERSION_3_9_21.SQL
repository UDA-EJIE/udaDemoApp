DEFINE DIR_SCRIPTS = '/aplic/aa79b/scripts';

SET DEFINE OFF;

INSERT INTO AA79B41T00 (ID_041, DESC_ES_041, DESC_EU_041, DESTINATARIO_041, ESTADO_041)
VALUES (30,'Revisión datos de pago al proveedor','Hornitzaileari ordaindu beharrekoa berrikustea', 2,'A');

INSERT INTO AA79B06T00 (ID_006, TEXTO_MAIL_ES_006, TEXTO_MAIL_EU_006, ASUNTO_ES_006, ASUNTO_EU_006)
VALUES (30,'--Revisi&oacute;n datos de pago al proveedor--','--Hornitzaileari ordaindu beharrekoa berrikustea--','Revisi&oacute;n datos de pago al proveedor','Hornitzaileari ordaindu beharrekoa berrikustea');

SET DEFINE ON;

@@&DIR_SCRIPTS/Procedures/ACT_DATOS_PAGO_PROV.SQL;
@@&DIR_SCRIPTS/Functions/COMPROBAR_REAPERTURA_TAREA.SQL;
@@&DIR_SCRIPTS/Procedures/CREAR_TAREAS_RELACIONADAS.SQL;
@@&DIR_SCRIPTS/Functions/COMPROBAR_ORDEN_TAREAS.SQL;
@@&DIR_SCRIPTS/Functions/CREAR_TAREAS_EXPEDIENTE.SQL

exit;