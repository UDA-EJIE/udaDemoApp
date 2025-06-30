-- CREAR REGISTROS DE ENVIO DE EMAIL CAMBIO FECHA DE FINALIZACION IZO EXPEDIENTE
set define off;

INSERT INTO AA79B41T00 (ID_041, DESC_ES_041, DESC_EU_041, DESTINATARIO_041, ESTADO_041) VALUES (36,'Notificación cambio de fecha de finalización IZO','IZO amaiera data aldaketa jakinaraztea', 1,'A');

INSERT INTO AA79B06T00 (ID_006, TEXTO_MAIL_ES_006, TEXTO_MAIL_EU_006, ASUNTO_ES_006, ASUNTO_EU_006) VALUES (36,'<p>-- Mediante este mensaje se informa de que se ha cambiado la fecha de finalizaci&oacute;n IZO del siguiente expediente: --</p>','<p>-- Mezu honen bidez jakinarazi nahi dizugu ondorengo espedientearen IZO amaiera-data aldatu dela --</p>',' Se ha cambiado la fecha de finalización IZO de un expediente ','Espediente baten IZO amaiera-data aldatu da');

set define on;

EXIT;