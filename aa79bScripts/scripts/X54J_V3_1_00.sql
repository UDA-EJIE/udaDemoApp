-- Borramos los permisos de receptores
Delete from X54J43T00 where CAPLIC_043 = 7 and CROL_043=4;
Delete from X54J42T00 where CAPLIC_042 = 7 and CROL_042=4;
Delete from X54J40T00 where CAPLIC_040 = 7 and CROL_040=4;

-- Borramos la vista de receptores
DROP VIEW X54JAPI_RECEPTORES_AUTORIZADOS;

EXIT;
