create or replace FUNCTION crear_tareas_relacionadas (
    idtarea   IN   NUMBER,
    ip        IN   VARCHAR2,
    usuario   IN   VARCHAR2
) RETURN NUMBER IS

    TYPE r_reg_tarea IS RECORD (
        id_tipo_tarea                NUMBER(2),
        ind_facturable               VARCHAR2(1),
        recurso_asignacion           VARCHAR2(1),
        num_exp                      NUMBER(6),
        anyo                         NUMBER(4),
        orden                        NUMBER(2),
        fecha_inicio_081             DATE,
        fecha_fin_081                DATE,
        horas_previstas_081          VARCHAR2(10),
        num_total_pal_izo_053        NUMBER(6, 0),
        ind_tramitacion_rapida_029 VARCHAR2(1),
        ind_no_conformidad_081       VARCHAR2(1),
        id_tarea_rel_081			NUMBER(20),
        ind_req_revision_081             VARCHAR2(1)
    );
    tarea                          r_reg_tarea;
    interpretar                    NUMBER(2) := 8;
    revisar                        NUMBER(2) := 4;
    traducir                       NUMBER(2) := 2;
    intro_pago_prov_inter          NUMBER(2) := 12;
    revision_pago_prov             NUMBER(2) := 11;
    revision_entrega_traduccion               NUMBER(2) := 19;
    revision_traduccion            NUMBER(2) := 21;
    traduccion_entrega_cliente     NUMBER(2) := 22;
    revision_entrega_cliente     NUMBER(2) := 20;
    no_conformidad_traductor        NUMBER(2) := 23;
    revision_traduccion_interna     NUMBER(2) := 24;
    si                             VARCHAR2(1) := 'S';
    recurso_externo                VARCHAR2(1) := 'P';
    recurso_interno                VARCHAR2(1) := 'I';
    estado_tarea                   NUMBER(1);
    estado_asignacion_tarea        NUMBER(1) := 1;
    estado_pdte_aceptacion_tarea   NUMBER(1) := 2;
    estado_aceptacion_tarea        NUMBER(1) := 3;
    tarea_pendiente_ejecucion      NUMBER(1) := 1;
    nuevo_id_tarea                 NUMBER(20) := 0;
    orden                          NUMBER(2) := 0;
    dni_recurso                    VARCHAR2(10);
    dni_recurso_estudio                    VARCHAR2(10);
    fecha_ini_nueva_tarea          DATE;
    fecha_fin_nueva_tarea          DATE;
    fecha_aceptacion_tarea         DATE;
    contador                       NUMBER(2) := 0;
    contador_entrega               NUMBER(2) := 0;
    ini_observ                     VARCHAR2(32) := 'Ataza hauek sortu dira: '
                               || chr(13)
                               || 'id = ';
    ini_observ_eliminar            VARCHAR2(32) := 'Ataza hauek ezabatu dira: '
                                        || chr(13)
                                        || 'id = ';
    observ                         VARCHAR2(15) := ', Ataza mota = ';
    fin_observ                     VARCHAR2(2) := chr(13);
    observaciones                  VARCHAR2(4000) := '';
    id_estado_bitacora             NUMBER(3) := 0;
    desc_tipo_tarea                VARCHAR2(150) := '';
    charDocsTarea					VARCHAR2(500):= '';
    tiempoPrevistoTarea				VARCHAR2(10):= '00:00';
	horasPrevistasTarea              NUMBER(12,2):=0;
    HORAS_TIEMPO_EXTRA_GESTION      NUMBER(12,2):=0;
	TIEMPO_EXTRA_GESTION			VARCHAR2(10):= '00:00';
	IND_TIEMPO_GESTION              VARCHAR2(1);

	tipo_revision_sencilla 			NUMBER(2):= 1;

--Párametros del envío del mail
    id_proceso_email               NUMBER(20) := 0;
    num_email                      NUMBER(2) := 1;
    tipo_aviso                     NUMBER(2) := 5;
    email_destinatario             VARCHAR2(256);
    id_tipo_expediente             VARCHAR2(1);
    titulo_expediente              VARCHAR2(150);
    origen                         VARCHAR2(1);
    aplic_origen                   VARCHAR2(6);
    retorno                        NUMBER(20);
    tarea_entrega                  NUMBER(20);
    lim_palabras                   NUMBER(6);

    id_tarea_rel_aux				NUMBER(20);
