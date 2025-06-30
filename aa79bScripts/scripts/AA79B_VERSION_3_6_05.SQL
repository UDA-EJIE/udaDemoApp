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
@@&DIR_SCRIPTS/AA79B_PreMigracion.sql;
/

SET DEFINE OFF;
delete from AA79BA9T00;
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (2, 3, 1, 1, 'Espedientea egoera eta fase honetan zegoen unera itzuliko da. Horrela, hautatutako espedientearen xehetasunei buruzko pantailaren bidez, eskaeraren jatorrizko datuak berrikusi ahal izango dira, espedientea espediente-motarekin, xede-hizkuntzarekin, garrantziarekin, dokumentazioarekin eta presakotasunarekin bat datorrela ziurtatzeko; gero, espedientearen izapideekin aurrera egiteko eta plangintza eta esleipena prestatzeko behar den informazioa bete beharko da, eta, hala badagokio, bezeroari datuak edo dokumentazioa zuzentzeko egin behar zaion errekerimendua eta ondorengo azterketa kudeatzeko datuak ere emango dira.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (3, 9, 1, 1, 'Espedientea egoera eta fase honetan zegoen unean bezala lan egingo da.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (3, 9, 2, 1, 'Espedientea ixteko eskatzen diren atazak bete gabe dituzten espedienteetarako sortzen den egoera/fasea.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (6, 10, 1, 1, 'Bezeroari entregatzeko ataza amaitutakoan, espedientea "fakturagarri" zegoen egoerara itzuliko da.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 1, 1, 'Espedientea baztertu ahal izango da, egoki iritziz gero.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 2, 1, 'Tipifikatutakoen artetik, baztertzeko arrazoi bat hautatu beharko da nahitaez.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 3, 1, '"Beste bat" hautatuz gero, testu-eremu libre bat editatu beharko da, esleitzaileak egokitzat jotzen dituen argibideak jasotzeko.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 4, 1, 'Espedientea "baztertua" egoerara eguneratuko da.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (4, 11, 5, 1, 'Eskatzaileari mezu elektronikoz jakinaraziko zaio espedientea baztertu egin dela eta izapidearen xehetasunak web-aplikazioan kontsulta ditzakeela.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 1, 1, 'Bezeroak epe barruan aurkezten ez badu IZOk eskaturiko zuzenketa');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 2, 2, '"Aztertzen" egoeran eta "Bezeroak izapidetzeko zain, zuzenketagatik" fasean dauden espedienteak dira; errekerimendua ez da gauzatu eta errekerimenduaren epemuga gainditu da.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 3, 1, 'Aurrekontuari buruzko erantzuna epe barruan izapidetzen ez bada.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 4, 2, '"Abian" egoeran eta "Bezeroak izapidetzeko zain" fasean dauden espedienteak dira; "Aurrekontua onartzea" edo "Aurrekontua eta IZOk proposatutako data onartzea" errekerimendua ez da gauzatu eta errekerimenduaren epemuga gainditu da.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 5, 1, 'IZOk proposatutako datari buruzko erantzuna epe barruan izapidetzen ez bada.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 6, 2, '"Abian" egoeran eta "Bezeroak izapidetzeko zain" fasean dauden espedienteak dira; "IZOk proposatutako data onartzea" errekerimendua ez da gauzatu eta errekerimenduaren epemuga gainditu da.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 7, 1, 'IZOk proposatutako data baztertzen bada.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 8, 2, '"Abian" egoeran eta "Bezeroak izapidetzeko zain" fasean dauden espedienteak dira; "IZOk proposatutako data onartzea" errekerimendua gauzatu da, baina bezeroak atzera bota du.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 9, 3, 'Tipifikatutakoen artetik, ezeztatzeko arrazoi bat hautatu beharko da nahitaez.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 10, 3, '"Beste bat" hautatuz gero, testu-eremu libre bat editatu beharko da, egokitzat jotzen diren argibideak jasotzeko.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 11, 3, 'Espedientea "ezeztatua" egoerara eguneratuko da.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (5, 12, 12, 3, 'Kudeatzaileari mezu elektronikoz jakinaraziko zaio espedientea ezeztatu dutela.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (6, 13, 1, 1, 'Espedienteotan, ixteko eskatzen diren ataza guztiak bete dira, entrega barne, baina espedientea ixteko eskatzen ez diren beste ataza batzuk gelditzen dira betetzeko. Gainera, espedientearen fakturazio-datuak berrikusi dira, espedientea "fakturagarria" bada.');
INSERT INTO  AA79BA9T00 (ID_ESTADO_EXP_0A9, ID_FASE_EXP_0A9, ORDEN_0A9, NIVEL_0A9, TEXTO_0A9)
VALUES (7, 14, 1, 1, 'Planifikatutako ataza guztiak bete dituzten espedienteak dira.');
SET DEFINE ON;

COMMIT;
/

exit;