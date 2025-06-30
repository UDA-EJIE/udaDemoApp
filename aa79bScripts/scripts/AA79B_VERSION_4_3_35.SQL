DEFINE DIR_SCRIPTS = '/aplic/aa79b/scripts';

-- ANADIR LOS CAMPO PARA EL ENVIO DEL EMAIL EN LAS TABLAS AA79B41T00 Y AA79B06T00
set define off;
INSERT INTO AA79B41T00 (ID_041, DESC_ES_041, DESC_EU_041, DESTINATARIO_041, ESTADO_041) VALUES (39, 'Nuevo expediente de interpretación', 'Interpretazio espediente berria ', 2, 'A');
INSERT INTO AA79B06T00 (ID_006, TEXTO_MAIL_ES_006, TEXTO_MAIL_EU_006, ASUNTO_ES_006, ASUNTO_EU_006) VALUES (39, '<p>Se ha creado un nuevo expediente de interpretación <strong>{{numExp}}</strong></p>', '<p>Interpretazio espediente berri bat sortu da <strong>{{numExp}}</strong></p>', 'Nuevo expediente de interpretación {{numExp}} ', 'Interpretazio espediente berria {{numExp}} ');
set define on;


-- MODIFICAR TAMANO EMAIL Y ANADIR LOS EMAILS EN LA TABLA AA79B07T00
ALTER TABLE AA79B07T00 MODIFY DIR_EMAIL_007 VARCHAR2(500);
INSERT INTO AA79B07T00 (ID_007,DIR_EMAIL_007) VALUES(2, 'o-lertxundi@euskadi.eus,pruebaadmin1@xxx.com,pruebaadmin2@xxx.com');


-- Transformacion del campo OBSV_0B2 de VARCHAR2 a CLOB:
-- 1. Añadir una nueva columna temporal de tipo CLOB
ALTER TABLE AA79B.AA79BB3T00 ADD (OBSV_0B2_CLOB CLOB);
-- 2. Copiar los datos de VARCHAR2 a CLOB
UPDATE AA79B.AA79BB3T00
SET OBSV_0B2_CLOB = TO_CLOB(OBSV_0B2);
-- 3. Confirmar que los datos fueron copiados correctamente 
-- Puedes hacer un conteo simple para validar
SELECT COUNT(*) FROM AA79B.AA79BB3T00 WHERE OBSV_0B2 IS NOT NULL AND OBSV_0B2_CLOB IS NULL;
-- Si el resultado del SELECT anterior es 0, es correcto.
-- 4. Eliminar la columna original VARCHAR2
ALTER TABLE AA79B.AA79BB3T00 DROP COLUMN OBSV_0B2;
-- 5. Renombrar la nueva columna CLOB para que tenga el nombre original
ALTER TABLE AA79B.AA79BB3T00 RENAME COLUMN OBSV_0B2_CLOB TO OBSV_0B2;

COMMIT;
EXIT;