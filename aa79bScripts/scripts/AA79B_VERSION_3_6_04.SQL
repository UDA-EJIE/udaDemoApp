DEFINE DIR_SCRIPTS = '/aplic/aa79b/scripts';

@@&DIR_SCRIPTS/Tipos/DROP_TIPOS.SQL;
@@&DIR_SCRIPTS/Tipos/STRUCT_ESTUDIO.SQL;
@@&DIR_SCRIPTS/Tipos/ESTUDIO_TABLE.SQL;
@@&DIR_SCRIPTS/Tipos/STRUCT_LIM_PRESUPUESTO.SQL;
@@&DIR_SCRIPTS/Tipos/LIM_PRESUPUESTO_TABLE.SQL;
@@&DIR_SCRIPTS/Tipos/NT_SPLIT_RESULT.SQL;
@@&DIR_SCRIPTS/Procedures/CARGAR_CALENDARIO_SERVICIO.SQL;
@@&DIR_SCRIPTS/Functions/FORMAT_HORA_MINUTOS.SQL;
@@&DIR_SCRIPTS/Functions/COMPROBAR_FINALIZACION_ESTUDIO.SQL;
@@&DIR_SCRIPTS/Functions/OBTENER_NUM_EXPEDIENTE.SQL;
@@&DIR_SCRIPTS/Functions/OBTENER_PLAZO_MINIMO_ENTREGA.SQL;
@@&DIR_SCRIPTS/Procedures/ACT_DATOS_DOCUMENTACION.SQL;
@@&DIR_SCRIPTS/Functions/OBTENER_ESTADO_SOLICITANTE.SQL;
@@&DIR_SCRIPTS/Procedures/CREAR_TAREAS_DEFECTO.SQL;
@@&DIR_SCRIPTS/Functions/HORAS_ENTRE_FECHAS_CALENDAR.SQL;
@@&DIR_SCRIPTS/Functions/CALCULO_HORAS_INTERPRETACION.SQL;
@@&DIR_SCRIPTS/Functions/F_SPLIT_STRING_INTO_ARRAY.SQL;
@@&DIR_SCRIPTS/Functions/CALCULO_HORAS_TRADUCCION.SQL;
@@&DIR_SCRIPTS/Functions/CALCULO_PRESUPUESTO_INTERPRETACION.SQL;
@@&DIR_SCRIPTS/Functions/CALCULO_PRESUPUESTO_TRADUCCION.SQL;
@@&DIR_SCRIPTS/Functions/COMPROBAR_FECHA_FIN_TAREA.SQL;
@@&DIR_SCRIPTS/Functions/COMPROBAR_ORDEN_TAREAS.SQL;
@@&DIR_SCRIPTS/Functions/COMPROBAR_TIPO_TAREA.SQL;
@@&DIR_SCRIPTS/Functions/COSTE_EXPEDIENTE_LOTE.SQL;
@@&DIR_SCRIPTS/Functions/ES_EXPEDIENTE_URGENTE.SQL;
@@&DIR_SCRIPTS/Functions/ES_RECURSO_DISPONIBLE.SQL;
@@&DIR_SCRIPTS/Functions/ESTADO_TAREAS_ASIGNADAS.SQL;
@@&DIR_SCRIPTS/Functions/ESTADO_TAREAS_DEPENDIENTES.SQL;
@@&DIR_SCRIPTS/Functions/OBTENER_ESTUDIO.SQL;
@@&DIR_SCRIPTS/Functions/OBTENER_RESPONSABLE_TRABAJO.SQL;
@@&DIR_SCRIPTS/Functions/TIENE_TAREAS_ENTREGA.SQL;
@@&DIR_SCRIPTS/Functions/IMPORTE_PREVISTO_LOTE.SQL;
@@&DIR_SCRIPTS/Functions/REQUIERE_TRADOS.SQL;
@@&DIR_SCRIPTS/Procedures/CALCULO_IMPORTES_INTERPRETACION.SQL;
@@&DIR_SCRIPTS/Functions/OBTENER_LIM_PRESUPUESTO.SQL;
@@&DIR_SCRIPTS/Functions/OBTENER_GRUPO_TRABAJO.SQL;
@@&DIR_SCRIPTS/Procedures/CREAR_TAREAS_RELACIONADAS.SQL
@@&DIR_SCRIPTS/Functions/COMPROBAR_REAPERTURA_TAREA.SQL;
@@&DIR_SCRIPTS/Functions/COMPROBAR_PAGADO.SQL;
@@&DIR_SCRIPTS/Functions/PARSE_HORA_MINUTOS.SQL;
@@&DIR_SCRIPTS/Procedures/ASOCIAR_DOC_A_ENTREGA.SQL;
@@&DIR_SCRIPTS/AA79B_Correspondencia.sql;
/

