CREATE OR REPLACE VIEW X54JAPI_SOLICITANTES AS 
SELECT
    TIPIDEN_044                                                   AS TIPIDEN ,
    PREDNI_044                                                    AS PREDNI ,
    DNI_044                                                       AS DNI ,
    SUFDNI_044                                                    AS SUFDNI ,
    NOMBRE_044                                                    AS NOMBRE ,
    APEL1_044                                                     AS APEL1 ,
    APEL2_044                                                     AS APEL2 ,
    'V'                                                           AS ESTADO ,
    ITIPENT_044                                                   AS TIPO_ENTIDAD ,
    ENTDPTO_044                                                   AS COD_ENTIDAD ,
    DENTIC_044                                                    AS DESC_ES ,
    DENTIE_044                                                    AS DESC_EU ,
    DECODE(ITIPENT_044, 'B', CIF_001, 'E', CIF_023, 'L', CIF_037) AS CIF ,
    FACTURABLE_036                                                AS FACTURABLE ,
    IVA_036                                                       AS IVA ,
    CDIRNORA_044                                                  AS CDIRNORA ,
    NVL2(A.CPER_043, 'S', 'N')                                       GESTOR_EXPEDIENTES ,
    NVL2(B.CPER_043, 'S', 'N')                                       GESTOR_FACTURAS ,
    NVL2(C.CPER_043, 'S', 'N')                                       COORDINADOR ,
    NVL2(D.CPER_043, 'S', 'N')                                       GESTOR_EXPEDIENTES_VIP ,
    NVL2(E.CPER_043, 'S', 'N')                                       GESTOR_EXPEDIENTES_BOPV ,
    NVL2(F.CPER_043, 'S', 'N')                                       GESTOR_EXPEDIENTES_TRAMITAGUNE 
FROM
    X54J44T00 T44
JOIN
    X54J42T00 T42
ON
    CAPLIC_042 = CAPLIC_044
AND DNI_042 = DNI_044
JOIN
    X54J40T00 T40
ON
    T40.CAPLIC_040 = T42.CAPLIC_042
AND T40.CROL_040 = T42.CROL_042
JOIN
    X54JAPI_TRABAJADORES_AA79B API
ON
    T42.DNI_042 = API.DNI
AND T42.CAPLIC_042 = API.COD_APLIC
LEFT JOIN
    X54J43T00 A
ON
    A.DNI_043 = DNI_042
AND A.CAPLIC_043 = CAPLIC_042
AND A.CROL_043 = CROL_042
AND A.CPER_043 = 1
LEFT JOIN
    X54J43T00 B
ON
    B.DNI_043 = DNI_042
AND B.CAPLIC_043 = CAPLIC_042
AND B.CROL_043 = CROL_042
AND B.CPER_043 = 2
LEFT JOIN
    X54J43T00 C
ON
    C.DNI_043 = DNI_042
AND C.CAPLIC_043 = CAPLIC_042
AND C.CROL_043 = CROL_042
AND C.CPER_043 = 3
LEFT JOIN
    X54J43T00 D
ON
    D.DNI_043 = DNI_042
AND D.CAPLIC_043 = CAPLIC_042
AND D.CROL_043 = CROL_042
AND D.CPER_043 = 4
LEFT JOIN
    X54J43T00 E
ON
    E.DNI_043 = DNI_042
AND E.CAPLIC_043 = CAPLIC_042
AND E.CROL_043 = CROL_042
AND E.CPER_043 = 5
LEFT JOIN
    X54J43T00 F
ON
    F.DNI_043 = DNI_042
AND F.CAPLIC_043 = CAPLIC_042
AND F.CROL_043 = CROL_042
AND F.CPER_043 = 6
LEFT JOIN
    X54J01T00
ON
    ITIPENT_044 = 'B'
AND ENTDPTO_044 = CENTIDAD_001
LEFT JOIN
    X54J23T00
ON
    ITIPENT_044 = 'E'
AND ENTDPTO_044 = CDEPARTAMENTOS_023
LEFT JOIN
    X54J37T00
ON
    ITIPENT_044 = 'L'
AND ENTDPTO_044 = CEMPRESA_037
LEFT JOIN
    X54J36T00
ON
    CAPLIC_036 = CAPLIC_044
AND ITIPENT_036 = ITIPENT_044
AND CODENT_036 = ENTDPTO_044
WHERE
    CAPLIC_044 = 7
AND ENTDPTO_044 IS NOT NULL
AND CROL_042 = 1
AND
    CASE
        WHEN T40.VINCULADO_PUESTO_040 = 'N'
        THEN 'V'
        WHEN T44.ITIPENT_044=API.TIPO_TRABAJADOR
        AND NVL(T44.ENTDPTO_044, -1) = NVL(API.ENTDPTO,-1)
        AND NVL(T44.CENORG_044, -1) = NVL(API.CENTRO_ORGANICO, -1)
        AND NVL(T44.COD_EST_044, -1) = NVL(API.COD_ESTRUCTURA, -1)
        AND NVL(T44.CENTRO_044, -1) = NVL(API.CODCENTRO, -1)
        THEN 'V'
        ELSE 'P'
    END = 'V'
AND
    CASE
        WHEN GESSOLAS_040 = 'N'
        THEN 1
        WHEN GESSOLAS_040 = 'S'
        AND API.TIPO_TRABAJADOR IN ('B',
                                    'E')
        THEN 1
        ELSE 0
    END = 1;
/