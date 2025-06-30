CREATE OR REPLACE VIEW IDABA AS
    WITH DOCUMENTOS AS ( SELECT ANYO, MES, COUNT(1) AS NUM_DOCUMENTOS
                           FROM ( SELECT TO_CHAR(DATA, 'YYYY') ANYO
                                     , TO_CHAR(DATA, 'MM') MES
                                  FROM R21B.R2100T00
                                 WHERE DATA IS NOT NULL)
                          GROUP BY ANYO, MES)
       , CONSULTAS AS ( SELECT ANYO
                           , MES
                           , SUM(KONTSULTAK + KONTSULTAK_ANONIMOAK) AS NUM_CONSULTAS
                           , SUM(KONTSULTAK_ANONIMOAK) AS NUM_CONSULTAS_ANONIMAS
                           , SUM(KONTSULTAK) AS NUM_CONSULTAS_CON_PERMISOS
                           , SUM(ESPORTAZIOAK) AS MEMORIAS_TRADUC_EXPORT
                        FROM ( SELECT TO_CHAR(DATA, 'YYYY') ANYO
                                  , TO_CHAR(DATA, 'MM') MES
                                  , NVL(KONTSULTAK, 0) AS KONTSULTAK
                                  , NVL(KONTSULTAK_ANONIMOAK, 0) AS KONTSULTAK_ANONIMOAK
                                  , NVL(ESPORTAZIOAK, 0) AS ESPORTAZIOAK
                               FROM R21B.R2108T00)
                       GROUP BY ANYO, MES)
    SELECT NVL(D.ANYO, C.ANYO) ANYO
         , NVL(D.MES, C.MES) MES
         , NVL(D.NUM_DOCUMENTOS, 0) NUM_DOCUMENTOS
         , NVL(C.NUM_CONSULTAS, 0) NUM_CONSULTAS
         , NVL(C.NUM_CONSULTAS_ANONIMAS, 0) NUM_CONSULTAS_ANONIMAS
         , NVL(C.NUM_CONSULTAS_CON_PERMISOS, 0) NUM_CONSULTAS_CON_PERMISOS
         , NVL(C.MEMORIAS_TRADUC_EXPORT, 0) MEMORIAS_TRADUC_EXPORT
      FROM DOCUMENTOS D
      FULL OUTER JOIN CONSULTAS C ON D.ANYO = C.ANYO AND D.MES = C.MES;