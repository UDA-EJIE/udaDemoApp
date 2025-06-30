CREATE OR REPLACE FORCE EDITIONABLE VIEW X54J.X54JAPI_CENORGS_PERSONA (
    DNI,
    CENTRO_ORGANICO,
    DESCRIPCION_CASTELLANO_AMP,
    DESCRIPCION_EUSKERA_AMP
) AS
    SELECT
        vpersonas.dni DNI,
        vpersonas.centro_organico,
        descripcion_castellano_amp,
        descripcion_euskera_amp
    FROM
        vista_general
        INNER JOIN vista_organigramas ON vista_general.subcodigo = '901'
                                         AND vista_general.codigo = vista_organigramas.centro_organico
        INNER JOIN vista_personas vpersonas ON vpersonas.centro_organico = vista_organigramas.centro_organico
                                               AND vpersonas.tipo_persona != 'E'
    WHERE
        vpersonas.dni IS NOT NULL;
/
