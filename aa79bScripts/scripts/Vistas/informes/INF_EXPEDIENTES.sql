 CREATE OR REPLACE FORCE EDITIONABLE VIEW "AA79B"."INF_EXPEDIENTES" (
  "ESP_ZK", "URTEA", "ESPEDIENTEA", "EHAA", "ESPEDIENTE_MOTA", "COD_ENTIDAD", "TIPO", "ESKATZAILEA", "HITZ_KOPURUA_IZO",
  "TARIFA_HITZAK", "AMAITUTA", "DOCUMENTU_MOTA", "ITZULPEN_MOTA", "ZENBATEKOA_BEZ_BARNE", "DESKRIBAPENA", "SOLASKIDEA",
  "HIZKUNTZA", "ALTA_DATA", "IZO_AMAIERA_DATA", "BENETAKO_ENTREGA_DATA", "LIDFAKTURA") AS 
  SELECT
    num_exp_051,
    anyo_051,
    substr(anyo_051, 3, 4)
    || '/'
    || lpad(num_exp_051, 6, '0'),
    decode(ind_publicar_bopv_053, 'S', 'Bai', 'Ez'),
    decode(id_tipo_expediente_051, 'T', 'ITZULPENA', 'R', 'BERRIKUSKETA',
           'INTERPRETAZIOA'),
    codigo,
    tipo,
    TRIM(t71solici.apel1_077)
    || ' '
    || TRIM(t71solici.apel2_077)
    || ', '
    || TRIM(t71solici.nombre_077),
    num_total_pal_izo_053,
    tarifa_pal_097,
    decode(id_estado_exp_059, 7, 'Bai', 'Ez'),
    tdocs.docs,
    desc_relevancia_eu_010,
    importe_total_097,
    titulo_051,
    decode(t71solaski.dni_077, NULL, NULL, TRIM(t71solaski.apel1_077)
                                           || ' '
                                           || TRIM(t71solaski.apel2_077)
                                           || ', '
                                           || TRIM(t71solaski.nombre_077)),
    id_idioma_053, /*HIZKUNTZA_XEDEA*/
    fecha_alta_051, /*Eskaeraren data eta ordua*/
    fecha_final_izo_053, /*IZO entrega Data/Ordua*/
    fecha_entrega_real_053, /*Benetan entregatutako data eta ordua*/
    tfacturas.lista_facturas /*lista facturas*/
FROM
    aa79b51t00
    LEFT JOIN aa79b53t00 ON anyo_051 = anyo_053
                            AND num_exp_051 = num_exp_053
    LEFT JOIN aa79b97t00 ON anyo_051 = anyo_097
                            AND num_exp_051 = num_exp_097
    JOIN aa79b59t00 ON num_exp_051 = num_exp_059
                       AND anyo_051 = anyo_059
                       AND estado_bitacora_051 = id_estado_bitacora_059
    LEFT JOIN (
        SELECT
            num_exp_056,
            anyo_056,
            LISTAGG(desc_eu_042, ', ') WITHIN GROUP(
                ORDER BY
                    desc_eu_042
            ) AS docs
        FROM
                 aa79b56t00
            JOIN aa79b42t00 ON tipo_documento_056 = id_042
        WHERE
            clase_documento_056 IN ( 1, 2 )
        GROUP BY
            num_exp_056,
            anyo_056
    )           tdocs ON tdocs.num_exp_056 = num_exp_051
               AND tdocs.anyo_056 = anyo_051
    LEFT JOIN aa79b10t00 ON id_tipo_relevancia_010 = id_relevancia_053
    JOIN aa79b54t00 ON anyo_051 = anyo_054
                       AND num_exp_051 = num_exp_054
    JOIN x54japi_entidades_solic ON tipo = tipo_entidad_054
                                    AND codigo = id_entidad_054
    JOIN aa79b77t00  t71solici ON t71solici.dni_077 = dni_solicitante_054
    LEFT JOIN aa79b77t00  t71solaski ON t71solaski.dni_077 = dni_representante_054
    LEFT JOIN (
        SELECT 
            anyo_0a5,
            num_exp_0a5,
            LISTAGG(id_factura_0a4, ', ') WITHIN GROUP(
                ORDER BY
                    id_factura_0a4
            )
            lista_facturas
        FROM
            aa79ba4t00
            JOIN aa79ba5t00 ON id_factura_0a4 = id_factura_0a5
            JOIN w0512s01 ON id_liquidacion_0a4 = t12_referencia
                             AND t12_est_factura NOT IN ( 4 )
            GROUP BY anyo_0a5, num_exp_0a5 
    )           tfacturas ON tfacturas.num_exp_0a5 = num_exp_051
                   AND tfacturas.anyo_0a5 = anyo_051;
