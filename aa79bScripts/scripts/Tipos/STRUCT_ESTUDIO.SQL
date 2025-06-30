create or replace
TYPE        STRUCT_ESTUDIO                                          AS OBJECT (
  RECURSO VARCHAR2(1),
  NUM_RECURSOS NUMBER(3),
  COSTE_HORAS VARCHAR2(10),
  COSTE_HORAS_RECURSO VARCHAR2(10)
);
/