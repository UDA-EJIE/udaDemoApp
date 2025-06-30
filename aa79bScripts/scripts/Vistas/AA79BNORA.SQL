CREATE OR REPLACE VIEW AA79BNORA                                         AS
SELECT t49.tiponora_049                                      AS TIPONORA,
  t49.cdirnora_049                                           AS CDIRNORA,
  TPAIS.DS_O                                                 AS PAIS,
  DECODE (t49.tiponora_049, 3, t49.txtprov_049, TPROV.DS_O)  AS PROVINCIA,
  DECODE (t49.tiponora_049, 3, t49.TXTCIUDAD_049, TMUN.DS_O) AS MUNICIPIO,
  DECODE (t49.tiponora_049, 3, t49.CODLOCAL_049, TLOC.DS_O)  AS LOCALIDAD,
  TPROV.ID                                                   AS CODPROVINCIA,
  TMUN.ID                                                    AS CODMUNICIPIO,
  TPORT.codigo_postal                                        AS CODPOSTAL,
  -- CALLE
  (
  CASE t49.tiponora_049
    WHEN '0'
    THEN NVL(tcal.ds_e, TCAL.DS_O)
    WHEN '-1'
    THEN t49.aprox_049
    ELSE t49.txtcalle_049
  END) "CALLE",
  -- PORTAL
  (
  CASE t49.tiponora_049
    WHEN '-1'
    THEN ''
    ELSE tport.numero
      || NVL2(tport.bis, ' Bis: '
      || tport.bis, '')
      || NVL2(tport.bloque, ' Bloq.: '
      || tport.bloque, '')
      || NVL2(tport.acepcion, ' '
      || tport.acepcion, '')
  END) "PORTAL",
  -- ESCALERA
  (
  CASE t49.tiponora_049
    WHEN '-1'
    THEN ''
    ELSE NVL2(t49.ESCALER_049, ' '
      || t49.ESCALER_049, '')
  END) "ESCALERA",
  -- PISO
  (
  CASE t49.tiponora_049
    WHEN '-1'
    THEN ''
    ELSE NVL2(t49.PISO_049, ' '
      || t49.PISO_049, '')
  END) "PISO",
  -- MANO
  (
  CASE t49.tiponora_049
    WHEN '-1'
    THEN ''
    ELSE NVL2(t49.MANO_049, ' '
      || t49.MANO_049, '')
  END) "MANO",
  -- PUERTA
  (
  CASE t49.tiponora_049
    WHEN '-1'
    THEN ''
    ELSE NVL2(t49.PUERTA_049, ' '
      || t49.PUERTA_049, '')
  END) "PUERTA"
FROM AA79B49S01 t49
LEFT JOIN AA79portal tport
ON t49.codnora_049 = tport.ID
LEFT JOIN AA79calle tcal
ON tport.calle_id = tcal.ID
LEFT JOIN AA79localidad tloc
ON t49.CODLOCAL_049 = tloc.ID
LEFT JOIN AA79pais tpais
ON t49.codpais_049 = tpais.ID
LEFT JOIN AA79provincia tprov
ON DECODE (t49.tiponora_049, 0, tcal.provincia_id, tloc.provincia_id) = tprov.ID
LEFT JOIN AA79municipio tmun
ON DECODE (t49.tiponora_049, 0, tcal.provincia_id, tloc.provincia_id ) = tmun.provincia_id
AND DECODE (t49.tiponora_049, 0, tcal.municipio_id, tloc.municipio_id) = tmun.ID
ORDER BY CDIRNORA DESC;

/