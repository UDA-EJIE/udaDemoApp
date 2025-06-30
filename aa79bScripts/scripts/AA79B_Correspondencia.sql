-- INsertamos el solicitante ficticio
INSERT into AA79B77T00 (DNI_077, TIPIDEN_077, PREDNI_077, SUFDNI_077, NOMBRE_077, APEL1_077, APEL2_077) 
values ('00000000', 1, null,'T', 'Solicitante', 'Histórico', 'R75B');

--  TABLA DE ASOCIACION ENTIDADES R75 X54
CREATE TABLE CODR75X54J (
    ID_R75 NUMBER(8) NOT NULL,
    TIPO_X54 VARCHAR2(1) NOT NULL,
    ID_X54 NUMBER(8) NOT NULL
)TABLESPACE  AA7901E00;

ALTER TABLE CODR75X54J ADD CONSTRAINT PK_CODR75X54J PRIMARY KEY (ID_R75);
ALTER INDEX PK_CODR75X54J REBUILD TABLESPACE AA7902E00;


-- INSERT DE DATOS
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (3,'E',19);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (12,'E',35);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (15,'B',466);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (23,'B',539);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (47,'E',18);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (58,'E',12);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (79,'B',254);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (122,'B',272);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (135,'E',46);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (158,'B',277);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (160,'L',1);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (175,'B',568);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (210,'L',4);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (213,'L',5);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (218,'L',6);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (219,'E',82);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (252,'B',521);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (253,'L',3);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (297,'L',7);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (304,'L',8);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (310,'L',9);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (315,'B',547);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (328,'B',249);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (384,'L',10);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (385,'L',11);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (392,'L',2);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (425,'B',309);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (448,'E',20);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (450,'B',551);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (452,'B',247);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (460,'B',252);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (467,'B',251);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (495,'E',95);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (499,'B',349);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (505,'L',12);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (506,'L',13);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (513,'E',13);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (532,'B',307);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (533,'B',567);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (544,'E',26);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (559,'B',423);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (561,'L',14);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (569,'B',292);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (574,'B',366);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (575,'B',250);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (582,'E',97);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (583,'E',94);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (584,'L',15);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1006,'E',8);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1012,'B',565);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1018,'E',12);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1021,'E',96);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1037,'B',226);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1071,'E',98);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1076,'L',16);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1077,'L',17);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1078,'L',18);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1080,'L',19);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1081,'L',20);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1084,'E',53);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1087,'E',63);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1088,'E',64);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1089,'E',65);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1092,'B',566);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1095,'B',429);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1096,'E',39);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1097,'E',51);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1100,'E',56);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1101,'E',57);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1103,'L',21);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1104,'B',564);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1105,'B',519);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1107,'L',22);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (1109,'B',563);
Insert into CODR75X54J ( ID_R75, TIPO_X54, ID_X54) values (null,'B',570);
/