BEGIN

	-- obtenemos el limite de palabras IZO  a partir del cual se realiza presupuesto y proyecto trados
    SELECT pal_trados_003 INTO lim_palabras FROM aa79b03t00 WHERE id_003 = 0;

 	-- OBTENEMOS LOS DATOS DE LA TAREA
    SELECT
        id_tipo_tarea_081, ind_facturable_081, recurso_asignacion_081, num_exp_081, anyo_081, orden_081, fecha_inicio_081, fecha_fin_081,
        horas_previstas_081, num_total_pal_izo_053, ind_tramitacion_rapida_029, ind_no_conformidad_081, id_tarea_rel_081, ind_req_revision_081 INTO tarea
    FROM aa79b81t00   t1 LEFT JOIN aa79b53s01   r1 ON t1.anyo_081 = r1.anyo_053 AND t1.num_exp_081 = r1.num_exp_053
        LEFT JOIN aa79b29s01   t29 ON t1.id_lote_081 = t29.id_lote_029
    WHERE
        id_tarea_081 = idtarea;

	-- OBTENEMOS DATOS DEL EXPEDIENTE DE LA TAREA
    SELECT
        id_tipo_expediente_051, origen_051, aplic_origen_051, dni_tecnico_051, titulo_051 INTO
        id_tipo_expediente, origen, aplic_origen, dni_recurso_estudio, titulo_expediente
    FROM aa79b51t00 WHERE anyo_051 = tarea.anyo AND num_exp_051 = tarea.num_exp;

    -- OBTENEMOS EL EMAIL DEL USUARIO QUE REALIZÓ EL ESTUDIO.
    BEGIN
        SELECT email_031 INTO email_destinatario FROM x54japi_datos_contacto WHERE dni_031 = dni_recurso_estudio;
    	EXCEPTION WHEN no_data_found THEN email_destinatario := '';
    END;

    -- SELECT ESTADO BITACORA ACTUAL
    SELECT MAX(id_estado_bitacora_059) INTO id_estado_bitacora FROM aa79b59t00 WHERE anyo_059 = tarea.anyo AND num_exp_059 = tarea.num_exp;

    -- OBtenemos el tiempo de gestión extra
    SELECT TIEMPO_EXTRA_GESTION_002 INTO TIEMPO_EXTRA_GESTION FROM AA79B02S02 WHERE ID_002 = 0;

    -- TAREA INTERPRETAR
    IF ( tarea.id_tipo_tarea = interpretar ) THEN
        IF ( tarea.recurso_asignacion = recurso_externo ) THEN
            IF ( tarea.ind_facturable = si ) THEN
            	-- COMPROBAMOS SI EXISTE LA TAREA DE REVISION DE PAGO
                SELECT COUNT(1) INTO contador FROM aa79b81t00 WHERE id_tipo_tarea_081 = intro_pago_prov_inter AND id_tarea_rel_081 = idtarea;

                IF ( contador = 0 ) THEN
	 			-- CREAMOS LA TAREA Y ARRASTRAMOS
                    SELECT aa79b81q00.NEXTVAL INTO nuevo_id_tarea FROM dual;
                    orden := tarea.orden + 1;
                    UPDATE aa79b81s01 SET orden_081 = orden_081 + 1
                    WHERE orden_081 >= orden AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp;

                    INSERT INTO aa79b81s01 (anyo_081,dni_recurso_081,estado_asignacion_081,estado_ejecucion_081,fecha_aceptacion_081,fecha_asignacion_081,
                        fecha_fin_081,fecha_inicio_081,horas_previstas_081,id_tarea_081,id_tarea_rel_081,id_requerimiento_081,ind_no_conformidad_081,
                        ind_facturable_081, id_lote_081, num_exp_081, observ_081, orden_081, recurso_asignacion_081, id_tipo_tarea_081, tipo_entidad_081,
                        id_entidad_081, dni_asignador_081, id_tipo_revision_081
                    ) VALUES (tarea.anyo, NULL, estado_asignacion_tarea, tarea_pendiente_ejecucion, NULL, NULL, NULL, NULL, '', nuevo_id_tarea, idtarea,
                        NULL, 'N', 'N', NULL, tarea.num_exp, NULL, orden, NULL, intro_pago_prov_inter, NULL, NULL, NULL, NULL);

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (id_tarea_083,id_doc_083)
                    SELECT nuevo_id_tarea, id_doc_083 FROM  aa79b83t00 WHERE id_tarea_083 = idtarea;

                    -- CREAMOS REGISTRO DE ACCION
                    SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = intro_pago_prov_inter;
                    observaciones := ini_observ || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

                    INSERT INTO aa79b43s01 (id_registro_accion_043, id_punto_menu_043, id_accion_043, ip_043, fecha_registro_043, usuario_registro_043,
                        anyo_exp_043, num_exp_043, observ_043, id_estado_bitacora_043
                        ) VALUES (aa79b43q00.NEXTVAL, 18, 1, ip, SYSDATE, usuario, tarea.anyo, tarea.num_exp, observaciones, id_estado_bitacora);

                END IF;

            ELSE
            	-- NO ES FACTURABLE, HAY QUE ELIMINAR LA DE REVISIÓN DE PAGO
                BEGIN
	                -- REGISTRO ENTRADA
                    SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = intro_pago_prov_inter;
					-- obtenemos el id_tarea de la que vamos a eliminar
                    SELECT id_tarea_081 INTO nuevo_id_tarea FROM aa79b81s01 WHERE id_tipo_tarea_081 = intro_pago_prov_inter AND id_tarea_rel_081 = idtarea;

                    observaciones := ini_observ_eliminar || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;
                    INSERT INTO aa79b43s01 (id_registro_accion_043, id_punto_menu_043, id_accion_043, ip_043, fecha_registro_043, usuario_registro_043,
                        anyo_exp_043, num_exp_043, observ_043, id_estado_bitacora_043
                    ) VALUES (aa79b43q00.NEXTVAL, 18, 2, ip, SYSDATE, usuario, tarea.anyo, tarea.num_exp, observaciones, id_estado_bitacora);
                EXCEPTION
                    WHEN no_data_found THEN
                        nuevo_id_tarea := NULL;
                END;
	 			--ELIMINAMOS LA TAREA
                DELETE aa79b81t00 WHERE id_tipo_tarea_081 = intro_pago_prov_inter AND id_tarea_rel_081 = idtarea;

            END IF;

        ELSE
        	-- SE HA ASIGNADO A IZO, HAY QUE ELIMINAR LA DE REVISIÓN DE PAGO SI EXISTE
            BEGIN
                -- REGISTRO ENTRADA
                SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = intro_pago_prov_inter;
				-- obtenemos el id_tarea de la que vamos a eliminar
                SELECT id_tarea_081 INTO nuevo_id_tarea FROM aa79b81s01 WHERE id_tipo_tarea_081 = intro_pago_prov_inter AND id_tarea_rel_081 = idtarea;
				observaciones := ini_observ_eliminar || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

                INSERT INTO aa79b43s01 (id_registro_accion_043, id_punto_menu_043, id_accion_043, ip_043, fecha_registro_043, usuario_registro_043,
                        anyo_exp_043, num_exp_043, observ_043, id_estado_bitacora_043
                ) VALUES (aa79b43q00.NEXTVAL, 18, 2, ip, SYSDATE, usuario, tarea.anyo, tarea.num_exp, observaciones, id_estado_bitacora);
            EXCEPTION
                WHEN no_data_found THEN
                    nuevo_id_tarea := NULL;
            END;
			--ELIMINAMOS LA TAREA
            DELETE aa79b81t00 WHERE id_tipo_tarea_081 = intro_pago_prov_inter AND id_tarea_rel_081 = idtarea;
        END IF;
    ELSIF ( tarea.id_tipo_tarea = revisar ) THEN
        -- TAREA REVISAR
    	-- creamos la tarea de entrega si no existe
    	 -- comprobamos si existe tarea de entrega
        SELECT COUNT(1) INTO contador_entrega FROM aa79b81t00 WHERE id_tipo_tarea_081 = revision_entrega_cliente AND anyo_081 = tarea.anyo
        AND num_exp_081 = tarea.num_exp AND ind_no_conformidad_081 = 'N';

        IF ( contador_entrega = 0 ) THEN
 			-- CREAMOS LA TAREA Y ARRASTRAMOS
            SELECT aa79b81q00.NEXTVAL INTO nuevo_id_tarea FROM dual;

            -- TENEMOS QUE CALCULAR EL TIEMPO NECESARIO PARA LA TAREA
            -- obtenemos string con los documentos concatenados.
            SELECT LISTAGG(TO_CHAR(id_doc_083), ',') WITHIN GROUP (ORDER BY id_doc_083) AS description INTO
            charDocsTarea
			FROM aa79b83t00 WHERE id_tarea_083 = idtarea GROUP BY id_tarea_083;

            -- al calcular el tiempo previsto, no tenemos en cuenta el tipo de revision ya que la tarea de revision no lo requiere
            -- para ello, ponemos el valor de tipo revision a 0
			tiempoPrevistoTarea:= CALCULO_HORAS_TRADUCCION(recurso_interno, tarea.anyo, tarea.num_exp, charDocsTarea,
   			revision_entrega_cliente,0);
   			horasPrevistasTarea:=  PARSE_HORA_MINUTOS(tiempoPrevistoTarea);

   				-- le indicamos el orden siguiente a la tarea de traducción	y sus fechas previstas
            orden := tarea.orden + 1;

            BEGIN
                SELECT nvl(fecha_fin_081, SYSDATE)
                INTO fecha_ini_nueva_tarea
                FROM aa79b81t00 WHERE anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND orden_081 = orden - 1 ORDER BY fecha_fin_081 DESC
                FETCH FIRST ROW ONLY;
            EXCEPTION
                WHEN no_data_found THEN
                    fecha_ini_nueva_tarea := SYSDATE;
            END;

            fecha_fin_nueva_tarea:= CALCULAR_FECHA_FIN_TAREA(fecha_ini_nueva_tarea, tiempoPrevistoTarea );

            id_tarea_rel_aux:= null;
            IF ( tarea.recurso_asignacion = recurso_externo ) THEN
            	id_tarea_rel_aux:= idtarea;
            END IF;

            -- ARRASTRAMOS LAS SIGUIENTES
            UPDATE aa79b81s01 SET orden_081 = orden_081 + 1 WHERE orden_081 >= orden AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp;

            INSERT INTO aa79b81s01 (anyo_081,dni_recurso_081,estado_asignacion_081,estado_ejecucion_081,fecha_aceptacion_081, fecha_asignacion_081,
                fecha_fin_081,fecha_inicio_081, horas_previstas_081,id_tarea_081,id_tarea_rel_081, id_requerimiento_081,ind_no_conformidad_081,
                ind_facturable_081,id_lote_081,num_exp_081,observ_081, orden_081,recurso_asignacion_081,id_tipo_tarea_081,tipo_entidad_081,id_entidad_081,
                dni_asignador_081,id_tipo_revision_081
                ) VALUES (tarea.anyo,NULL,estado_asignacion_tarea,tarea_pendiente_ejecucion, NULL,NULL,fecha_fin_nueva_tarea, fecha_ini_nueva_tarea,tiempoPrevistoTarea,nuevo_id_tarea,id_tarea_rel_aux,NULL,'N','N',
                NULL,tarea.num_exp,NULL,orden,NULL,revision_entrega_cliente,NULL,NULL,NULL,NULL);

            INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

            INSERT INTO aa79b83t00 (id_tarea_083, id_doc_083)
            SELECT nuevo_id_tarea,id_doc_083 FROM aa79b83t00 WHERE id_tarea_083 = idtarea;

            --Registro entrada
            SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_entrega_cliente;
            observaciones := ini_observ || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

            INSERT INTO aa79b43s01 (id_registro_accion_043, id_punto_menu_043, id_accion_043, ip_043, fecha_registro_043, usuario_registro_043,
            anyo_exp_043, num_exp_043, observ_043, id_estado_bitacora_043
            ) VALUES (aa79b43q00.NEXTVAL, 18, 1, ip, SYSDATE, usuario, tarea.anyo, tarea.num_exp, observaciones, id_estado_bitacora);

		ELSE
			-- RECUPERO SU ORDEN PARA ASIGNAR LUEGO EL QUE CORRESPONDA
            SELECT orden_081 INTO orden FROM aa79b81t00 WHERE id_tipo_tarea_081 = revision_entrega_cliente AND anyo_081 = tarea.anyo
        	AND num_exp_081 = tarea.num_exp AND ind_no_conformidad_081 = 'N';

        	id_tarea_rel_aux:= null;
            IF ( tarea.recurso_asignacion = recurso_externo ) THEN
            	id_tarea_rel_aux:= idtarea;
            END IF;

            UPDATE aa79b81t00 set ID_TAREA_REL_081= id_tarea_rel_aux where id_tipo_tarea_081 = revision_entrega_cliente AND anyo_081 = tarea.anyo
        	AND num_exp_081 = tarea.num_exp AND ind_no_conformidad_081 = 'N';
		END IF;

    	IF ( tarea.recurso_asignacion = recurso_externo ) THEN
    		IF ( tarea.ind_facturable = si ) THEN
            	-- COMPROBAMOS SI EXISTE LA TAREA DE REVISION DE PAGO
                SELECT COUNT(1) INTO contador FROM aa79b81t00 WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;
                IF ( contador = 0 ) THEN
	 				-- NO EXISTE, CREAMOS LA TAREA, LA ASIGNAMOS Y ARRASTRAMOS LAS SIGUIENTES
                    SELECT aa79b81q00.NEXTVAL INTO nuevo_id_tarea FROM dual;
                    orden := orden + 1;

					-- obtenemos string con los documentos concatenados.
	            	SELECT LISTAGG(TO_CHAR(id_doc_083), ',') WITHIN GROUP (ORDER BY id_doc_083) AS description INTO
	            	charDocsTarea
					FROM aa79b83t00 WHERE id_tarea_083 = idtarea GROUP BY id_tarea_083;

                    -- al calcular el tiempo previsto, no tenemos en cuenta el tipo de revision ya que la tarea de revision no lo requiere
                    -- para ello, ponemos el valor de tipo revision a 0
                    tiempoPrevistoTarea:= CALCULO_HORAS_TRADUCCION(recurso_interno, tarea.anyo, tarea.num_exp, charDocsTarea,
   					revision_pago_prov,0);
		   			horasPrevistasTarea:=  PARSE_HORA_MINUTOS(tiempoPrevistoTarea);

                    BEGIN
                    SELECT nvl(fecha_fin_081, SYSDATE) INTO fecha_ini_nueva_tarea
                    FROM aa79b81t00 WHERE anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND orden_081 = orden - 1 ORDER BY fecha_fin_081 DESC
                    FETCH FIRST ROW ONLY;
                    EXCEPTION
                    WHEN no_data_found THEN
                        fecha_ini_nueva_tarea := SYSDATE;
                    END;

                    fecha_fin_nueva_tarea:= CALCULAR_FECHA_FIN_TAREA(fecha_ini_nueva_tarea, tiempoPrevistoTarea );


                    -- Arrastramos el orden de las siguientes tareas.
                    UPDATE aa79b81s01 SET orden_081 = orden_081 + 1 WHERE orden_081 >= orden AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp;
					--configuramos la tarea a insertar
                    estado_tarea := estado_pdte_aceptacion_tarea;
                    fecha_aceptacion_tarea := NULL;
                    IF ( dni_recurso_estudio = usuario ) THEN
                        estado_tarea := estado_aceptacion_tarea;
                        fecha_aceptacion_tarea := SYSDATE;
                    END IF;

                    INSERT INTO aa79b81s01 (anyo_081, dni_recurso_081, estado_asignacion_081, estado_ejecucion_081, fecha_aceptacion_081, fecha_asignacion_081,
                        fecha_fin_081, fecha_inicio_081, horas_previstas_081, id_tarea_081, id_tarea_rel_081, id_requerimiento_081, ind_no_conformidad_081,
                        ind_facturable_081, id_lote_081, num_exp_081, observ_081, orden_081, recurso_asignacion_081, id_tipo_tarea_081, tipo_entidad_081,
                        id_entidad_081, dni_asignador_081, id_tipo_revision_081
                    ) VALUES ( tarea.anyo, dni_recurso_estudio, estado_tarea, tarea_pendiente_ejecucion, fecha_aceptacion_tarea, SYSDATE, fecha_fin_nueva_tarea,
                    fecha_ini_nueva_tarea, tiempoPrevistoTarea, nuevo_id_tarea, idtarea, NULL, 'N', 'N', NULL,tarea.num_exp,NULL,orden,recurso_interno,revision_pago_prov,
                        NULL, NULL, usuario, NULL);

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (id_tarea_083, id_doc_083 )
                    SELECT nuevo_id_tarea, id_doc_083 FROM aa79b83t00 WHERE id_tarea_083 = idtarea;

                    SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_pago_prov;
					-- envío email asignación
                    IF ( dni_recurso_estudio <> usuario ) THEN
                        IF ( id_proceso_email = 0 ) THEN
                            SELECT aa79bb2q00.NEXTVAL INTO id_proceso_email FROM dual;
                        END IF;

                        INSERT INTO aa79bb2t00 (id_proceso_email_0b2,num_email_0b2,id_tipo_aviso_0b2,anyo_0b2,num_exp_0b2,titulo_exp_0b2,id_tarea_0b2,
                            id_tipo_tarea_0b2,desc_tarea_es_0b2,desc_tarea_eu_0b2,dni_destinatario_0b2,email_destinatario_0b2
                            ) VALUES (id_proceso_email,num_email,tipo_aviso,tarea.anyo,tarea.num_exp,titulo_expediente,nuevo_id_tarea,tarea.id_tipo_tarea,
                            desc_tipo_tarea,desc_tipo_tarea,dni_recurso_estudio,email_destinatario);
                        num_email := num_email + 1;
                        retorno := id_proceso_email;
                    ELSE
                        retorno := NULL;
                    END IF;

                    -- REGISTRO DE ENTRADA
                    observaciones := ini_observ || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;
                    INSERT INTO aa79b43s01 (id_registro_accion_043,id_punto_menu_043,id_accion_043,ip_043,fecha_registro_043,usuario_registro_043,
                        anyo_exp_043, num_exp_043,observ_043, id_estado_bitacora_043
                        ) VALUES (aa79b43q00.NEXTVAL,18,1,ip,SYSDATE,usuario,tarea.anyo,tarea.num_exp,observaciones,id_estado_bitacora);
                END IF;

            ELSE
            	-- tarea de revisión no facturable, borraremos una posible de revisión de datos de pago
                BEGIN
                    SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_pago_prov;
					-- Recuperamos el id de tarea de la de revisión de datos de pago
                    SELECT id_tarea_081 INTO nuevo_id_tarea FROM aa79b81s01 WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;
					-- Registro de entrada
                    observaciones := ini_observ_eliminar || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;
                    INSERT INTO aa79b43s01 (id_registro_accion_043, id_punto_menu_043, id_accion_043, ip_043, fecha_registro_043, usuario_registro_043,
                        anyo_exp_043, num_exp_043, observ_043, id_estado_bitacora_043
                        ) VALUES ( aa79b43q00.NEXTVAL, 18,2,ip,SYSDATE,usuario,tarea.anyo,tarea.num_exp,observaciones,id_estado_bitacora);
                EXCEPTION
                    WHEN no_data_found THEN
                        nuevo_id_tarea := NULL;
                END;
	 			--ELIMINAMOS LA TAREA
                DELETE aa79b81t00 WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;
            END IF;
        ELSE
            -- LA tarea esta asiganda al IZO, borramos una posible revisión de datos de pago
        	BEGIN
                 SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_pago_prov;
				-- Recuperamos el id de tarea de la de revisión de datos de pago
                SELECT id_tarea_081 INTO nuevo_id_tarea FROM aa79b81s01 WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;
                -- Registro de entrada
                observaciones := ini_observ_eliminar || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;
                INSERT INTO aa79b43s01 (id_registro_accion_043, id_punto_menu_043, id_accion_043, ip_043, fecha_registro_043, usuario_registro_043,
                    anyo_exp_043, num_exp_043, observ_043, id_estado_bitacora_043
                    ) VALUES ( aa79b43q00.NEXTVAL, 18,2,ip,SYSDATE,usuario,tarea.anyo,tarea.num_exp,observaciones,id_estado_bitacora);
            EXCEPTION
                WHEN no_data_found THEN
                    nuevo_id_tarea := NULL;
            END;
			--ELIMINAMOS LA TAREA
            DELETE aa79b81t00 WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;
        END IF;
    ELSIF ( tarea.id_tipo_tarea = traducir ) THEN

    	-- TAREA TRADUCIR
        IF ( tarea.recurso_asignacion = recurso_externo ) THEN

            -- Asignada fuera
            -- comprobamos si existe la de revisión de la traducción asociada
        	SELECT COUNT(1) INTO contador FROM  aa79b81t00 WHERE id_tipo_tarea_081 = revision_traduccion AND id_tarea_rel_081 = idtarea;
            IF ( contador = 0 ) THEN

 			-- NO EXISTE, CREAMOS LA TAREA Y ARRASTRAMOS
                SELECT aa79b81q00.NEXTVAL INTO nuevo_id_tarea FROM dual;
                -- TENEMOS QUE CALCULAR EL TIEMPO NECESARIO PARA LA TAREA
                -- obtenemos string con los documentos concatenados.
                SELECT LISTAGG(TO_CHAR(id_doc_083), ',') WITHIN GROUP (ORDER BY id_doc_083) AS description INTO
                charDocsTarea
				FROM aa79b83t00 WHERE id_tarea_083 = idtarea GROUP BY id_tarea_083;

                -- al calcular el tiempo previsto, no tenemos en cuenta el tipo de revision ya que todavia no ha sido seleccionado
                -- para ello, ponemos el valor de tipo revision a 0
                tiempoPrevistoTarea:= CALCULO_HORAS_TRADUCCION(recurso_interno, tarea.anyo, tarea.num_exp, charDocsTarea, revision_traduccion,0);
		   		horasPrevistasTarea:=  PARSE_HORA_MINUTOS(tiempoPrevistoTarea);

                -- le indicamos el orden siguiente a la tarea de traducción	y sus fechas previstas
			     orden := tarea.orden + 1;
                BEGIN
                SELECT nvl(fecha_fin_081, SYSDATE)
                INTO fecha_ini_nueva_tarea
                FROM aa79b81t00 WHERE anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND orden_081 = orden - 1 ORDER BY fecha_fin_081 DESC
                FETCH FIRST ROW ONLY;
                 EXCEPTION
                    WHEN no_data_found THEN
                        fecha_ini_nueva_tarea := SYSDATE;
                    END;

                fecha_fin_nueva_tarea:= CALCULAR_FECHA_FIN_TAREA(fecha_ini_nueva_tarea, tiempoPrevistoTarea );

				-- Arrastramos el resto de tareas siguientes
                UPDATE aa79b81s01 SET orden_081 = orden_081 + 1 WHERE orden_081 >= orden AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp;

                IF ( tarea.num_total_pal_izo_053 < lim_palabras AND tarea.ind_tramitacion_rapida_029 = 'S' ) THEN
                	-- si es un expediente de tramitación rápida esta tarea va a ir asignada al crearla
                	estado_tarea := estado_pdte_aceptacion_tarea;
                    fecha_aceptacion_tarea := NULL;
                    IF ( dni_recurso_estudio = usuario ) THEN
                        estado_tarea := estado_aceptacion_tarea;
                        fecha_aceptacion_tarea := SYSDATE;
                    END IF;

                    INSERT INTO aa79b81s01 (anyo_081, dni_recurso_081, estado_asignacion_081, estado_ejecucion_081, fecha_aceptacion_081,
                        fecha_asignacion_081, fecha_fin_081, fecha_inicio_081, horas_previstas_081, id_tarea_081, id_tarea_rel_081, id_requerimiento_081,
                        ind_no_conformidad_081, ind_facturable_081, id_lote_081, num_exp_081, observ_081, orden_081, recurso_asignacion_081, id_tipo_tarea_081,
                        tipo_entidad_081, id_entidad_081, dni_asignador_081, id_tipo_revision_081
                        ) VALUES ( tarea.anyo,dni_recurso_estudio,estado_tarea,tarea_pendiente_ejecucion,fecha_aceptacion_tarea,SYSDATE,fecha_fin_nueva_tarea,
                        fecha_ini_nueva_tarea,tiempoPrevistoTarea,nuevo_id_tarea,idtarea,NULL,'N','N',NULL,tarea.num_exp,NULL,orden,recurso_interno,
                        revision_traduccion,NULL,NULL,NULL,tipo_revision_sencilla);

                    -- email asignación
                    SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_traduccion;
	                IF ( dni_recurso_estudio <> usuario ) THEN
	                    IF ( id_proceso_email = 0 ) THEN
	                        SELECT aa79bb2q00.NEXTVAL INTO id_proceso_email FROM dual;
	                    END IF;

	                    INSERT INTO aa79bb2t00 (id_proceso_email_0b2,num_email_0b2,id_tipo_aviso_0b2,anyo_0b2,num_exp_0b2,titulo_exp_0b2,id_tarea_0b2,
                            id_tipo_tarea_0b2,desc_tarea_es_0b2,desc_tarea_eu_0b2,dni_destinatario_0b2,email_destinatario_0b2
                            ) VALUES (id_proceso_email,num_email,tipo_aviso,tarea.anyo,tarea.num_exp,titulo_expediente,nuevo_id_tarea,tarea.id_tipo_tarea,
                            desc_tipo_tarea,desc_tipo_tarea,dni_recurso_estudio,email_destinatario);
                        num_email := num_email + 1;
                        retorno := id_proceso_email;
	                ELSE
	                    retorno := NULL;
	                END IF;

                ELSE
                	-- No es de tramitación rápida, se crea sin asignar
                    INSERT INTO aa79b81s01 (anyo_081,dni_recurso_081,estado_asignacion_081,estado_ejecucion_081,fecha_aceptacion_081, fecha_asignacion_081,
                    fecha_fin_081,fecha_inicio_081, horas_previstas_081,id_tarea_081,id_tarea_rel_081, id_requerimiento_081,ind_no_conformidad_081,
                    ind_facturable_081,id_lote_081,num_exp_081,observ_081, orden_081,recurso_asignacion_081,id_tipo_tarea_081,tipo_entidad_081,id_entidad_081,
                    dni_asignador_081,id_tipo_revision_081
                    ) VALUES (tarea.anyo,NULL,estado_asignacion_tarea,tarea_pendiente_ejecucion, NULL,NULL,NULL, NULL,'',nuevo_id_tarea,idtarea,NULL,'N','N',
                    NULL,tarea.num_exp,NULL,orden,NULL,revision_traduccion,NULL,NULL,NULL,tipo_revision_sencilla);

                END IF;

                INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                INSERT INTO aa79b83t00 (id_tarea_083,id_doc_083)
                SELECT nuevo_id_tarea, id_doc_083 FROM aa79b83t00 WHERE id_tarea_083 = idtarea;

                -- registro acción de creación de tarea
                observaciones := ini_observ || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;
                INSERT INTO aa79b43s01 (id_registro_accion_043, id_punto_menu_043, id_accion_043, ip_043, fecha_registro_043, usuario_registro_043,
                    anyo_exp_043, num_exp_043, observ_043, id_estado_bitacora_043
                    ) VALUES ( aa79b43q00.NEXTVAL, 18,1,ip,SYSDATE,usuario,tarea.anyo,tarea.num_exp,observaciones,id_estado_bitacora);
            ELSE
				-- RECUPERO SU ORDEN PARA ASIGNAR LUEGO EL QUE CORRESPONDA
                SELECT orden_081 INTO orden FROM aa79b81t00 WHERE id_tipo_tarea_081 = revision_traduccion AND id_tarea_rel_081 = idtarea;
            END IF;

            -- metemos la de entrega si es necesario.
            -- comprobamos si existe tarea de entrega
            SELECT COUNT(1) INTO contador_entrega FROM aa79b81t00 WHERE id_tipo_tarea_081 IN (revision_entrega_traduccion, traduccion_entrega_cliente) AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND ind_no_conformidad_081 = 'N';

            IF ( tarea.num_total_pal_izo_053 < lim_palabras AND tarea.ind_tramitacion_rapida_029 = 'S' ) THEN
                IF ( contador_entrega = 0 ) THEN
 					-- CREAMOS LA TAREA ASIGNADA Y ARRASTRAMOS
                    SELECT aa79b81q00.NEXTVAL INTO nuevo_id_tarea FROM dual;
                    orden := orden + 1;

                    tiempoPrevistoTarea:= '00:05';
		   			horasPrevistasTarea:=  PARSE_HORA_MINUTOS(tiempoPrevistoTarea);
						-- comprobamos si hay que aplicar un tiempo extra de gestión
					SELECT IND_TIEMPO_GESTION_015 INTO IND_TIEMPO_GESTION FROM AA79B15S01 WHERE ID_015 = revision_entrega_traduccion;
				    IF( IND_TIEMPO_GESTION = 'S') THEN
				        HORAS_TIEMPO_EXTRA_GESTION := PARSE_HORA_MINUTOS(TIEMPO_EXTRA_GESTION);
				        horasPrevistasTarea:= horasPrevistasTarea + HORAS_TIEMPO_EXTRA_GESTION;
				        tiempoPrevistoTarea:= FORMAT_HORA_MINUTOS(horasPrevistasTarea);
				    END IF;

                    BEGIN
                    SELECT nvl(fecha_fin_081, SYSDATE)INTO fecha_ini_nueva_tarea FROM aa79b81t00
                    WHERE anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND orden_081 = orden - 1
                    ORDER BY fecha_fin_081 DESC FETCH FIRST ROW ONLY;
                     EXCEPTION
                    WHEN no_data_found THEN
                        fecha_ini_nueva_tarea := SYSDATE;
                    END;


                    fecha_fin_nueva_tarea:= CALCULAR_FECHA_FIN_TAREA(fecha_ini_nueva_tarea, tiempoPrevistoTarea );

                    -- Arrastramos las siguientes
                    UPDATE aa79b81s01 SET orden_081 = orden_081 + 1 WHERE orden_081 >= orden AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp;

                    estado_tarea := estado_pdte_aceptacion_tarea;
                    fecha_aceptacion_tarea := NULL;
                    IF ( dni_recurso_estudio = usuario ) THEN
                        estado_tarea := estado_aceptacion_tarea;
                        fecha_aceptacion_tarea := SYSDATE;
                    END IF;

                    INSERT INTO aa79b81s01 (anyo_081,dni_recurso_081, estado_asignacion_081,estado_ejecucion_081,fecha_aceptacion_081,fecha_asignacion_081,fecha_fin_081, fecha_inicio_081,horas_previstas_081,id_tarea_081,
                    id_tarea_rel_081,id_requerimiento_081, ind_no_conformidad_081,ind_facturable_081,id_lote_081,num_exp_081,observ_081,orden_081,recurso_asignacion_081,id_tipo_tarea_081,tipo_entidad_081,id_entidad_081, dni_asignador_081,id_tipo_revision_081
                    ) VALUES (tarea.anyo,dni_recurso_estudio, estado_tarea,tarea_pendiente_ejecucion,fecha_aceptacion_tarea, SYSDATE,fecha_fin_nueva_tarea,fecha_ini_nueva_tarea,tiempoPrevistoTarea,nuevo_id_tarea, NULL, NULL,'N','N', NULL,
                    tarea.num_exp, NULL, orden,recurso_interno,revision_entrega_traduccion, NULL, NULL, usuario,NULL);

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (id_tarea_083,id_doc_083)
                    SELECT nuevo_id_tarea, id_doc_083 FROM aa79b83t00 WHERE id_tarea_083 = idtarea;

                    -- Registro de entrada
                    SELECT desc_eu_015  INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_entrega_traduccion;

                    observaciones := ini_observ || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

                    INSERT INTO aa79b43s01 (id_registro_accion_043,id_punto_menu_043,id_accion_043, ip_043,fecha_registro_043, usuario_registro_043,anyo_exp_043,num_exp_043, observ_043,id_estado_bitacora_043
                    ) VALUES (aa79b43q00.NEXTVAL, 18, 1,ip, SYSDATE,  usuario,tarea.anyo,tarea.num_exp, observaciones, id_estado_bitacora);

                    -- envio de email asignación
                    IF ( dni_recurso_estudio <> usuario ) THEN
                        IF ( id_proceso_email = 0 ) THEN
                            SELECT aa79bb2q00.NEXTVAL INTO id_proceso_email FROM dual;
                        END IF;

                        INSERT INTO aa79bb2t00 ( id_proceso_email_0b2, num_email_0b2,id_tipo_aviso_0b2, anyo_0b2,num_exp_0b2, titulo_exp_0b2,id_tarea_0b2, id_tipo_tarea_0b2, desc_tarea_es_0b2,desc_tarea_eu_0b2,dni_destinatario_0b2,email_destinatario_0b2
                        ) VALUES (id_proceso_email, num_email, tipo_aviso,tarea.anyo, tarea.num_exp,titulo_expediente,nuevo_id_tarea, revision_entrega_traduccion, desc_tipo_tarea,desc_tipo_tarea,dni_recurso_estudio, email_destinatario);

                        num_email := num_email + 1;
                        retorno := id_proceso_email;
                    ELSE
                        retorno := NULL;
                    END IF;

                ELSE
					-- LA TAREA EXISTE PERO LA ASIGNAMOS.
                    SELECT id_tarea_081, orden_081 INTO nuevo_id_tarea, orden FROM aa79b81t00
                    WHERE id_tipo_tarea_081 IN ( revision_entrega_traduccion, traduccion_entrega_cliente) AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND tarea.ind_no_conformidad_081 = 'N';


                    tiempoPrevistoTarea:= '00:05';
		   			horasPrevistasTarea:=  PARSE_HORA_MINUTOS(tiempoPrevistoTarea);
						-- comprobamos si hay que aplicar un tiempo extra de gestión
					SELECT IND_TIEMPO_GESTION_015 INTO IND_TIEMPO_GESTION FROM AA79B15S01 WHERE ID_015 = revision_entrega_traduccion;
				    IF( IND_TIEMPO_GESTION = 'S') THEN
				        HORAS_TIEMPO_EXTRA_GESTION := PARSE_HORA_MINUTOS(TIEMPO_EXTRA_GESTION);
				        horasPrevistasTarea:= horasPrevistasTarea + HORAS_TIEMPO_EXTRA_GESTION;
				        tiempoPrevistoTarea:= FORMAT_HORA_MINUTOS(horasPrevistasTarea);
				    END IF;


                    BEGIN
                    SELECT nvl(fecha_fin_081, SYSDATE) INTO fecha_ini_nueva_tarea
                    FROM aa79b81t00 WHERE anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND orden_081 = orden - 1 ORDER BY fecha_fin_081 DESC
                    FETCH FIRST ROW ONLY;
                     EXCEPTION
                    WHEN no_data_found THEN
                        fecha_ini_nueva_tarea := SYSDATE;
                    END;


                    fecha_fin_nueva_tarea:= CALCULAR_FECHA_FIN_TAREA(fecha_ini_nueva_tarea, tiempoPrevistoTarea );

                    estado_tarea := estado_pdte_aceptacion_tarea;
                    fecha_aceptacion_tarea := NULL;
                    IF ( dni_recurso_estudio = usuario ) THEN
                        estado_tarea := estado_aceptacion_tarea;
                        fecha_aceptacion_tarea := SYSDATE;
                    END IF;

                    UPDATE aa79b81t00
                    SET estado_asignacion_081 = estado_tarea, estado_ejecucion_081 = tarea_pendiente_ejecucion, fecha_aceptacion_081 = fecha_aceptacion_tarea, fecha_asignacion_081 = SYSDATE,
                        fecha_fin_081 = fecha_fin_nueva_tarea, fecha_inicio_081 = fecha_ini_nueva_tarea, horas_previstas_081 = tiempoPrevistoTarea, dni_recurso_081 = dni_recurso_estudio, recurso_asignacion_081 = recurso_interno,
                        id_tipo_tarea_081 = revision_entrega_traduccion, dni_asignador_081 = usuario WHERE id_tarea_081 = nuevo_id_tarea;

                    -- email asignacion
                    SELECT desc_eu_015  INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_entrega_traduccion;

                    observaciones := ini_observ || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

                    IF ( dni_recurso_estudio <> usuario ) THEN
                        IF ( id_proceso_email = 0 ) THEN
                            SELECT aa79bb2q00.NEXTVAL INTO id_proceso_email FROM dual;
                        END IF;

                        INSERT INTO aa79bb2t00 ( id_proceso_email_0b2, num_email_0b2,id_tipo_aviso_0b2, anyo_0b2,num_exp_0b2, titulo_exp_0b2,id_tarea_0b2, id_tipo_tarea_0b2, desc_tarea_es_0b2,desc_tarea_eu_0b2,dni_destinatario_0b2,email_destinatario_0b2
                        ) VALUES (id_proceso_email, num_email, tipo_aviso,tarea.anyo, tarea.num_exp,titulo_expediente,nuevo_id_tarea, revision_entrega_traduccion, desc_tipo_tarea,desc_tipo_tarea,dni_recurso_estudio, email_destinatario);

                        num_email := num_email + 1;
                        retorno := id_proceso_email;
                    ELSE
                        retorno := NULL;
                    END IF;

                    -- registro entrada
                    INSERT INTO aa79b43s01 (id_registro_accion_043,id_punto_menu_043,id_accion_043, ip_043,fecha_registro_043, usuario_registro_043,anyo_exp_043,num_exp_043, observ_043,id_estado_bitacora_043
                    ) VALUES (aa79b43q00.NEXTVAL, 18, 1,ip, SYSDATE,  usuario,tarea.anyo,tarea.num_exp, observaciones, id_estado_bitacora);

                END IF;
           	ELSE
           		IF ( contador_entrega = 0 ) THEN
 					-- CREAMOS LA TAREA Y ARRASTRAMOS
                    SELECT aa79b81q00.NEXTVAL INTO nuevo_id_tarea FROM dual;
                    orden := orden + 1;

                    -- Arrastramos las siguientes
                    UPDATE aa79b81s01 SET orden_081 = orden_081 + 1 WHERE orden_081 >= orden AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp;

					INSERT INTO aa79b81s01 (anyo_081,dni_recurso_081,estado_asignacion_081,estado_ejecucion_081,fecha_aceptacion_081, fecha_asignacion_081,fecha_fin_081,fecha_inicio_081, horas_previstas_081,
	                id_tarea_081, id_tarea_rel_081,id_requerimiento_081, ind_no_conformidad_081, ind_facturable_081, id_lote_081, num_exp_081, observ_081,  orden_081, recurso_asignacion_081, id_tipo_tarea_081, tipo_entidad_081, id_entidad_081, dni_asignador_081, id_tipo_revision_081
	                ) VALUES ( tarea.anyo,NULL,estado_asignacion_tarea,tarea_pendiente_ejecucion,NULL,NULL,NULL,NULL, '', nuevo_id_tarea, idtarea, NULL,'N', 'N', NULL,tarea.num_exp,NULL, orden,
	                NULL, revision_entrega_traduccion,NULL,NULL, NULL, NULL);

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (id_tarea_083,id_doc_083)
                    SELECT nuevo_id_tarea, id_doc_083 FROM aa79b83t00 WHERE id_tarea_083 = idtarea;

                    -- Registro de entrada
                    SELECT desc_eu_015  INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_entrega_traduccion;

                    observaciones := ini_observ || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

                    INSERT INTO aa79b43s01 (id_registro_accion_043,id_punto_menu_043,id_accion_043, ip_043,fecha_registro_043, usuario_registro_043,anyo_exp_043,num_exp_043, observ_043,id_estado_bitacora_043
                    ) VALUES (aa79b43q00.NEXTVAL, 18, 1,ip, SYSDATE,  usuario,tarea.anyo,tarea.num_exp, observaciones, id_estado_bitacora);

                ELSE
                	SELECT
                    orden_081
                	INTO orden
	                FROM
	                    aa79b81t00
	                WHERE id_tipo_tarea_081 IN (revision_entrega_traduccion, traduccion_entrega_cliente) AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND ind_no_conformidad_081 = 'N';

	                SELECT id_tarea_081 INTO nuevo_id_tarea FROM aa79b81s01
                   WHERE id_tipo_tarea_081 IN (revision_entrega_traduccion, traduccion_entrega_cliente) AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND ind_no_conformidad_081 = 'N';

	                UPDATE aa79b81t00
                    SET id_tipo_tarea_081 = revision_entrega_traduccion WHERE id_tarea_081 = nuevo_id_tarea;

                END IF;
           	END IF;

            IF ( tarea.ind_facturable = 'S' ) THEN

                -- comprobamos si existe la de revisión de datos de pago
            	SELECT COUNT(1) INTO contador FROM aa79b81t00
                WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;

                IF ( contador = 0 ) THEN
	 			-- CREAMOS LA TAREA Y ARRASTRAMOS
                    SELECT aa79b81q00.NEXTVAL INTO nuevo_id_tarea FROM dual;

                    orden := orden + 1;

                     -- obtenemos string con los documentos concatenados.
	                SELECT LISTAGG(TO_CHAR(id_doc_083), ',') WITHIN GROUP (ORDER BY id_doc_083) AS description INTO
	                charDocsTarea
					FROM aa79b83t00 WHERE id_tarea_083 = idtarea GROUP BY id_tarea_083;
                    
                    -- al calcular el tiempo previsto, no tenemos en cuenta el tipo de revision porque la tarea no lo requiere
                    -- para ello, ponemos el valor de tipo revision a 0
                    tiempoPrevistoTarea:= CALCULO_HORAS_TRADUCCION(recurso_interno, tarea.anyo, tarea.num_exp, charDocsTarea,
   					revision_pago_prov,0);
		   			horasPrevistasTarea:=  PARSE_HORA_MINUTOS(tiempoPrevistoTarea);

                    BEGIN
                    SELECT nvl(fecha_fin_081, SYSDATE) INTO fecha_ini_nueva_tarea FROM aa79b81t00
                    WHERE anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND orden_081 = orden - 1 ORDER BY fecha_fin_081 DESC FETCH FIRST ROW ONLY;
                     EXCEPTION
                    WHEN no_data_found THEN
                        fecha_ini_nueva_tarea := SYSDATE;
                    END;

                    fecha_fin_nueva_tarea:= CALCULAR_FECHA_FIN_TAREA(fecha_ini_nueva_tarea, tiempoPrevistoTarea );

                    -- Arrastramos las siguientes
                    UPDATE aa79b81s01 SET orden_081 = orden_081 + 1
                    WHERE orden_081 >= orden AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp;

                    IF ( tarea.num_total_pal_izo_053 < lim_palabras AND tarea.ind_tramitacion_rapida_029 = 'S' ) THEN

	                    estado_tarea := estado_pdte_aceptacion_tarea;
	                    fecha_aceptacion_tarea := NULL;
	                    IF ( dni_recurso_estudio = usuario ) THEN
	                        estado_tarea := estado_aceptacion_tarea;
	                        fecha_aceptacion_tarea := SYSDATE;
	                    END IF;

                    	INSERT INTO aa79b81s01 (anyo_081,dni_recurso_081, estado_asignacion_081,estado_ejecucion_081,fecha_aceptacion_081, fecha_asignacion_081,
                        fecha_fin_081,fecha_inicio_081,horas_previstas_081,id_tarea_081, id_tarea_rel_081, id_requerimiento_081, ind_no_conformidad_081,ind_facturable_081,
                        id_lote_081,num_exp_081,observ_081,orden_081, recurso_asignacion_081,id_tipo_tarea_081, tipo_entidad_081,id_entidad_081,
                        dni_asignador_081,id_tipo_revision_081
                   		) VALUES (tarea.anyo,dni_recurso_estudio,estado_tarea,tarea_pendiente_ejecucion,fecha_aceptacion_tarea,SYSDATE,fecha_fin_nueva_tarea,
                   		fecha_ini_nueva_tarea, tiempoPrevistoTarea,nuevo_id_tarea,idtarea, NULL,'N','N', NULL,tarea.num_exp,NULL,orden, recurso_interno,
                   		revision_pago_prov, NULL,NULL,usuario, NULL);

                   		-- email de asignación
                        SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_pago_prov;

	                    IF ( dni_recurso_estudio <> usuario ) THEN
	                        IF ( id_proceso_email = 0 ) THEN
	                            SELECT aa79bb2q00.NEXTVAL INTO id_proceso_email FROM dual;
	                        END IF;

	                        INSERT INTO aa79bb2t00 ( id_proceso_email_0b2, num_email_0b2,id_tipo_aviso_0b2, anyo_0b2,num_exp_0b2, titulo_exp_0b2,id_tarea_0b2, id_tipo_tarea_0b2, desc_tarea_es_0b2,desc_tarea_eu_0b2,dni_destinatario_0b2,email_destinatario_0b2
	                        ) VALUES (id_proceso_email, num_email, tipo_aviso,tarea.anyo, tarea.num_exp,titulo_expediente,nuevo_id_tarea, revision_entrega_traduccion, desc_tipo_tarea,desc_tipo_tarea,dni_recurso_estudio, email_destinatario);

	                        num_email := num_email + 1;
	                        retorno := id_proceso_email;
	                    ELSE
	                        retorno := NULL;
	                    END IF;

                    ELSE
	                    INSERT INTO aa79b81s01 ( anyo_081,dni_recurso_081,estado_asignacion_081, estado_ejecucion_081, fecha_aceptacion_081,fecha_asignacion_081,
	                    fecha_fin_081,fecha_inicio_081, horas_previstas_081, id_tarea_081,id_tarea_rel_081,id_requerimiento_081,ind_no_conformidad_081, ind_facturable_081,
	                    id_lote_081,num_exp_081,observ_081, orden_081, recurso_asignacion_081, id_tipo_tarea_081,tipo_entidad_081, id_entidad_081,
	                    dni_asignador_081,id_tipo_revision_081
	                    ) VALUES (tarea.anyo,NULL,estado_asignacion_tarea,tarea_pendiente_ejecucion,NULL,NULL,NULL,NULL,'',nuevo_id_tarea,idtarea,NULL,
	                    'N','N',NULL,tarea.num_exp,NULL, orden,NULL,revision_pago_prov,NULL,NULL, NULL,NULL);
                    END IF;

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (id_tarea_083,id_doc_083)
                    SELECT nuevo_id_tarea, id_doc_083 FROM aa79b83t00 WHERE id_tarea_083 = idtarea;

                    -- registro de acciones
                    observaciones := ini_observ || nuevo_id_tarea || observ || desc_tipo_tarea|| fin_observ;

                    INSERT INTO aa79b43s01 (id_registro_accion_043,id_punto_menu_043,id_accion_043, ip_043,fecha_registro_043, usuario_registro_043,anyo_exp_043,num_exp_043, observ_043,id_estado_bitacora_043
                    ) VALUES (aa79b43q00.NEXTVAL, 18, 1,ip, SYSDATE,  usuario,tarea.anyo,tarea.num_exp, observaciones, id_estado_bitacora);

				ELSE
                	SELECT orden_081 INTO orden FROM aa79b81t00 WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;
                END IF;

            ELSE
				-- no es facturable, por lo que borramos una posible revisión de datos de pago
                BEGIN
                    SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_pago_prov;

                    SELECT id_tarea_081 INTO nuevo_id_tarea FROM aa79b81s01 WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;

                    observaciones := ini_observ_eliminar || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

                    INSERT INTO aa79b43s01 (id_registro_accion_043,id_punto_menu_043,id_accion_043, ip_043,fecha_registro_043, usuario_registro_043,anyo_exp_043,num_exp_043, observ_043,id_estado_bitacora_043
                    ) VALUES (aa79b43q00.NEXTVAL, 18, 2,ip, SYSDATE,  usuario,tarea.anyo,tarea.num_exp, observaciones, id_estado_bitacora);

                EXCEPTION
                    WHEN no_data_found THEN
                        nuevo_id_tarea := NULL;
                END;
	 		--ELIMINAMOS LA TAREA
                DELETE aa79b81t00
                WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;
            END IF;
            -- comprobamos si existe la tarea de revision interna y si es asi, la eliminamos

            DELETE aa79b81t00
                WHERE (id_tipo_tarea_081 = revision_traduccion_interna AND id_tarea_rel_081 = idtarea)
                        OR (id_tipo_tarea_081 = no_conformidad_traductor AND id_tarea_rel_081 = idtarea);

        ELSE
            -- eliminamos posibles tareas de revisión_traduccion y revision_datos_pago
        	BEGIN
                SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_traduccion;

                SELECT id_tarea_081 INTO nuevo_id_tarea FROM aa79b81s01 WHERE
                id_tipo_tarea_081 = revision_traduccion AND id_tarea_rel_081 = idtarea;

                observaciones := ini_observ_eliminar || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

                INSERT INTO aa79b43s01 (id_registro_accion_043,id_punto_menu_043,id_accion_043, ip_043,fecha_registro_043, usuario_registro_043,anyo_exp_043,num_exp_043, observ_043,id_estado_bitacora_043
                ) VALUES (aa79b43q00.NEXTVAL, 18, 2,ip, SYSDATE,  usuario,tarea.anyo,tarea.num_exp, observaciones, id_estado_bitacora);

                SELECT desc_eu_015 INTO desc_tipo_tarea FROM aa79b15s01 WHERE id_015 = revision_pago_prov;

                SELECT id_tarea_081 INTO nuevo_id_tarea FROM aa79b81s01
                WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;

                observaciones := ini_observ_eliminar || nuevo_id_tarea || observ || desc_tipo_tarea || fin_observ;

                INSERT INTO aa79b43s01 (id_registro_accion_043,id_punto_menu_043,id_accion_043, ip_043,fecha_registro_043, usuario_registro_043,anyo_exp_043,num_exp_043, observ_043,id_estado_bitacora_043
                ) VALUES (aa79b43q00.NEXTVAL, 18, 2,ip, SYSDATE,  usuario,tarea.anyo,tarea.num_exp, observaciones, id_estado_bitacora);

            EXCEPTION
                WHEN no_data_found THEN
                    nuevo_id_tarea := NULL;
            END;

			--ELIMINAMOS LAS TAREAS

            DELETE aa79b81t00 WHERE id_tipo_tarea_081 = revision_traduccion AND id_tarea_rel_081 = idtarea;

            DELETE aa79b81t00 WHERE id_tipo_tarea_081 = revision_pago_prov AND id_tarea_rel_081 = idtarea;

            --IND REQ REV INT

             IF(tarea.ind_req_revision_081 = 'S') THEN
                -- si ind req, comprobamos si esta creada la tarea. Si no lo esta, la creamos
                SELECT COUNT(1) INTO contador FROM aa79b81t00
            	WHERE id_tipo_tarea_081 = revision_traduccion_interna AND id_tarea_rel_081 = idtarea;
             	IF(contador = 0) THEN
                    SELECT aa79b81q00.NEXTVAL INTO nuevo_id_tarea FROM dual;
                    orden := tarea.orden + 1;

                    UPDATE aa79b81s01 SET orden_081 = orden + 1
                    WHERE orden_081 >= orden AND anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp;

                    INSERT INTO aa79b81s01 (anyo_081, dni_recurso_081, estado_asignacion_081, estado_ejecucion_081, fecha_aceptacion_081, fecha_asignacion_081,
                        fecha_fin_081, fecha_inicio_081, horas_previstas_081, id_tarea_081, id_tarea_rel_081, id_requerimiento_081, ind_no_conformidad_081,
                        ind_facturable_081, id_lote_081, num_exp_081, observ_081, orden_081, recurso_asignacion_081, id_tipo_tarea_081, tipo_entidad_081,
                        id_entidad_081, dni_asignador_081, id_tipo_revision_081
                    ) VALUES ( tarea.anyo, NULL, estado_asignacion_tarea, tarea_pendiente_ejecucion, NULL,NULL, NULL,
                    NULL, '', nuevo_id_tarea, idtarea, NULL, 'N', 'N', NULL,tarea.num_exp,NULL,orden,
                    NULL,revision_traduccion_interna,NULL, NULL, NULL, NULL);

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (id_tarea_083,id_doc_083)
                    SELECT nuevo_id_tarea, id_doc_083 FROM aa79b83t00 WHERE id_tarea_083 = idtarea;

                END IF;

            ELSE
                -- si ind req a 'N', comprobamos si existe una tarea de rev_trad_int y si es asi la eliminamos
                DELETE aa79b81t00
                    WHERE (id_tipo_tarea_081 = revision_traduccion_interna AND id_tarea_rel_081 = idtarea)
                        OR (id_tipo_tarea_081 = no_conformidad_traductor AND id_tarea_rel_081 = idtarea);
            END IF;

        END IF;
    ELSIF (tarea.id_tipo_tarea = revision_traduccion ) THEN
        -- si es de revisión_traducción actualizo la de entrega
        SELECT t1.id_tarea_081 INTO tarea_entrega FROM
        aa79b81t00   t1 JOIN aa79b81t00   t2 ON t2.num_exp_081 = t1.num_exp_081
        AND t2.anyo_081 = t1.anyo_081 AND t2.orden_081 < t1.orden_081 AND t2.id_tarea_081 = idtarea
        WHERE t1.id_tipo_tarea_081 IN ( revision_entrega_traduccion, traduccion_entrega_cliente) ORDER BY t1.orden_081 ASC
        FETCH FIRST ROW ONLY;


        tiempoPrevistoTarea:= '00:05';
   		horasPrevistasTarea:=  PARSE_HORA_MINUTOS(tiempoPrevistoTarea);
				-- comprobamos si hay que aplicar un tiempo extra de gestión
		SELECT IND_TIEMPO_GESTION_015 INTO IND_TIEMPO_GESTION FROM AA79B15S01 WHERE ID_015 = revision_entrega_traduccion;
	    IF( IND_TIEMPO_GESTION = 'S') THEN
	        HORAS_TIEMPO_EXTRA_GESTION := PARSE_HORA_MINUTOS(TIEMPO_EXTRA_GESTION);
	        horasPrevistasTarea:= horasPrevistasTarea + HORAS_TIEMPO_EXTRA_GESTION;
	        tiempoPrevistoTarea:= FORMAT_HORA_MINUTOS(horasPrevistasTarea);
	    END IF;

        SELECT recurso_asignacion_081, dni_recurso_081, nvl(fecha_fin_081, SYSDATE)
        INTO recurso_interno, dni_recurso, fecha_ini_nueva_tarea
        FROM aa79b81t00 WHERE id_tarea_081 = idtarea;

        fecha_fin_nueva_tarea:= CALCULAR_FECHA_FIN_TAREA(fecha_ini_nueva_tarea, tiempoPrevistoTarea );

        estado_tarea := estado_pdte_aceptacion_tarea;
        fecha_aceptacion_tarea := NULL;
        IF ( dni_recurso = usuario ) THEN
            estado_tarea := estado_aceptacion_tarea;
            fecha_aceptacion_tarea := SYSDATE;
        END IF;

        UPDATE aa79b81t00
        SET recurso_asignacion_081 = recurso_interno, dni_recurso_081 = dni_recurso, estado_asignacion_081 = estado_tarea,
            fecha_aceptacion_081 = fecha_aceptacion_tarea, fecha_asignacion_081 = SYSDATE, fecha_fin_081 = fecha_fin_nueva_tarea,
            fecha_inicio_081 = fecha_ini_nueva_tarea, horas_previstas_081 = tiempoPrevistoTarea, id_tipo_tarea_081 = revision_entrega_traduccion,
            dni_asignador_081 = usuario
        WHERE id_tarea_081 = tarea_entrega;

        -- y con la de entrega actualizo la de revision de datos pago
		BEGIN
			SELECT
        	t1.id_tarea_081, t1.orden_081
    		INTO tarea_entrega, orden
    		FROM
        	aa79b81t00   t1
    		WHERE
        	t1.id_tipo_tarea_081 = revision_pago_prov and t1.id_tarea_rel_081 = tarea.id_tarea_rel_081
    		AND t1.recurso_asignacion_081 is null;

    		-- obtenemos string con los documentos concatenados.
            SELECT LISTAGG(TO_CHAR(id_doc_083), ',') WITHIN GROUP (ORDER BY id_doc_083) AS description INTO
            charDocsTarea
			FROM aa79b83t00 WHERE id_tarea_083 = idtarea GROUP BY id_tarea_083;
            
            -- al calcular el tiempo previsto, no tenemos en cuenta el tipo de revision porque la tarea no lo requiere
            -- para ello, ponemos el valor de tipo revision a 0
    		tiempoPrevistoTarea:= CALCULO_HORAS_TRADUCCION(recurso_interno, tarea.anyo, tarea.num_exp, charDocsTarea,
   					revision_pago_prov,0);
   			horasPrevistasTarea:=  PARSE_HORA_MINUTOS(tiempoPrevistoTarea);

            BEGIN
    		SELECT nvl(fecha_fin_081, SYSDATE) INTO fecha_ini_nueva_tarea FROM aa79b81t00
            WHERE anyo_081 = tarea.anyo AND num_exp_081 = tarea.num_exp AND orden_081 = orden - 1 ORDER BY fecha_fin_081 DESC FETCH FIRST ROW ONLY;
             EXCEPTION
                    WHEN no_data_found THEN
                        fecha_ini_nueva_tarea := SYSDATE;
                    END;

            fecha_fin_nueva_tarea:= CALCULAR_FECHA_FIN_TAREA(fecha_ini_nueva_tarea, tiempoPrevistoTarea );

           	estado_tarea := estado_pdte_aceptacion_tarea;
    		fecha_aceptacion_tarea := NULL;
            IF ( dni_recurso = usuario ) THEN
                estado_tarea := estado_aceptacion_tarea;
                fecha_aceptacion_tarea := SYSDATE;
            END IF;

            -- se asigna a quien ha realizado el estudio

            UPDATE aa79b81t00
            SET recurso_asignacion_081 = recurso_interno, dni_recurso_081 = dni_recurso_estudio, estado_asignacion_081 = estado_tarea, fecha_aceptacion_081 = fecha_aceptacion_tarea,
                fecha_asignacion_081 = SYSDATE, fecha_fin_081 = fecha_fin_nueva_tarea, fecha_inicio_081 = fecha_ini_nueva_tarea, horas_previstas_081 = tiempoPrevistoTarea,
                dni_asignador_081 = usuario
            WHERE id_tarea_081 = tarea_entrega;

		EXCEPTION
    	WHEN no_data_found THEN
        	tarea_entrega := NULL;
		END;

    END IF;

    RETURN retorno;
EXCEPTION
    WHEN no_data_found THEN

     RETURN NULL;
END;
/