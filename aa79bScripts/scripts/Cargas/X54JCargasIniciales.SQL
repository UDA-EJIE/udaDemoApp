

Delete from X54J43T00 where CAPLIC_043 = 7;
Delete from X54J42T00 where CAPLIC_042 = 7;
Delete from X54J41T00 where CAPLIC_041 = 7;
Delete from X54J40T00 where CAPLIC_040 = 7;

Insert into X54J40T00 (CAPLIC_040,CROL_040,DESROL_040,DEUROL_040,REQDIR_040,GESSOLAS_040,FACDIR3_040) values ('7','1','Solicitante','Eskatzailea','S','N','N');
Insert into X54J40T00 (CAPLIC_040,CROL_040,DESROL_040,DEUROL_040,REQDIR_040,GESSOLAS_040,FACDIR3_040) values ('7','2','Personal IZO','IZOko langilea','N','N','N');
Insert into X54J40T00 (CAPLIC_040,CROL_040,DESROL_040,DEUROL_040,REQDIR_040,GESSOLAS_040,FACDIR3_040) values ('7','3','Proveedor','Hornitzailea','N','N','N');

Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','1','1','Gestor de expedientes','Espediente-kudeatzailea');
Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','1','2','Gestor de facturas','Faktura-Kudeatzailea');
Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','1','3','Coordinador','Koordinatzailea');
Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','1','4','Gestor expedientes VIP','VIP espedienteen kudeatzailea');
Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','1','5','Gestor de expedientes BOPV','EHAAko espedienteen kudeatzailea');

Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','2','1','Técnico Gestor','Teknikari Kudeatzailea');
Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','2','2','Asignador','Esleitzailea');
Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','2','3','Traductor','Itzultzailea');
Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','2','4','Intérprete','Interpretea');

Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','3','1','Traductor','Itzultzailea');
Insert into X54J41T00 (CAPLIC_041,CROL_041,CPER_041,DESPER_041,DEUPER_041) values ('7','3','2','Intérprete','Interpretea');


COMMIT;