ALTER TABLE AA79BA9T00 DROP CONSTRAINT AA79B61T00_A9_FK;

ALTER TABLE AA79BA9T00 ADD CONSTRAINT AA79B61T00_A9_FK
FOREIGN KEY (ID_FASE_EXP_0A9)
REFERENCES AA79B61T00 (ID_061);

SET DEFINE OFF;
delete from AA79BA9T00;
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (2, 3, 1, 1, 'eu_Volverá al momento en el que el expediente se encontraba en este estado-fase. De manera que a través de la pantalla de detalle del expediente seleccionado, podrá  revisar los datos originales de la solicitud con el propósito de asegurar la coherencia del expediente en cuanto a tipo de expediente, idioma destino, relevancia, documentación y urgencia del mismo se refiere, completará la información requerida para continuar la tramitación del expediente y prepararlo para su planificación y asignación y, si procede, gestionar el requerimiento de una subsanación de datos o documentación al cliente y su posterior estudio.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (3, 9, 1, 1, 'eu_Se pasará a trabajar con el expediente como en el momento en que se encontraba en este Estado-Fase.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (3, 9, 2, 1, 'eu_Fase/estado que se genera para los expedientes que tienen tareas requeridas para el cierre pendientes de ejecutar.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (6, 10, 1, 1, 'eu_Volverá al estado en que quedó el expediente "facturable" tras finalizar la tarea de entrega a cliente.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 1, 1, 'eu_Podrá rechazar el expediente si lo considera oportuno.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 2, 1, 'eu_Será obligatorio seleccionar un motivo de rechazo de entre los tipificados.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 3, 1, 'eu_En caso de seleccionar "Otro" se requerirá como obligatorio la edición de un campo de texto libre para contemplar las aclaraciones que el asignador crea pertinentes.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 4, 1, 'eu_El estado del expediente se actualizará a "rechazado"');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 5, 1, 'eu_El solicitante recibirá un email de aviso indicándole que el expediente ha sido rechazado y que puede consultar los detalles del trámite a través de la aplicación web.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 1, 1, 'eu_Si el cliente no aporta la subsanación requerida por el IZO en el plazo estipulado para ello.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 2, 2, 'eu_Son expedientes que están en estado "En estudio" y fase "Pendiente tramitación cliente por subsanación", el requerimiento no se ha realizado y se ha superado la fecha límite del requerimiento.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 3, 1, 'eu_Si no se tramita la respuesta al presupuesto en el plazo estipulado para ello.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 4, 2, 'eu_Son expedientes que están en estado "En curso" y fase "Pendiente tramitación cliente", el requerimiento de tipo "Aceptación presupuesto" o "Aceptación de presupuesto y fecha propuesta por el IZO" no se ha realizado y se ha superado la fecha límite del requerimiento.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 5, 1, 'eu_Si no se tramita la respuesta a la fecha propuesta por el IZO en el plazo estipulado.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 6, 2, 'eu_Son expedientes que están en estado "En curso" y fase "Pendiente tramitación cliente", el requerimiento de tipo "Aceptación de fecha propuesta por el IZO" no se ha realizado y se ha superado la fecha límite del requerimiento.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 7, 1, 'eu_Si se rechaza la fecha propuesta por el IZO.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 8, 2, 'eu_Son expedientes que están en estado "En curso" y fase "Pendiente tramitación cliente", el requerimiento de tipo "Aceptación de fecha propuesta por el IZO" se ha realizado pero ha sido rechazado por el cliente.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 9, 3, 'eu_Será obligatorio seleccionar un motivo de anulación de entre los tipificados.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 10, 3, 'eu_En caso de seleccionar "Otro" se requerirá como obligatorio la edición de un campo de texto libre para contemplar las aclaraciones que se crean pertinentes.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 11, 3, 'eu_El estado del expediente se actualizará a "anulado"');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 12, 3, 'eu_El gestor recibirá un email de aviso indicándole que el expediente ha sido anulado.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (6, 13, 1, 1, 'Son expedientes en los que ya se han realizado todas las requeridas para el cierre, incluida la entrega pero quedan tareas, no requeridas para el cierre, pendientes de ejecutar. Además se han revisado los datos de facturación si el expediente es "facturable"');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (7, 14, 1, 1, 'Son expedientes en los que ya se han realizado todas las tareas planificadas');
SET DEFINE ON;

COMMIT;
/
exit;