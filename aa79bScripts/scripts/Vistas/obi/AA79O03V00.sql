CREATE OR REPLACE VIEW AA79O03V00 AS
    SELECT 'I' COD_TIPO_EXP
         , 'Interpretazioa' DESC_TIPO_EXP
      FROM DUAL
    UNION ALL
    SELECT 'T'
         , 'Itzulpena'
      FROM DUAL
    UNION ALL
    SELECT 'R'
         , 'Berrikusketa'
      FROM DUAL;