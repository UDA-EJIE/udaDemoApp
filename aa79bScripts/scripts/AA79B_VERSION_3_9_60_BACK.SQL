
ALTER TABLE AA79B15T00
DROP COLUMN "IND_TIEMPO_GESTION_015";

ALTER TABLE AA79B02T01
DROP COLUMN "TIEMPO_EXTRA_GESTION_002";

-- LANZAR PL VERSION ANTERIOR CREAR_TAREAS_RELACIONADAS

UPDATE AA79B15T00 set IND_REQ_CIERRE_015 = 'N' where ID_015 = 11;
/

CREATE OR REPLACE FUNCTION crear_tareas_relacionadas (
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
        id_tarea_rel_081			NUMBER(20)
    );
    tarea                          r_reg_tarea;
    interpretar                    NUMBER(2) := 8;
    revisar                        NUMBER(2) := 4;
    traducir                       NUMBER(2) := 2;
    intro_pago_prov_inter          NUMBER(2) := 12;
    revision_pago_prov             NUMBER(2) := 11;
    revision_entrega               NUMBER(2) := 19;
    revision_traduccion            NUMBER(2) := 21;
    traduccion_entrega_cliente     NUMBER(2) := 22;
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
    charDocsTarea					VARCHAR2(25):= '';
    horasPrevistasTarea					VARCHAR2(10):= '00:00';


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
BEGIN

-- obtenemos el limite de palabras IZO  a partir del cual se realiza presupuesto y proyecto trados
    SELECT
        pal_trados_003
    INTO lim_palabras
    FROM
        aa79b03t00
    WHERE
        id_003 = 0;

 -- OBTENEMOS LOS DATOS DE LA TAREA

    SELECT
        id_tipo_tarea_081,
        ind_facturable_081,
        recurso_asignacion_081,
        num_exp_081,
        anyo_081,
        orden_081,
        fecha_inicio_081,
        fecha_fin_081,
        horas_previstas_081,
        num_total_pal_izo_053,
        ind_tramitacion_rapida_029,
        ind_no_conformidad_081, 
        id_tarea_rel_081
    INTO tarea
    FROM
        aa79b81t00   t1
        LEFT JOIN aa79b53s01   r1 ON t1.anyo_081 = r1.anyo_053
                                   AND t1.num_exp_081 = r1.num_exp_053
        LEFT JOIN aa79b29s01   t29 ON t1.id_lote_081 = t29.id_lote_029
    WHERE
        id_tarea_081 = idtarea;

    SELECT
        id_tipo_expediente_051,
        origen_051,
        aplic_origen_051,
        dni_tecnico_051,
        titulo_051
    INTO
        id_tipo_expediente,
        origen,
        aplic_origen,
        dni_recurso,
        titulo_expediente
    FROM
        aa79b51t00
    WHERE
        anyo_051 = tarea.anyo
        AND num_exp_051 = tarea.num_exp;

    BEGIN
        SELECT
            email_031
        INTO email_destinatario
        FROM
            x54japi_datos_contacto
        WHERE
            dni_031 = dni_recurso;

    EXCEPTION
        WHEN no_data_found THEN
            email_destinatario := '';
    END;

    SELECT
        MAX(id_estado_bitacora_059)
    INTO id_estado_bitacora
    FROM
        aa79b59t00
    WHERE
        anyo_059 = tarea.anyo
        AND num_exp_059 = tarea.num_exp;

    IF ( tarea.id_tipo_tarea = interpretar ) THEN
        IF ( tarea.recurso_asignacion = recurso_externo ) THEN
            IF ( tarea.ind_facturable = si ) THEN
                SELECT
                    COUNT(1)
                INTO contador
                FROM
                    aa79b81t00
                WHERE
                    id_tipo_tarea_081 = intro_pago_prov_inter
                    AND id_tarea_rel_081 = idtarea;

                IF ( contador = 0 ) THEN
	 			-- CREAMOS LA TAREA Y ARRASTRAMOS
                    SELECT
                        aa79b81q00.NEXTVAL
                    INTO nuevo_id_tarea
                    FROM
                        dual;

                    orden := tarea.orden + 1;
                    UPDATE aa79b81s01
                    SET
                        orden_081 = orden_081 + 1
                    WHERE
                        orden_081 >= orden
                        AND anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp;

                    INSERT INTO aa79b81s01 (
                        anyo_081,
                        dni_recurso_081,
                        estado_asignacion_081,
                        estado_ejecucion_081,
                        fecha_aceptacion_081,
                        fecha_asignacion_081,
                        fecha_fin_081,
                        fecha_inicio_081,
                        horas_previstas_081,
                        id_tarea_081,
                        id_tarea_rel_081,
                        id_requerimiento_081,
                        ind_no_conformidad_081,
                        ind_facturable_081,
                        id_lote_081,
                        num_exp_081,
                        observ_081,
                        orden_081,
                        recurso_asignacion_081,
                        id_tipo_tarea_081,
                        tipo_entidad_081,
                        id_entidad_081,
                        dni_asignador_081,
                        id_tipo_revision_081
                    ) VALUES (
                        tarea.anyo,
                        NULL,
                        estado_asignacion_tarea,
                        tarea_pendiente_ejecucion,
                        NULL,
                        NULL,
                        NULL,
                        NULL,
                        '',
                        nuevo_id_tarea,
                        idtarea,
                        NULL,
                        'N',
                        'N',
                        NULL,
                        tarea.num_exp,
                        NULL,
                        orden,
                        NULL,
                        intro_pago_prov_inter,
                        NULL,
                        NULL,
                        NULL,
                        NULL
                    );

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (
                        id_tarea_083,
                        id_doc_083
                    )
                        SELECT
                            nuevo_id_tarea,
                            id_doc_083
                        FROM
                            aa79b83t00
                        WHERE
                            id_tarea_083 = idtarea;

                    SELECT
                        desc_eu_015
                    INTO desc_tipo_tarea
                    FROM
                        aa79b15s01
                    WHERE
                        id_015 = intro_pago_prov_inter;

                    observaciones := ini_observ
                                     || nuevo_id_tarea
                                     || observ
                                     || desc_tipo_tarea
                                     || fin_observ;
                    INSERT INTO aa79b43s01 (
                        id_registro_accion_043,
                        id_punto_menu_043,
                        id_accion_043,
                        ip_043,
                        fecha_registro_043,
                        usuario_registro_043,
                        anyo_exp_043,
                        num_exp_043,
                        observ_043,
                        id_estado_bitacora_043
                    ) VALUES (
                        aa79b43q00.NEXTVAL,
                        18,
                        1,
                        ip,
                        SYSDATE,
                        usuario,
                        tarea.anyo,
                        tarea.num_exp,
                        observaciones,
                        id_estado_bitacora
                    );

                END IF;

            ELSE
                BEGIN
                    SELECT
                        desc_eu_015
                    INTO desc_tipo_tarea
                    FROM
                        aa79b15s01
                    WHERE
                        id_015 = intro_pago_prov_inter;

                    SELECT
                        id_tarea_081
                    INTO nuevo_id_tarea
                    FROM
                        aa79b81s01
                    WHERE
                        id_tipo_tarea_081 = intro_pago_prov_inter
                        AND id_tarea_rel_081 = idtarea;

                    observaciones := ini_observ_eliminar
                                     || nuevo_id_tarea
                                     || observ
                                     || desc_tipo_tarea
                                     || fin_observ;
                    INSERT INTO aa79b43s01 (
                        id_registro_accion_043,
                        id_punto_menu_043,
                        id_accion_043,
                        ip_043,
                        fecha_registro_043,
                        usuario_registro_043,
                        anyo_exp_043,
                        num_exp_043,
                        observ_043,
                        id_estado_bitacora_043
                    ) VALUES (
                        aa79b43q00.NEXTVAL,
                        18,
                        2,
                        ip,
                        SYSDATE,
                        usuario,
                        tarea.anyo,
                        tarea.num_exp,
                        observaciones,
                        id_estado_bitacora
                    );

                EXCEPTION
                    WHEN no_data_found THEN
                        nuevo_id_tarea := NULL;
                END;

	 		--ELIMINAMOS LA TAREA

                DELETE aa79b81t00
                WHERE
                    id_tipo_tarea_081 = intro_pago_prov_inter
                    AND id_tarea_rel_081 = idtarea;

            END IF;

        ELSE
            BEGIN
                SELECT
                    desc_eu_015
                INTO desc_tipo_tarea
                FROM
                    aa79b15s01
                WHERE
                    id_015 = intro_pago_prov_inter;

                SELECT
                    id_tarea_081
                INTO nuevo_id_tarea
                FROM
                    aa79b81s01
                WHERE
                    id_tipo_tarea_081 = intro_pago_prov_inter
                    AND id_tarea_rel_081 = idtarea;

                observaciones := ini_observ_eliminar
                                 || nuevo_id_tarea
                                 || observ
                                 || desc_tipo_tarea
                                 || fin_observ;
                INSERT INTO aa79b43s01 (
                    id_registro_accion_043,
                    id_punto_menu_043,
                    id_accion_043,
                    ip_043,
                    fecha_registro_043,
                    usuario_registro_043,
                    anyo_exp_043,
                    num_exp_043,
                    observ_043,
                    id_estado_bitacora_043
                ) VALUES (
                    aa79b43q00.NEXTVAL,
                    18,
                    2,
                    ip,
                    SYSDATE,
                    usuario,
                    tarea.anyo,
                    tarea.num_exp,
                    observaciones,
                    id_estado_bitacora
                );

            EXCEPTION
                WHEN no_data_found THEN
                    nuevo_id_tarea := NULL;
            END;

		--ELIMINAMOS LA TAREA

            DELETE aa79b81t00
            WHERE
                id_tipo_tarea_081 = intro_pago_prov_inter
                AND id_tarea_rel_081 = idtarea;

        END IF;
    ELSIF ( tarea.id_tipo_tarea = revisar ) THEN
        IF ( tarea.recurso_asignacion = recurso_externo ) THEN
            IF ( tarea.ind_facturable = si ) THEN
                SELECT
                    COUNT(1)
                INTO contador
                FROM
                    aa79b81t00
                WHERE
                    id_tipo_tarea_081 = revision_pago_prov
                    AND id_tarea_rel_081 = idtarea;

                IF ( contador = 0 ) THEN
	 			-- CREAMOS LA TAREA Y ARRASTRAMOS
                    SELECT
                        aa79b81q00.NEXTVAL
                    INTO nuevo_id_tarea
                    FROM
                        dual;

                    orden := tarea.orden + 1;
                    SELECT
                        nvl(fecha_fin_081, SYSDATE),
                        nvl(fecha_fin_081, SYSDATE) + 30 / 1440
                    INTO
                        fecha_ini_nueva_tarea,
                        fecha_fin_nueva_tarea
                    FROM
                        aa79b81t00
                    WHERE
                        anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp
                        AND orden_081 = orden - 1
                    ORDER BY
                        fecha_fin_081 DESC
                    FETCH FIRST ROW ONLY;

                    UPDATE aa79b81s01
                    SET
                        orden_081 = orden_081 + 1
                    WHERE
                        orden_081 >= orden
                        AND anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp;

                    estado_tarea := estado_pdte_aceptacion_tarea;
                    fecha_aceptacion_tarea := NULL;
                    IF ( dni_recurso = usuario ) THEN
                        estado_tarea := estado_aceptacion_tarea;
                        fecha_aceptacion_tarea := SYSDATE;
                    END IF;

                    INSERT INTO aa79b81s01 (
                        anyo_081,
                        dni_recurso_081,
                        estado_asignacion_081,
                        estado_ejecucion_081,
                        fecha_aceptacion_081,
                        fecha_asignacion_081,
                        fecha_fin_081,
                        fecha_inicio_081,
                        horas_previstas_081,
                        id_tarea_081,
                        id_tarea_rel_081,
                        id_requerimiento_081,
                        ind_no_conformidad_081,
                        ind_facturable_081,
                        id_lote_081,
                        num_exp_081,
                        observ_081,
                        orden_081,
                        recurso_asignacion_081,
                        id_tipo_tarea_081,
                        tipo_entidad_081,
                        id_entidad_081,
                        dni_asignador_081,
                        id_tipo_revision_081
                    ) VALUES (
                        tarea.anyo,
                        dni_recurso,
                        estado_tarea,
                        tarea_pendiente_ejecucion,
                        fecha_aceptacion_tarea,
                        SYSDATE,
                        fecha_fin_nueva_tarea,
                        fecha_ini_nueva_tarea,
                        '00:30',
                        nuevo_id_tarea,
                        idtarea,
                        NULL,
                        'N',
                        'N',
                        NULL,
                        tarea.num_exp,
                        NULL,
                        orden,
                        recurso_interno,
                        revision_pago_prov,
                        NULL,
                        NULL,
                        NULL,
                        NULL
                    );

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (
                        id_tarea_083,
                        id_doc_083
                    )
                        SELECT
                            nuevo_id_tarea,
                            id_doc_083
                        FROM
                            aa79b83t00
                        WHERE
                            id_tarea_083 = idtarea;

                    SELECT
                        desc_eu_015
                    INTO desc_tipo_tarea
                    FROM
                        aa79b15s01
                    WHERE
                        id_015 = revision_pago_prov;

                    IF ( dni_recurso <> usuario ) THEN
                        IF ( id_proceso_email = 0 ) THEN
                            SELECT
                                aa79bb2q00.NEXTVAL
                            INTO id_proceso_email
                            FROM
                                dual;

                        END IF;

                        INSERT INTO aa79bb2t00 (
                            id_proceso_email_0b2,
                            num_email_0b2,
                            id_tipo_aviso_0b2,
                            anyo_0b2,
                            num_exp_0b2,
                            titulo_exp_0b2,
                            id_tarea_0b2,
                            id_tipo_tarea_0b2,
                            desc_tarea_es_0b2,
                            desc_tarea_eu_0b2,
                            dni_destinatario_0b2,
                            email_destinatario_0b2
                        ) VALUES (
                            id_proceso_email,
                            num_email,
                            tipo_aviso,
                            tarea.anyo,
                            tarea.num_exp,
                            titulo_expediente,
                            nuevo_id_tarea,
                            tarea.id_tipo_tarea,
                            desc_tipo_tarea,
                            desc_tipo_tarea,
                            dni_recurso,
                            email_destinatario
                        );

                        num_email := num_email + 1;
                        retorno := id_proceso_email;
                    ELSE
                        retorno := NULL;
                    END IF;

                    observaciones := ini_observ
                                     || nuevo_id_tarea
                                     || observ
                                     || desc_tipo_tarea
                                     || fin_observ;
                    INSERT INTO aa79b43s01 (
                        id_registro_accion_043,
                        id_punto_menu_043,
                        id_accion_043,
                        ip_043,
                        fecha_registro_043,
                        usuario_registro_043,
                        anyo_exp_043,
                        num_exp_043,
                        observ_043,
                        id_estado_bitacora_043
                    ) VALUES (
                        aa79b43q00.NEXTVAL,
                        18,
                        1,
                        ip,
                        SYSDATE,
                        usuario,
                        tarea.anyo,
                        tarea.num_exp,
                        observaciones,
                        id_estado_bitacora
                    );

                END IF;

            ELSE
                BEGIN
                    SELECT
                        desc_eu_015
                    INTO desc_tipo_tarea
                    FROM
                        aa79b15s01
                    WHERE
                        id_015 = revision_pago_prov;

                    SELECT
                        id_tarea_081
                    INTO nuevo_id_tarea
                    FROM
                        aa79b81s01
                    WHERE
                        id_tipo_tarea_081 = revision_pago_prov
                        AND id_tarea_rel_081 = idtarea;

                    observaciones := ini_observ_eliminar
                                     || nuevo_id_tarea
                                     || observ
                                     || desc_tipo_tarea
                                     || fin_observ;
                    INSERT INTO aa79b43s01 (
                        id_registro_accion_043,
                        id_punto_menu_043,
                        id_accion_043,
                        ip_043,
                        fecha_registro_043,
                        usuario_registro_043,
                        anyo_exp_043,
                        num_exp_043,
                        observ_043,
                        id_estado_bitacora_043
                    ) VALUES (
                        aa79b43q00.NEXTVAL,
                        18,
                        2,
                        ip,
                        SYSDATE,
                        usuario,
                        tarea.anyo,
                        tarea.num_exp,
                        observaciones,
                        id_estado_bitacora
                    );

                EXCEPTION
                    WHEN no_data_found THEN
                        nuevo_id_tarea := NULL;
                END;

	 		--ELIMINAMOS LA TAREA

                DELETE aa79b81t00
                WHERE
                    id_tipo_tarea_081 = revision_pago_prov
                    AND id_tarea_rel_081 = idtarea;

            END IF;

        ELSE
            BEGIN
                SELECT
                    desc_eu_015
                INTO desc_tipo_tarea
                FROM
                    aa79b15s01
                WHERE
                    id_015 = revision_pago_prov;

                SELECT
                    id_tarea_081
                INTO nuevo_id_tarea
                FROM
                    aa79b81s01
                WHERE
                    id_tipo_tarea_081 = revision_pago_prov
                    AND id_tarea_rel_081 = idtarea;

                observaciones := ini_observ_eliminar
                                 || nuevo_id_tarea
                                 || observ
                                 || desc_tipo_tarea
                                 || fin_observ;
                INSERT INTO aa79b43s01 (
                    id_registro_accion_043,
                    id_punto_menu_043,
                    id_accion_043,
                    ip_043,
                    fecha_registro_043,
                    usuario_registro_043,
                    anyo_exp_043,
                    num_exp_043,
                    observ_043,
                    id_estado_bitacora_043
                ) VALUES (
                    aa79b43q00.NEXTVAL,
                    18,
                    2,
                    ip,
                    SYSDATE,
                    usuario,
                    tarea.anyo,
                    tarea.num_exp,
                    observaciones,
                    id_estado_bitacora
                );

            EXCEPTION
                WHEN no_data_found THEN
                    nuevo_id_tarea := NULL;
            END;

		--ELIMINAMOS LA TAREA

            DELETE aa79b81t00
            WHERE
                id_tipo_tarea_081 = revision_pago_prov
                AND id_tarea_rel_081 = idtarea;

        END IF;
    ELSIF ( tarea.id_tipo_tarea = traducir ) THEN
        IF ( tarea.recurso_asignacion = recurso_externo ) THEN
            SELECT
                COUNT(1)
            INTO contador
            FROM
                aa79b81t00
            WHERE
                id_tipo_tarea_081 = revision_traduccion
                AND id_tarea_rel_081 = idtarea;

            SELECT
                COUNT(1)
            INTO contador_entrega
            FROM
                aa79b81t00
            WHERE
                id_tipo_tarea_081 IN (
                    revision_entrega,
                    traduccion_entrega_cliente
                )
                AND anyo_081 = tarea.anyo
                AND num_exp_081 = tarea.num_exp
                AND ind_no_conformidad_081 = 'N';

            IF ( contador = 0 ) THEN
 		-- CREAMOS LA TAREA Y ARRASTRAMOS
                SELECT
                    aa79b81q00.NEXTVAL
                INTO nuevo_id_tarea
                FROM
                    dual;
                    
                    -- TENEMOS QUE CALCULAR EL TIEMPO NECESARIO PARA LA TAREA
                    -- obtenemos string con los documentos.
                    SELECT LISTAGG(TO_CHAR(id_doc_083), ',') WITHIN GROUP (ORDER BY id_doc_083) AS description INTO
                    charDocsTarea
					FROM aa79b83t00 WHERE id_tarea_083 = idtarea GROUP BY id_tarea_083;
					
					horasPrevistasTarea:= CALCULO_HORAS_TRADUCCION(recurso_externo, tarea.anyo, tarea.num_exp, charDocsTarea, 
   					revision_traduccion);
					
					

                orden := tarea.orden + 1;
                SELECT
                    nvl(fecha_fin_081, SYSDATE),
                    nvl(fecha_fin_081, SYSDATE) + PARSE_HORA_MINUTOS(horasPrevistasTarea)/ 24
                INTO
                    fecha_ini_nueva_tarea,
                    fecha_fin_nueva_tarea
                FROM
                    aa79b81t00
                WHERE
                    anyo_081 = tarea.anyo
                    AND num_exp_081 = tarea.num_exp
                    AND orden_081 = orden - 1
                ORDER BY
                    fecha_fin_081 DESC
                FETCH FIRST ROW ONLY;

                UPDATE aa79b81s01
                SET
                    orden_081 = orden_081 + 1
                WHERE
                    orden_081 >= orden
                    AND anyo_081 = tarea.anyo
                    AND num_exp_081 = tarea.num_exp;

                IF ( tarea.num_total_pal_izo_053 < lim_palabras AND tarea.ind_tramitacion_rapida_029 = 'S' ) THEN
                    estado_tarea := estado_pdte_aceptacion_tarea;
                    fecha_aceptacion_tarea := NULL;
                    IF ( dni_recurso = usuario ) THEN
                        estado_tarea := estado_aceptacion_tarea;
                        fecha_aceptacion_tarea := SYSDATE;
                    END IF;

                    INSERT INTO aa79b81s01 (
                        anyo_081,
                        dni_recurso_081,
                        estado_asignacion_081,
                        estado_ejecucion_081,
                        fecha_aceptacion_081,
                        fecha_asignacion_081,
                        fecha_fin_081,
                        fecha_inicio_081,
                        horas_previstas_081,
                        id_tarea_081,
                        id_tarea_rel_081,
                        id_requerimiento_081,
                        ind_no_conformidad_081,
                        ind_facturable_081,
                        id_lote_081,
                        num_exp_081,
                        observ_081,
                        orden_081,
                        recurso_asignacion_081,
                        id_tipo_tarea_081,
                        tipo_entidad_081,
                        id_entidad_081,
                        dni_asignador_081,
                        id_tipo_revision_081
                    ) VALUES (
                        tarea.anyo,
                        dni_recurso,
                        estado_tarea,
                        tarea_pendiente_ejecucion,
                        fecha_aceptacion_tarea,
                        SYSDATE,
                        fecha_fin_nueva_tarea,
                        fecha_ini_nueva_tarea,
                        horasPrevistasTarea,
                        nuevo_id_tarea,
                        idtarea,
                        NULL,
                        'N',
                        'N',
                        NULL,
                        tarea.num_exp,
                        NULL,
                        orden,
                        recurso_interno,
                        revision_traduccion,
                        NULL,
                        NULL,
                        NULL,
                        NULL
                    );
                    
                    SELECT
                    desc_eu_015
	                INTO desc_tipo_tarea
	                FROM
	                    aa79b15s01
	                WHERE
	                    id_015 = revision_traduccion;
	
	                IF ( dni_recurso <> usuario ) THEN
	                    IF ( id_proceso_email = 0 ) THEN
	                        SELECT
	                            aa79bb2q00.NEXTVAL
	                        INTO id_proceso_email
	                        FROM
	                            dual;
	
	                    END IF;
	
	                    INSERT INTO aa79bb2t00 (
	                        id_proceso_email_0b2,
	                        num_email_0b2,
	                        id_tipo_aviso_0b2,
	                        anyo_0b2,
	                        num_exp_0b2,
	                        titulo_exp_0b2,
	                        id_tarea_0b2,
	                        id_tipo_tarea_0b2,
	                        desc_tarea_es_0b2,
	                        desc_tarea_eu_0b2,
	                        dni_destinatario_0b2,
	                        email_destinatario_0b2
	                    ) VALUES (
	                        id_proceso_email,
	                        num_email,
	                        tipo_aviso,
	                        tarea.anyo,
	                        tarea.num_exp,
	                        titulo_expediente,
	                        nuevo_id_tarea,
	                        tarea.id_tipo_tarea,
	                        desc_tipo_tarea,
	                        desc_tipo_tarea,
	                        dni_recurso,
	                        email_destinatario
	                    );
	
	                    num_email := num_email + 1;
	                    retorno := id_proceso_email;
	                ELSE
	                    retorno := NULL;
	                END IF;

                ELSE
                    INSERT INTO aa79b81s01 (
                        anyo_081,
                        dni_recurso_081,
                        estado_asignacion_081,
                        estado_ejecucion_081,
                        fecha_aceptacion_081,
                        fecha_asignacion_081,
                        fecha_fin_081,
                        fecha_inicio_081,
                        horas_previstas_081,
                        id_tarea_081,
                        id_tarea_rel_081,
                        id_requerimiento_081,
                        ind_no_conformidad_081,
                        ind_facturable_081,
                        id_lote_081,
                        num_exp_081,
                        observ_081,
                        orden_081,
                        recurso_asignacion_081,
                        id_tipo_tarea_081,
                        tipo_entidad_081,
                        id_entidad_081,
                        dni_asignador_081,
                        id_tipo_revision_081
                    ) VALUES (
                        tarea.anyo,
                        NULL,
                        estado_asignacion_tarea,
                        tarea_pendiente_ejecucion,
                        NULL,
                        NULL,
                        NULL,
                        NULL,
                        '',
                        nuevo_id_tarea,
                        idtarea,
                        NULL,
                        'N',
                        'N',
                        NULL,
                        tarea.num_exp,
                        NULL,
                        orden,
                        NULL,
                        revision_traduccion,
                        NULL,
                        NULL,
                        NULL,
                        NULL
                    );

                END IF;

                INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                INSERT INTO aa79b83t00 (
                    id_tarea_083,
                    id_doc_083
                )
                    SELECT
                        nuevo_id_tarea,
                        id_doc_083
                    FROM
                        aa79b83t00
                    WHERE
                        id_tarea_083 = idtarea;

                

                observaciones := ini_observ
                                 || nuevo_id_tarea
                                 || observ
                                 || desc_tipo_tarea
                                 || fin_observ;
                INSERT INTO aa79b43s01 (
                    id_registro_accion_043,
                    id_punto_menu_043,
                    id_accion_043,
                    ip_043,
                    fecha_registro_043,
                    usuario_registro_043,
                    anyo_exp_043,
                    num_exp_043,
                    observ_043,
                    id_estado_bitacora_043
                ) VALUES (
                    aa79b43q00.NEXTVAL,
                    18,
                    1,
                    ip,
                    SYSDATE,
                    usuario,
                    tarea.anyo,
                    tarea.num_exp,
                    observaciones,
                    id_estado_bitacora
                );

            ELSE
		-- RECUPERO SU ORDEN PARA ASIGNAR LUEGO EL QUE CORRESPONDA
                SELECT
                    orden_081
                INTO orden
                FROM
                    aa79b81t00
                WHERE
                    id_tipo_tarea_081 = revision_traduccion
                    AND id_tarea_rel_081 = idtarea;

            END IF;

            IF ( tarea.ind_facturable = si ) THEN
                SELECT
                    COUNT(1)
                INTO contador
                FROM
                    aa79b81t00
                WHERE
                    id_tipo_tarea_081 = revision_pago_prov
                    AND id_tarea_rel_081 = idtarea;

                IF ( contador = 0 ) THEN
	 			-- CREAMOS LA TAREA Y ARRASTRAMOS
                    SELECT
                        aa79b81q00.NEXTVAL
                    INTO nuevo_id_tarea
                    FROM
                        dual;

                        
                    -- TENEMOS QUE CALCULAR EL TIEMPO NECESARIO PARA LA TAREA
                    -- obtenemos string con los documentos.
                    SELECT LISTAGG(TO_CHAR(id_doc_083), ',') WITHIN GROUP (ORDER BY id_doc_083) AS description INTO
                    charDocsTarea
					FROM aa79b83t00 WHERE id_tarea_083 = idtarea GROUP BY id_tarea_083;
					
					horasPrevistasTarea:= CALCULO_HORAS_TRADUCCION(recurso_externo, tarea.anyo, tarea.num_exp, charDocsTarea, 
   					revision_pago_prov);
                        
                        
                    orden := orden + 1;
                    SELECT
                        nvl(fecha_fin_081, SYSDATE),
                        nvl(fecha_fin_081, SYSDATE) + PARSE_HORA_MINUTOS(horasPrevistasTarea)/ 24
                    INTO
                        fecha_ini_nueva_tarea,
                        fecha_fin_nueva_tarea
                    FROM
                        aa79b81t00
                    WHERE
                        anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp
                        AND orden_081 = orden - 1
                    ORDER BY
                        fecha_fin_081 DESC
                    FETCH FIRST ROW ONLY;

                    UPDATE aa79b81s01
                    SET
                        orden_081 = orden_081 + 1
                    WHERE
                        orden_081 >= orden
                        AND anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp;

                    IF ( tarea.num_total_pal_izo_053 < lim_palabras AND tarea.ind_tramitacion_rapida_029 = 'S' ) THEN    
                        
	                    estado_tarea := estado_pdte_aceptacion_tarea;
	                    fecha_aceptacion_tarea := NULL;
	                    IF ( dni_recurso = usuario ) THEN
	                        estado_tarea := estado_aceptacion_tarea;
	                        fecha_aceptacion_tarea := SYSDATE;
	                    END IF;

                    INSERT INTO aa79b81s01 (
                        anyo_081,
                        dni_recurso_081,
                        estado_asignacion_081,
                        estado_ejecucion_081,
                        fecha_aceptacion_081,
                        fecha_asignacion_081,
                        fecha_fin_081,
                        fecha_inicio_081,
                        horas_previstas_081,
                        id_tarea_081,
                        id_tarea_rel_081,
                        id_requerimiento_081,
                        ind_no_conformidad_081,
                        ind_facturable_081,
                        id_lote_081,
                        num_exp_081,
                        observ_081,
                        orden_081,
                        recurso_asignacion_081,
                        id_tipo_tarea_081,
                        tipo_entidad_081,
                        id_entidad_081,
                        dni_asignador_081,
                        id_tipo_revision_081
                    ) VALUES (
                        tarea.anyo,
                        dni_recurso,
                        estado_tarea,
                        tarea_pendiente_ejecucion,
                        fecha_aceptacion_tarea,
                        SYSDATE,
                        fecha_fin_nueva_tarea,
                        fecha_ini_nueva_tarea,
                        '00:30',
                        nuevo_id_tarea,
                        idtarea,
                        NULL,
                        'N',
                        'N',
                        NULL,
                        tarea.num_exp,
                        NULL,
                        orden,
                        recurso_interno,
                        revision_pago_prov,
                        NULL,
                        NULL,
                        NULL,
                        NULL);
                        
                        SELECT
                        desc_eu_015
                    INTO desc_tipo_tarea
                    FROM
                        aa79b15s01
                    WHERE
                        id_015 = revision_pago_prov;

                    IF ( dni_recurso <> usuario ) THEN
                        IF ( id_proceso_email = 0 ) THEN
                            SELECT
                                aa79bb2q00.NEXTVAL
                            INTO id_proceso_email
                            FROM
                                dual;

                        END IF;

                        INSERT INTO aa79bb2t00 (
                            id_proceso_email_0b2,
                            num_email_0b2,
                            id_tipo_aviso_0b2,
                            anyo_0b2,
                            num_exp_0b2,
                            titulo_exp_0b2,
                            id_tarea_0b2,
                            id_tipo_tarea_0b2,
                            desc_tarea_es_0b2,
                            desc_tarea_eu_0b2,
                            dni_destinatario_0b2,
                            email_destinatario_0b2
                        ) VALUES (
                            id_proceso_email,
                            num_email,
                            tipo_aviso,
                            tarea.anyo,
                            tarea.num_exp,
                            titulo_expediente,
                            nuevo_id_tarea,
                            revision_pago_prov,
                            desc_tipo_tarea,
                            desc_tipo_tarea,
                            dni_recurso,
                            email_destinatario
                        );

                        num_email := num_email + 1;
                        retorno := id_proceso_email;
                    ELSE
                        retorno := NULL;
                    END IF;
                        
                    ELSE
	                    INSERT INTO aa79b81s01 (
	                        anyo_081,
	                        dni_recurso_081,
	                        estado_asignacion_081,
	                        estado_ejecucion_081,
	                        fecha_aceptacion_081,
	                        fecha_asignacion_081,
	                        fecha_fin_081,
	                        fecha_inicio_081,
	                        horas_previstas_081,
	                        id_tarea_081,
	                        id_tarea_rel_081,
	                        id_requerimiento_081,
	                        ind_no_conformidad_081,
	                        ind_facturable_081,
	                        id_lote_081,
	                        num_exp_081,
	                        observ_081,
	                        orden_081,
	                        recurso_asignacion_081,
	                        id_tipo_tarea_081,
	                        tipo_entidad_081,
	                        id_entidad_081,
	                        dni_asignador_081,
	                        id_tipo_revision_081
	                    ) VALUES (
	                        tarea.anyo,
	                        NULL,
	                        estado_asignacion_tarea,
	                        tarea_pendiente_ejecucion,
	                        NULL,
	                        NULL,
	                        NULL,
	                        NULL,
	                        '',
	                        nuevo_id_tarea,
	                        idtarea,
	                        NULL,
	                        'N',
	                        'N',
	                        NULL,
	                        tarea.num_exp,
	                        NULL,
	                        orden,
	                        NULL,
	                        revision_pago_prov,
	                        NULL,
	                        NULL,
	                        NULL,
	                        NULL
	                    );
                    
                    END IF;

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (
                        id_tarea_083,
                        id_doc_083
                    )
                        SELECT
                            nuevo_id_tarea,
                            id_doc_083
                        FROM
                            aa79b83t00
                        WHERE
                            id_tarea_083 = idtarea;

                    

                    observaciones := ini_observ
                                     || nuevo_id_tarea
                                     || observ
                                     || desc_tipo_tarea
                                     || fin_observ;
                    INSERT INTO aa79b43s01 (
                        id_registro_accion_043,
                        id_punto_menu_043,
                        id_accion_043,
                        ip_043,
                        fecha_registro_043,
                        usuario_registro_043,
                        anyo_exp_043,
                        num_exp_043,
                        observ_043,
                        id_estado_bitacora_043
                    ) VALUES (
                        aa79b43q00.NEXTVAL,
                        18,
                        1,
                        ip,
                        SYSDATE,
                        usuario,
                        tarea.anyo,
                        tarea.num_exp,
                        observaciones,
                        id_estado_bitacora
                    );
				ELSE
                	SELECT
                    orden_081
                	INTO orden
	                FROM
	                    aa79b81t00
	                WHERE
	                    id_tipo_tarea_081 = revision_pago_prov
	                    AND id_tarea_rel_081 = idtarea;
                    
                END IF;

            ELSE
            
                BEGIN
                    SELECT
                        desc_eu_015
                    INTO desc_tipo_tarea
                    FROM
                        aa79b15s01
                    WHERE
                        id_015 = revision_pago_prov;

                    SELECT
                        id_tarea_081
                    INTO nuevo_id_tarea
                    FROM
                        aa79b81s01
                    WHERE
                        id_tipo_tarea_081 = revision_pago_prov
                        AND id_tarea_rel_081 = idtarea;

                    observaciones := ini_observ_eliminar
                                     || nuevo_id_tarea
                                     || observ
                                     || desc_tipo_tarea
                                     || fin_observ;
                    INSERT INTO aa79b43s01 (
                        id_registro_accion_043,
                        id_punto_menu_043,
                        id_accion_043,
                        ip_043,
                        fecha_registro_043,
                        usuario_registro_043,
                        anyo_exp_043,
                        num_exp_043,
                        observ_043,
                        id_estado_bitacora_043
                    ) VALUES (
                        aa79b43q00.NEXTVAL,
                        18,
                        2,
                        ip,
                        SYSDATE,
                        usuario,
                        tarea.anyo,
                        tarea.num_exp,
                        observaciones,
                        id_estado_bitacora
                    );

                EXCEPTION
                    WHEN no_data_found THEN
                        nuevo_id_tarea := NULL;
                END;	

	 		--ELIMINAMOS LA TAREA

                DELETE aa79b81t00
                WHERE
                    id_tipo_tarea_081 = revision_pago_prov
                    AND id_tarea_rel_081 = idtarea;

            END IF;

            IF ( tarea.num_total_pal_izo_053 < lim_palabras AND tarea.ind_tramitacion_rapida_029 = 'S' ) THEN
                IF ( contador_entrega = 0 ) THEN
	 		-- CREAMOS LA TAREA Y ARRASTRAMOS
                    SELECT
                        aa79b81q00.NEXTVAL
                    INTO nuevo_id_tarea
                    FROM
                        dual;

                    orden := orden + 1;
                    SELECT
                        nvl(fecha_fin_081, SYSDATE),
                        nvl(fecha_fin_081, SYSDATE) + 5 / 1440
                    INTO
                        fecha_ini_nueva_tarea,
                        fecha_fin_nueva_tarea
                    FROM
                        aa79b81t00
                    WHERE
                        anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp
                        AND orden_081 = orden - 1
                    ORDER BY
                        fecha_fin_081 DESC
                    FETCH FIRST ROW ONLY;

                    UPDATE aa79b81s01
                    SET
                        orden_081 = orden_081 + 1
                    WHERE
                        orden_081 >= orden
                        AND anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp;

                    estado_tarea := estado_pdte_aceptacion_tarea;
                    fecha_aceptacion_tarea := NULL;
                    IF ( dni_recurso = usuario ) THEN
                        estado_tarea := estado_aceptacion_tarea;
                        fecha_aceptacion_tarea := SYSDATE;
                    END IF;

                    INSERT INTO aa79b81s01 (
                        anyo_081,
                        dni_recurso_081,
                        estado_asignacion_081,
                        estado_ejecucion_081,
                        fecha_aceptacion_081,
                        fecha_asignacion_081,
                        fecha_fin_081,
                        fecha_inicio_081,
                        horas_previstas_081,
                        id_tarea_081,
                        id_tarea_rel_081,
                        id_requerimiento_081,
                        ind_no_conformidad_081,
                        ind_facturable_081,
                        id_lote_081,
                        num_exp_081,
                        observ_081,
                        orden_081,
                        recurso_asignacion_081,
                        id_tipo_tarea_081,
                        tipo_entidad_081,
                        id_entidad_081,
                        dni_asignador_081,
                        id_tipo_revision_081
                    ) VALUES (
                        tarea.anyo,
                        dni_recurso,
                        estado_tarea,
                        tarea_pendiente_ejecucion,
                        fecha_aceptacion_tarea,
                        SYSDATE,
                        fecha_fin_nueva_tarea,
                        fecha_ini_nueva_tarea,
                        '0:05',
                        nuevo_id_tarea,
                        NULL,
                        NULL,
                        'N',
                        'N',
                        NULL,
                        tarea.num_exp,
                        NULL,
                        orden,
                        recurso_interno,
                        revision_entrega,
                        NULL,
                        NULL,
                        NULL,
                        NULL
                    );

                    INSERT INTO aa79b82t00 ( id_tarea_082 ) VALUES ( nuevo_id_tarea );

                    INSERT INTO aa79b83t00 (
                        id_tarea_083,
                        id_doc_083
                    )
                        SELECT
                            nuevo_id_tarea,
                            id_doc_083
                        FROM
                            aa79b83t00
                        WHERE
                            id_tarea_083 = idtarea;

                    SELECT
                        desc_eu_015
                    INTO desc_tipo_tarea
                    FROM
                        aa79b15s01
                    WHERE
                        id_015 = revision_entrega;

                    observaciones := ini_observ
                                     || nuevo_id_tarea
                                     || observ
                                     || desc_tipo_tarea
                                     || fin_observ;
                    IF ( dni_recurso <> usuario ) THEN
                        IF ( id_proceso_email = 0 ) THEN
                            SELECT
                                aa79bb2q00.NEXTVAL
                            INTO id_proceso_email
                            FROM
                                dual;

                        END IF;

                        INSERT INTO aa79bb2t00 (
                            id_proceso_email_0b2,
                            num_email_0b2,
                            id_tipo_aviso_0b2,
                            anyo_0b2,
                            num_exp_0b2,
                            titulo_exp_0b2,
                            id_tarea_0b2,
                            id_tipo_tarea_0b2,
                            desc_tarea_es_0b2,
                            desc_tarea_eu_0b2,
                            dni_destinatario_0b2,
                            email_destinatario_0b2
                        ) VALUES (
                            id_proceso_email,
                            num_email,
                            tipo_aviso,
                            tarea.anyo,
                            tarea.num_exp,
                            titulo_expediente,
                            nuevo_id_tarea,
                            revision_entrega,
                            desc_tipo_tarea,
                            desc_tipo_tarea,
                            dni_recurso,
                            email_destinatario
                        );

                        num_email := num_email + 1;
                        retorno := id_proceso_email;
                    ELSE
                        retorno := NULL;
                    END IF;

                    INSERT INTO aa79b43s01 (
                        id_registro_accion_043,
                        id_punto_menu_043,
                        id_accion_043,
                        ip_043,
                        fecha_registro_043,
                        usuario_registro_043,
                        anyo_exp_043,
                        num_exp_043,
                        observ_043,
                        id_estado_bitacora_043
                    ) VALUES (
                        aa79b43q00.NEXTVAL,
                        18,
                        1,
                        ip,
                        SYSDATE,
                        usuario,
                        tarea.anyo,
                        tarea.num_exp,
                        observaciones,
                        id_estado_bitacora
                    );

                ELSE
			-- LA TAREA EXISTE PERO LA ASIGNAMOS.
                    SELECT
                        id_tarea_081,
                        orden_081
                    INTO
                        nuevo_id_tarea,
                        orden
                    FROM
                        aa79b81t00
                    WHERE
                        id_tipo_tarea_081 IN (
                            revision_entrega,
                            traduccion_entrega_cliente
                        )
                        AND anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp
                        AND tarea.ind_no_conformidad_081 = 'N';

                    SELECT
                        nvl(fecha_fin_081, SYSDATE),
                        nvl(fecha_fin_081, SYSDATE) + 5 / 1440
                    INTO
                        fecha_ini_nueva_tarea,
                        fecha_fin_nueva_tarea
                    FROM
                        aa79b81t00
                    WHERE
                        anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp
                        AND orden_081 = orden - 1
                    ORDER BY
                        fecha_fin_081 DESC
                    FETCH FIRST ROW ONLY;

                    SELECT
                        nvl(fecha_fin_081, SYSDATE),
                        nvl(fecha_fin_081, SYSDATE) + 5 / 1440
                    INTO
                        fecha_ini_nueva_tarea,
                        fecha_fin_nueva_tarea
                    FROM
                        aa79b81t00
                    WHERE
                        anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp
                        AND orden_081 = orden - 1
                    ORDER BY
                        fecha_fin_081 DESC
                    FETCH FIRST ROW ONLY;

                    estado_tarea := estado_pdte_aceptacion_tarea;
                    fecha_aceptacion_tarea := NULL;
                    IF ( dni_recurso = usuario ) THEN
                        estado_tarea := estado_aceptacion_tarea;
                        fecha_aceptacion_tarea := SYSDATE;
                    END IF;

                    UPDATE aa79b81t00
                    SET
                        estado_asignacion_081 = estado_tarea,
                        estado_ejecucion_081 = tarea_pendiente_ejecucion,
                        fecha_aceptacion_081 = fecha_aceptacion_tarea,
                        fecha_asignacion_081 = SYSDATE,
                        fecha_fin_081 = fecha_fin_nueva_tarea,
                        fecha_inicio_081 = fecha_ini_nueva_tarea,
                        horas_previstas_081 = '0:05',
                        dni_recurso_081 = dni_recurso,
                        recurso_asignacion_081 = recurso_interno,
                        id_tipo_tarea_081 = revision_entrega
                    WHERE
                        id_tarea_081 = nuevo_id_tarea;

                    SELECT
                        desc_eu_015
                    INTO desc_tipo_tarea
                    FROM
                        aa79b15s01
                    WHERE
                        id_015 = revision_entrega;

                    observaciones := ini_observ
                                     || nuevo_id_tarea
                                     || observ
                                     || desc_tipo_tarea
                                     || fin_observ;
                    IF ( dni_recurso <> usuario ) THEN
                        IF ( id_proceso_email = 0 ) THEN
                            SELECT
                                aa79bb2q00.NEXTVAL
                            INTO id_proceso_email
                            FROM
                                dual;

                        END IF;

                        INSERT INTO aa79bb2t00 (
                            id_proceso_email_0b2,
                            num_email_0b2,
                            id_tipo_aviso_0b2,
                            anyo_0b2,
                            num_exp_0b2,
                            titulo_exp_0b2,
                            id_tarea_0b2,
                            id_tipo_tarea_0b2,
                            desc_tarea_es_0b2,
                            desc_tarea_eu_0b2,
                            dni_destinatario_0b2,
                            email_destinatario_0b2
                        ) VALUES (
                            id_proceso_email,
                            num_email,
                            tipo_aviso,
                            tarea.anyo,
                            tarea.num_exp,
                            titulo_expediente,
                            nuevo_id_tarea,
                            tarea.id_tipo_tarea,
                            desc_tipo_tarea,
                            desc_tipo_tarea,
                            dni_recurso,
                            email_destinatario
                        );

                        num_email := num_email + 1;
                        retorno := id_proceso_email;
                    ELSE
                        retorno := NULL;
                    END IF;

                    INSERT INTO aa79b43s01 (
                        id_registro_accion_043,
                        id_punto_menu_043,
                        id_accion_043,
                        ip_043,
                        fecha_registro_043,
                        usuario_registro_043,
                        anyo_exp_043,
                        num_exp_043,
                        observ_043,
                        id_estado_bitacora_043
                    ) VALUES (
                        aa79b43q00.NEXTVAL,
                        18,
                        1,
                        ip,
                        SYSDATE,
                        usuario,
                        tarea.anyo,
                        tarea.num_exp,
                        observaciones,
                        id_estado_bitacora
                    );

                END IF;
            END IF;

        ELSE
            BEGIN
                SELECT
                    desc_eu_015
                INTO desc_tipo_tarea
                FROM
                    aa79b15s01
                WHERE
                    id_015 = revision_traduccion;

                SELECT
                    id_tarea_081
                INTO nuevo_id_tarea
                FROM
                    aa79b81s01
                WHERE
                    id_tipo_tarea_081 = revision_traduccion
                    AND id_tarea_rel_081 = idtarea;

                observaciones := ini_observ_eliminar
                                 || nuevo_id_tarea
                                 || observ
                                 || desc_tipo_tarea
                                 || fin_observ;
                INSERT INTO aa79b43s01 (
                    id_registro_accion_043,
                    id_punto_menu_043,
                    id_accion_043,
                    ip_043,
                    fecha_registro_043,
                    usuario_registro_043,
                    anyo_exp_043,
                    num_exp_043,
                    observ_043,
                    id_estado_bitacora_043
                ) VALUES (
                    aa79b43q00.NEXTVAL,
                    18,
                    2,
                    ip,
                    SYSDATE,
                    usuario,
                    tarea.anyo,
                    tarea.num_exp,
                    observaciones,
                    id_estado_bitacora
                );

                SELECT
                    desc_eu_015
                INTO desc_tipo_tarea
                FROM
                    aa79b15s01
                WHERE
                    id_015 = revision_pago_prov;

                SELECT
                    id_tarea_081
                INTO nuevo_id_tarea
                FROM
                    aa79b81s01
                WHERE
                    id_tipo_tarea_081 = revision_pago_prov
                    AND id_tarea_rel_081 = idtarea;

                observaciones := ini_observ_eliminar
                                 || nuevo_id_tarea
                                 || observ
                                 || desc_tipo_tarea
                                 || fin_observ;
                INSERT INTO aa79b43s01 (
                    id_registro_accion_043,
                    id_punto_menu_043,
                    id_accion_043,
                    ip_043,
                    fecha_registro_043,
                    usuario_registro_043,
                    anyo_exp_043,
                    num_exp_043,
                    observ_043,
                    id_estado_bitacora_043
                ) VALUES (
                    aa79b43q00.NEXTVAL,
                    18,
                    2,
                    ip,
                    SYSDATE,
                    usuario,
                    tarea.anyo,
                    tarea.num_exp,
                    observaciones,
                    id_estado_bitacora
                );

            EXCEPTION
                WHEN no_data_found THEN
                    nuevo_id_tarea := NULL;
            END;

		--ELIMINAMOS LA TAREA

            DELETE aa79b81t00
            WHERE
                id_tipo_tarea_081 = revision_traduccion
                AND id_tarea_rel_081 = idtarea;

            DELETE aa79b81t00
            WHERE
                id_tipo_tarea_081 = revision_pago_prov
                AND id_tarea_rel_081 = idtarea;

        END IF;
    ELSIF ( tarea.id_tipo_tarea = revision_pago_prov OR tarea.id_tipo_tarea = revision_traduccion ) THEN
                -- si es de revisión_traducción miramos si existe una de revisión pago sin asignar y en ese caso asignamos y calculamos
    						
    			IF (tarea.id_tipo_tarea = revision_traduccion) THEN
    			--actualizamos la de revisión pago si está sin asignar
    				BEGIN
    					SELECT
                    	t1.id_tarea_081
                		INTO tarea_entrega
                		FROM
                    	aa79b81t00   t1
                		WHERE
                    	t1.id_tipo_tarea_081 = revision_pago_prov and t1.id_tarea_rel_081 = tarea.id_tarea_rel_081
                		AND t1.recurso_asignacion_081 is null;
                		
                		SELECT
	                        nvl(fecha_fin_081, SYSDATE),
	                        nvl(fecha_fin_081, SYSDATE) + 30 / 1440
	                    INTO
	                        fecha_ini_nueva_tarea,
	                        fecha_fin_nueva_tarea
	                    FROM
	                        aa79b81t00
	                    WHERE
	                        id_tipo_tarea_081 = revision_traduccion
	                        AND anyo_081 = tarea.anyo
	                        AND num_exp_081 = tarea.num_exp;
                		
	                   	estado_tarea := estado_pdte_aceptacion_tarea;
                		fecha_aceptacion_tarea := NULL;
		                IF ( dni_recurso = usuario ) THEN
		                    estado_tarea := estado_aceptacion_tarea;
		                    fecha_aceptacion_tarea := SYSDATE;
		                END IF;

		                UPDATE aa79b81t00
		                SET
		                    recurso_asignacion_081 = recurso_interno,
		                    dni_recurso_081 = dni_recurso,
		                    estado_asignacion_081 = estado_tarea,
		                    fecha_aceptacion_081 = fecha_aceptacion_tarea,
		                    fecha_asignacion_081 = SYSDATE,
		                    fecha_fin_081 = fecha_fin_nueva_tarea,
		                    fecha_inicio_081 = fecha_ini_nueva_tarea,
		                    horas_previstas_081 = '0:30'
		                WHERE
		                    id_tarea_081 = tarea_entrega;
                		
    				EXCEPTION
                	WHEN no_data_found THEN
                    tarea_entrega := NULL;
            		END;
            		
    			END IF;
    
    			SELECT
                    t1.id_tarea_081
                INTO tarea_entrega
                FROM
                    aa79b81t00   t1
                    JOIN aa79b81t00   t2 ON t2.num_exp_081 = t1.num_exp_081
                                          AND t2.anyo_081 = t1.anyo_081
                                          AND t2.orden_081 < t1.orden_081
                                          AND t2.id_tarea_081 = idtarea
                WHERE
                    t1.id_tipo_tarea_081 IN (
                        revision_entrega,
                        traduccion_entrega_cliente
                    )
                ORDER BY
                    t1.orden_081 ASC
                FETCH FIRST ROW ONLY;

                SELECT
                    COUNT(1)
                INTO contador
                FROM
                    aa79b81t00
                WHERE
                    id_tipo_tarea_081 = revision_pago_prov
                    AND anyo_081 = tarea.anyo
                    AND num_exp_081 = tarea.num_exp;

                IF ( contador = 0 ) THEN
                    SELECT
                        recurso_asignacion_081,
                        dni_recurso_081,
                        nvl(fecha_fin_081, SYSDATE),
                        nvl(fecha_fin_081, SYSDATE) + 5 / 1440
                    INTO
                        recurso_interno,
                        dni_recurso,
                        fecha_ini_nueva_tarea,
                        fecha_fin_nueva_tarea
                    FROM
                        aa79b81t00
                    WHERE
                        id_tipo_tarea_081 = revision_traduccion
                        AND anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp;

                ELSE
                    SELECT
                        recurso_asignacion_081,
                        dni_recurso_081
                    INTO
                        recurso_interno,
                        dni_recurso
                    FROM
                        aa79b81t00
                    WHERE
                        id_tipo_tarea_081 = revision_traduccion
                        AND anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp;

                    SELECT
                        nvl(fecha_fin_081, SYSDATE),
                        nvl(fecha_fin_081, SYSDATE) + 5 / 1440
                    INTO
                        fecha_ini_nueva_tarea,
                        fecha_fin_nueva_tarea
                    FROM
                        aa79b81t00
                    WHERE
                        id_tipo_tarea_081 = revision_pago_prov
                        AND anyo_081 = tarea.anyo
                        AND num_exp_081 = tarea.num_exp;

                END IF;

                estado_tarea := estado_pdte_aceptacion_tarea;
                fecha_aceptacion_tarea := NULL;
                IF ( dni_recurso = usuario ) THEN
                    estado_tarea := estado_aceptacion_tarea;
                    fecha_aceptacion_tarea := SYSDATE;
                END IF;

                UPDATE aa79b81t00
                SET
                    recurso_asignacion_081 = recurso_interno,
                    dni_recurso_081 = dni_recurso,
                    estado_asignacion_081 = estado_tarea,
                    fecha_aceptacion_081 = fecha_aceptacion_tarea,
                    fecha_asignacion_081 = SYSDATE,
                    fecha_fin_081 = fecha_fin_nueva_tarea,
                    fecha_inicio_081 = fecha_ini_nueva_tarea,
                    horas_previstas_081 = '0:05'
                WHERE
                    id_tarea_081 = tarea_entrega;

            
    END IF;

    RETURN retorno;
EXCEPTION
    WHEN no_data_found THEN
        RETURN NULL;
END;
/

create or replace FUNCTION COMPROBAR_ORDEN_TAREAS(
      ANYO          IN NUMBER,
      NUM_EXP       IN NUMBER,
      ID_TIPO_TAREA IN NUMBER,
      ORDEN         IN NUMBER,
      ID_TAREA      IN NUMBER)
    RETURN NUMBER
    IS
      RETORNO                      NUMBER(1):=0; -- 0 orden correcto; 1 orden incorrecto
      TIPO_EXPEDIENTE              VARCHAR2(1);
      TIPO_INTERPRETACION          VARCHAR2(1):= 'I';
      TIPO_TRADUCCION              VARCHAR2(1):= 'T';
      TIPO_REVISION                VARCHAR2(1):= 'R';
      IND_REQ_CIERRE               VARCHAR2(1);
      TIPO_TAREA_TRADOS            NUMBER(2) := 1;
      TIPO_TAREA_TRADUCCION        NUMBER(2) := 2;
      TIPO_TAREA_CORREDACCION      NUMBER(2) := 3;
      TIPO_TAREA_REVISAR           NUMBER(2) := 4;
      TIPO_TAREA_NO_CONF_PROVEEDOR NUMBER(2) := 5;
      TIPO_TAREA_GESTION_INTER     NUMBER(2) := 6;
      TIPO_TAREA_PREP_INTER        NUMBER(2) := 7;
      TIPO_TAREA_INTERPRETACION    NUMBER(2) := 8;
      TIPO_TAREA_NO_CONF_CLIENTE   NUMBER(2) := 9;
      TIPO_TAREA_POST_BOE          NUMBER(2) := 10;
      TIPO_TAREA_REV_PAGO_PROV     NUMBER(2) := 11;
      TIPO_TAREA_PAGO_PROF_INTER   NUMBER(2) := 12;
      TIPO_TAREA_NOT_CORRE_PROV    NUMBER(2) := 13;
      TIPO_TAREA_ESTUDIAR_SUBS     NUMBER(2) := 14;
      TIPO_TAREA_ALINEAR           NUMBER(2) := 15;
      TIPO_TAREA_ACT_MEMORIAS      NUMBER(2) := 16;
      TIPO_TAREA_ACT_IDABA         NUMBER(2) := 17;
      TIPO_TAREA_TERMINOLOGIA      NUMBER(2) := 18;
      TIPO_TAREA_ENTREGA_TRAD      NUMBER(2) := 19;
      TIPO_TAREA_REV_ENTREGA       NUMBER(2) := 20;
      TIPO_TAREA_REVISAR_TRAD      NUMBER(2) := 21;
      TIPO_TAREA_TRAD_ENTREGA      NUMBER(2) := 22;
      ESTADO_TAREA_EJECUTADA       NUMBER(1) :=3;
      ID_TAREA_REL                 NUMBER(20):= NULL;
      CURSOR C_TAREAS
      IS
        (SELECT ID_TAREA_081,
          ID_TIPO_TAREA_081,
          ORDEN_081,
          ESTADO_EJECUCION_081,
          IND_REQ_CIERRE_015,
          ID_TAREA_REL_081
        FROM AA79B81T00,
          AA79B15T00
        WHERE ANYO_081        = ANYO
        AND NUM_EXP_081       = NUM_EXP
        AND ID_TIPO_TAREA_081 = ID_015
        AND ID_TAREA_081 <> nvl(ID_TAREA,0)
        );

        ESTADO NUMBER(2);
        ESTADO_CERRADO NUMBER(2):= 6;
        CONTADOR NUMBER(2):=0;
        ORDEN_TAREAS NUMBER(2);
        ORDEN_TAREA_ACTUAL NUMBER(2);
        IS_TAREA_FOUND boolean := false;

    BEGIN
      BEGIN
        SELECT ID_TIPO_EXPEDIENTE_051
        INTO TIPO_EXPEDIENTE
        FROM AA79B51T00
        WHERE NUM_EXP_051 = NUM_EXP
        AND ANYO_051      = ANYO;
        IF (ORDEN        IS NULL) THEN
          RETORNO        := 1;
        ELSIF (ORDEN      = 0 AND TIPO_EXPEDIENTE = TIPO_REVISION) THEN
          RETORNO        := 1;
        ELSIF (ORDEN      = 0 AND TIPO_EXPEDIENTE <> TIPO_REVISION AND ID_TIPO_TAREA NOT IN (TIPO_TAREA_TRADOS, TIPO_TAREA_GESTION_INTER)) THEN
          RETORNO        := 1;
        ELSE
          -- CONSULTAMOS SI EL TIPO DE TAREA ES REQUERIDA PARA EL CIERRE, SI NO LO ES SE LE PERMITE CUALQUIER ORDEN.
          SELECT IND_REQ_CIERRE_015
          INTO IND_REQ_CIERRE
          FROM AA79B15T00
          WHERE ID_015 = ID_TIPO_TAREA;
          -- MIRAMOS SI TIENE TAREA RELACIONADA
          BEGIN
            SELECT ID_TAREA_REL_081
            INTO ID_TAREA_REL
            FROM AA79B81T00
            WHERE ID_TAREA_081 = ID_TAREA;
          EXCEPTION
          WHEN NO_DATA_FOUND THEN
            ID_TAREA_REL := NULL;
          END;
          IF (IND_REQ_CIERRE = 'S') THEN
            -- NOS RECORREMOS EL CURSOR CON LAS TAREAS DEL EXPEDIENTE
            FOR R_TAREAS IN C_TAREAS
            LOOP
                IF (ID_TIPO_TAREA                      = TIPO_TAREA_TRADUCCION OR ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_PROVEEDOR OR ID_TIPO_TAREA = TIPO_TAREA_REVISAR_TRAD) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081        = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  = TIPO_TAREA_TRAD_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_REVISAR OR ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_PROVEEDOR) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_REV_PAGO_PROV) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  =  TIPO_TAREA_TRAD_ENTREGA)  THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;

                --
                  ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_POST_BOE) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  =  TIPO_TAREA_TRAD_ENTREGA)  THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                  --
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_NO_CONF_PROVEEDOR) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  = TIPO_TAREA_TRAD_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 <> ESTADO_TAREA_EJECUTADA AND ORDEN >= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA                  = TIPO_TAREA_NO_CONF_CLIENTE) THEN
                  IF (R_TAREAS.ID_TIPO_TAREA_081       = TIPO_TAREA_ENTREGA_TRAD OR R_TAREAS.ID_TIPO_TAREA_081  =  TIPO_TAREA_TRAD_ENTREGA)  THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 = ESTADO_TAREA_EJECUTADA AND ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO                        := 1;
                    END IF;
                  END IF;
                  IF (R_TAREAS.ID_TIPO_TAREA_081      = TIPO_TAREA_REV_ENTREGA) THEN
                    IF (R_TAREAS.ESTADO_EJECUCION_081 = ESTADO_TAREA_EJECUTADA AND ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO                        := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA       = TIPO_TAREA_ENTREGA_TRAD OR ID_TIPO_TAREA  = TIPO_TAREA_TRAD_ENTREGA)  THEN
                  IF (R_TAREAS.IND_REQ_CIERRE_015      = 'S') THEN
                    IF (ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                ELSIF (ID_TIPO_TAREA                   = TIPO_TAREA_REV_ENTREGA) THEN
                  IF (R_TAREAS.IND_REQ_CIERRE_015      = 'S') THEN
                    IF (ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO                         := 1;
                    END IF;
                  END IF;
                END IF;
                IF (ID_TAREA_REL   IS NOT NULL AND R_TAREAS.ID_TAREA_081 = ID_TAREA_REL) THEN
                  IF (ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_PROVEEDOR) THEN
                    IF (ORDEN       < R_TAREAS.ORDEN_081) THEN
                      RETORNO      := 1;
                    END IF;
                  ELSE
                    IF (ORDEN <= R_TAREAS.ORDEN_081) THEN
                      RETORNO := 1;
                    END IF;
                  END IF;
                END IF;
                IF (ID_TAREA_REL   IS NOT NULL AND R_TAREAS.ID_TAREA_REL_081 = ID_TAREA_REL) THEN
                  IF (ID_TIPO_TAREA = TIPO_TAREA_NO_CONF_PROVEEDOR) THEN
                    IF (ORDEN      >= R_TAREAS.ORDEN_081) THEN
                      RETORNO      := 1;
                    END IF;
                  ELSIF (ID_TIPO_TAREA   = TIPO_TAREA_REV_PAGO_PROV AND R_TAREAS.ID_TIPO_TAREA_081 = TIPO_TAREA_REVISAR_TRAD ) THEN
                    IF (ORDEN      <= R_TAREAS.ORDEN_081) THEN
                      RETORNO      := 1;
                    END IF;

                  ELSIF (ID_TIPO_TAREA   = TIPO_TAREA_REVISAR_TRAD AND R_TAREAS.ID_TIPO_TAREA_081 = TIPO_TAREA_REV_PAGO_PROV ) THEN
					IF (ORDEN      >= R_TAREAS.ORDEN_081) THEN
                      RETORNO      := 1;
                    END IF;
                  END IF;
                END IF;
            END LOOP;
            /*IF NOT IS_TAREA_FOUND THEN
		    	SELECT NVL(MAX(ORDEN_081),0)
		    	INTO ORDEN_TAREAS
		        FROM AA79B81T00
		        WHERE ANYO_081        = ANYO
		        AND NUM_EXP_081       = NUM_EXP
		        AND ID_TAREA_081 <> nvl(ID_TAREA,0);

		        IF (ID_TAREA IS NOT NULL) THEN
	              SELECT ORDEN_081
	              INTO ORDEN_TAREA_ACTUAL
	              FROM AA79B81T00
	              WHERE ID_TAREA_081 = ID_TAREA;

	              IF (ORDEN != ORDEN_TAREA_ACTUAL AND ORDEN <= ORDEN_TAREAS) THEN
	                RETORNO      := 1;
	              END IF;
	            ELSE
	            	IF (ORDEN <= ORDEN_TAREAS) THEN
	              		RETORNO      := 1;
	              	END IF;
	            END IF;
			END IF;*/
          ELSE

          	IF (ORDEN = 0) THEN
              RETORNO:= 1;
            ELSE
            	-- COMPROBAMOS EL ESTADO DEL EXPEDIENTE Y SI ES CERRADO COMPROBAMOS QUE EL ORDEN SEA SUPERIOR A LAS TAREAS DE ENTREGA
            	SELECT ID_ESTADO_EXP_059 into ESTADO FROM AA79B51T00, AA79B59T00 WHERE NUM_EXP_051 = NUM_EXP AND ANYO_051 = ANYO
            	AND NUM_EXP_051 = NUM_EXP_059 AND ANYO_051 = ANYO_059 AND ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059;
            	IF (ESTADO = ESTADO_CERRADO) THEN
            		SELECT COUNT(1) into CONTADOR FROM AA79B81T00 WHERE ID_TIPO_TAREA_081 in (TIPO_TAREA_ENTREGA_TRAD,TIPO_TAREA_REV_ENTREGA, TIPO_TAREA_INTERPRETACION)
            		AND ORDEN_081 >= ORDEN AND NUM_EXP_081 = NUM_EXP AND ANYO_081 = ANYO;
            		IF (CONTADOR > 0) THEN
            			RETORNO:= 1;
            		END IF;
            	END IF;
            END IF;
          END IF;
        END IF;
      EXCEPTION
      WHEN NO_DATA_FOUND THEN
        RETORNO:= 1;
      END;
      RETURN RETORNO;
    END;
    /
    
    CREATE OR REPLACE PROCEDURE ACT_DATOS_PAGO_PROV (
   ID_TAREA IN NUMBER, 
   IND_FORMATO IN VARCHAR2, 
   PAL_FORMATO IN NUMBER,  
   IND_APREMIO IN VARCHAR2, 
   POR_APREMIO IN NUMBER, 
   IND_PENALIZACION IN VARCHAR2, 
   POR_PENALIZACION IN NUMBER,
   NIVEL_CALIDAD IN VARCHAR2
)
IS

ANYO NUMBER(4);
NUMEXP NUMBER(6);
TIPOTAREA NUMBER(2);
IDLOTE NUMBER(6);
TAREAREVPAGOPROVEEDORES NUMBER(20);
IVA NUMBER(3);
NUMTOTALPAL56 NUMBER(6,0);
NUMTOTALPAL91 NUMBER(6);
NUMPALCONCOR084 NUMBER(6);
NUMPALCONCOR8594 NUMBER(6);
NUMPALCONCOR95100 NUMBER(6);
POR_REVISION_FINAL NUMBER(3);
NUMPALIZO NUMBER(6);
NUM_TOTAL_PAL NUMBER(6);
POR_PENALIZACION_CALIDAD NUMBER(3);

IT NUMBER(8,2);
IFOR NUMBER(8,2);
IA NUMBER(8,2);
IR NUMBER(8,2);
ITP NUMBER(9,2);
IMPIVA NUMBER(8,2);
IBP NUMBER(8,2);
IC NUMBER(8,2);

EXISTETAREA NUMBER(1);
IND_ALBARAN VARCHAR2(1);

TAREA_TRADOS NUMBER(1):=  1;
TAREA_TRADUCIR NUMBER(1):=  2;
TAREA_REVISAR NUMBER(1):=  4;
TAREA_PAGO_PROV NUMBER(2):=  11;
REVISAR_TRADUCCION NUMBER(2):=  21;
ENTREGA_CLIENTE_REVISION NUMBER(2):=  20;

TYPE r_reg_provdataforupdate IS RECORD (
	IMPORTE_TAREA_094 NUMBER(8,2),
	IMPORTE_RECARGO_FORMATO_094	NUMBER(8,2),
	IMPORTE_RECARGO_APREMIO_094	NUMBER(8,2)
);
PROVFORUPDATE r_reg_provdataforupdate;

TYPE r_reg_proveedores IS RECORD (
	NUM_TOTAL_PAL_094 NUMBER(6),
	NUM_PAL_CONCOR_0_84_094	NUMBER(6),
	NUM_PAL_CONCOR_85_94_094 NUMBER(6),
	NUM_PAL_CONCOR_95_100_094 NUMBER(6),
	IND_RECARGO_FORMATO_094	VARCHAR2(1),
	NUM_PAL_RECARGO_FORMATO_094	NUMBER(6),
	IND_RECARGO_APREMIO_094	VARCHAR2(1),
	POR_RECARGO_APREMIO_094	NUMBER(3),
	IND_PENALIZACION_094 VARCHAR2(1),
	POR_PENALIZACION_094 NUMBER(3),
	IVA_APLIC_094 NUMBER(3),
	IMPORTE_PAL_APLIC_094 NUMBER(6,5),
	POR_REVISION_APLIC_094 NUMBER(3),
	IMPORTE_TAREA_094 NUMBER(8,2),
	IMPORTE_RECARGO_FORMATO_094	NUMBER(8,2),
	IMPORTE_RECARGO_APREMIO_094	NUMBER(8,2),
	IMPORTE_PENALIZACION_094 NUMBER(8,2),
	IMPORTE_BASE_094 NUMBER(8,2),
	IMPORTE_IVA_094	NUMBER(8,2),
	IMPORTE_TOTAL_094 NUMBER(9,2),
	IND_PAGADO_094 VARCHAR2(1),
	IND_ALBARAN_094	VARCHAR2(1),
	COD_ALBARAN_094	NUMBER(10)
);
PROVEEDORES r_reg_proveedores;

TYPE r_reg_lote IS RECORD (
	FECHA_INICIO_029 DATE,
	FECHA_FIN_029 DATE,
	IMPORTE_ASIGNADO_029 NUMBER(9,2),
    IMPORTE_CONSUMIDO_029 NUMBER(9,2), 
	IMPORTE_PALABRA_029 NUMBER(6,5),
	POR_PAGO_PAL_CONCOR_85_94_029 NUMBER(3),
	POR_PAGO_PAL_CONCOR_95_100_029 NUMBER(3),
	POR_REVISION_029 NUMBER(3),
	POR_RECARGO_FORMATO_029	NUMBER(3),
	IND_RECARGO_APREMIO_029	VARCHAR2(1),
	POR_RECARGO_APREMIO_029	NUMBER(3),
	ID_TIPO_PENALIZACION_029 VARCHAR2(1),
	POR_PENALIZACION_029 NUMBER(3)
);
LOTE r_reg_lote;

LIM_PALABRAS_TRADOS NUMBER(6);

IDTAREAREL NUMBER(20);

		
		

BEGIN 
	-- obtenemos el limite de palabras IZO  a partir del cual se realiza presupuesto y proyecto trados
	select  PAL_TRADOS_003 into LIM_PALABRAS_TRADOS from AA79B03T00 where ID_003 = 0;
	
	SELECT ANYO_081, NUM_EXP_081, ID_TIPO_TAREA_081, ID_LOTE_081, ID_TAREA_REL_081 INTO ANYO, NUMEXP, TIPOTAREA, IDLOTE, IDTAREAREL FROM AA79B81T00 WHERE ID_TAREA_081 = ID_TAREA;
	
	SELECT POR_IVA_031 INTO IVA FROM AA79B30T00, AA79B31T00 WHERE IND_VIGOR_030 = 'S' AND ID_030 = ID_ORDEN_031;
		
	IF (TIPOTAREA = TAREA_TRADUCIR OR TIPOTAREA = TAREA_REVISAR) THEN
	
		SELECT ID_TAREA_081 INTO TAREAREVPAGOPROVEEDORES FROM AA79B81T00 WHERE ID_TAREA_REL_081 = ID_TAREA AND ID_TIPO_TAREA_081 = TAREA_PAGO_PROV;
	
    	SELECT NUM_TOTAL_PAL_IZO_053 INTO NUMPALIZO FROM AA79B51T00 JOIN AA79B53T00 ON ANYO_053 = ANYO_051 AND NUM_EXP_053 = NUM_EXP_051 WHERE ANYO_053 = ANYO AND NUM_EXP_053 = NUMEXP;
	
		SELECT FECHA_INICIO_029, FECHA_FIN_029, IMPORTE_ASIGNADO_029, IMPORTE_CONSUMIDO_029, IMPORTE_PALABRA_029, 
		POR_PAGO_PAL_CONCOR_85_94_029, POR_PAGO_PAL_CONCOR_95_100_029, POR_REVISION_029, POR_RECARGO_FORMATO_029, IND_RECARGO_APREMIO_029, 
		POR_RECARGO_APREMIO_029, ID_TIPO_PENALIZACION_029, POR_PENALIZACION_029
		INTO LOTE FROM AA79B29T00 WHERE ID_LOTE_029 = IDLOTE;
		
		SELECT NVL(SUM(t56.num_pal_izo_056),0), NVL(SUM(tr2.num_total_pal_091),0), NVL(SUM(tr2.num_pal_concor_0_84_091),0), NVL(SUM(tr2.num_pal_concor_85_94_091),0), NVL(SUM(tr2.num_pal_concor_95_100_091),0)  
		INTO NUMTOTALPAL56, NUMTOTALPAL91, NUMPALCONCOR084, NUMPALCONCOR8594, NUMPALCONCOR95100
		FROM AA79B81T00 t81 
		LEFT JOIN AA79B83T00 t83 ON t81.id_tarea_081 = t83.id_tarea_083 
		LEFT JOIN AA79B56T00 t56 ON t83.id_doc_083 = t56.id_doc_056
		LEFT JOIN AA79B81T00 tr1 ON tr1.ANYO_081 = t81.ANYO_081 AND tr1.NUM_EXP_081 = t81.NUM_EXP_081 AND tr1.ID_TIPO_TAREA_081 = TAREA_TRADOS
		LEFT JOIN AA79B91T00 tr2 ON tr1.ID_TAREA_081 = tr2.ID_TAREA_091 AND tr2.id_doc_orig_091 = t83.id_doc_083 
		WHERE t81.ID_TAREA_081 = ID_TAREA
		GROUP BY t81.id_tarea_081;
			
		IF (TAREAREVPAGOPROVEEDORES IS NOT NULL) THEN
		
			-- importeRealizacionTarea
			IF (NUMPALIZO <= LIM_PALABRAS_TRADOS) THEN
  				NUM_TOTAL_PAL := NUMPALIZO;
  				NUMTOTALPAL91:= 0;
  				NUMPALCONCOR084:= 0;
  				NUMPALCONCOR8594:= 0; 
  				NUMPALCONCOR95100:= 0;
			ELSE
				IF (NUMTOTALPAL91 = 0) THEN
					NUM_TOTAL_PAL := NUMPALIZO;
				ELSE
					NUM_TOTAL_PAL := NUMTOTALPAL91;
				END IF;
			END IF;
		
			IF (TIPOTAREA = TAREA_TRADUCIR) THEN
				IF (NUM_TOTAL_PAL != 0 AND NUMPALCONCOR084 = 0 AND NUMPALCONCOR8594 = 0 AND NUMPALCONCOR95100 = 0) THEN
					IT := NUM_TOTAL_PAL * LOTE.IMPORTE_PALABRA_029;
				ELSE
					IT := (NUMPALCONCOR084 * LOTE.IMPORTE_PALABRA_029) + (NUMPALCONCOR8594 * LOTE.IMPORTE_PALABRA_029 * (LOTE.POR_PAGO_PAL_CONCOR_85_94_029/100)) + (NUMPALCONCOR95100 * LOTE.IMPORTE_PALABRA_029 * (LOTE.POR_PAGO_PAL_CONCOR_95_100_029/100));
				END IF;
				POR_REVISION_FINAL := 0;
			ELSE
				IT := NUM_TOTAL_PAL * LOTE.IMPORTE_PALABRA_029 * (LOTE.POR_REVISION_029/100);
				POR_REVISION_FINAL := LOTE.POR_REVISION_029;
			END IF;
			
			--importeRecargoFormato
			IFOR := PAL_FORMATO * LOTE.IMPORTE_PALABRA_029 * (LOTE.POR_RECARGO_FORMATO_029/100);
			
			--importeRecargoApremio
			IA := IT * (POR_APREMIO/100);
			
			--importePenalizacionRetraso
			IR := (IT + IFOR + IA) * (0/100);
			
			--importeTotalPagar
			ITP := (IT + IFOR + IA) - IR;
						
			--importeBasePagar
			IBP := ITP * 100/(100 + IVA);
			
			--importeCorrespondienteIVA
			IMPIVA := ITP - IBP;
	
			SELECT COUNT(1) INTO EXISTETAREA FROM AA79B94T00 WHERE ID_TAREA_094 = TAREAREVPAGOPROVEEDORES;
			
			IF (EXISTETAREA > 0) THEN
				
				UPDATE AA79B94T00 SET NUM_TOTAL_PAL_094 = NUM_TOTAL_PAL,
				NUM_PAL_CONCOR_0_84_094 = NUMPALCONCOR084,
				NUM_PAL_CONCOR_85_94_094 = NUMPALCONCOR8594,
				NUM_PAL_CONCOR_95_100_094 = NUMPALCONCOR95100,
				IND_RECARGO_FORMATO_094 = IND_FORMATO,
				NUM_PAL_RECARGO_FORMATO_094 = PAL_FORMATO,
				IND_RECARGO_APREMIO_094 = IND_APREMIO,
				POR_RECARGO_APREMIO_094 = POR_APREMIO,
				IVA_APLIC_094 = IVA,
				POR_REVISION_APLIC_094 =POR_REVISION_FINAL,
				IMPORTE_PAL_APLIC_094 = LOTE.IMPORTE_PALABRA_029,
				IMPORTE_TAREA_094 = IT,  
				IMPORTE_RECARGO_FORMATO_094 = IFOR,  
				IMPORTE_RECARGO_APREMIO_094 = IA, 
				IMPORTE_PENALIZACION_094 = IR,  
				IMPORTE_BASE_094 = IBP, 
				IMPORTE_IVA_094 = IMPIVA,  
				IMPORTE_TOTAL_094 = ITP 
				WHERE ID_TAREA_094 = TAREAREVPAGOPROVEEDORES;
			
			ELSE
			
				INSERT INTO AA79B94T00 (
				ID_TAREA_094,
				NUM_TOTAL_PAL_094,
				NUM_PAL_CONCOR_0_84_094,
				NUM_PAL_CONCOR_85_94_094,
				NUM_PAL_CONCOR_95_100_094,
				IND_RECARGO_FORMATO_094,
				NUM_PAL_RECARGO_FORMATO_094,
				IND_RECARGO_APREMIO_094,
				POR_RECARGO_APREMIO_094,
				IVA_APLIC_094,
				POR_REVISION_APLIC_094,
				IMPORTE_PAL_APLIC_094,
				IMPORTE_TAREA_094,  
				IMPORTE_RECARGO_FORMATO_094,  
				IMPORTE_RECARGO_APREMIO_094, 
				IMPORTE_PENALIZACION_094,  
				IMPORTE_BASE_094, 
				IMPORTE_IVA_094,  
				IMPORTE_TOTAL_094) VALUES (
				TAREAREVPAGOPROVEEDORES,
				NUM_TOTAL_PAL,
				NUMPALCONCOR084,
				NUMPALCONCOR8594,
				NUMPALCONCOR95100,
				IND_FORMATO,
				PAL_FORMATO,
				IND_APREMIO,
				POR_APREMIO,
				IVA,
				POR_REVISION_FINAL,
				LOTE.IMPORTE_PALABRA_029,
				IT,  
				IFOR,  
				IA, 
				IR,  
				IBP, 
				IMPIVA,  
				ITP);
				
			END IF;
		
		END IF;
		
	ELSE IF (TIPOTAREA = REVISAR_TRADUCCION) THEN
	
		SELECT ID_TAREA_081 INTO TAREAREVPAGOPROVEEDORES FROM AA79B81T00 
		WHERE ID_TIPO_TAREA_081 = TAREA_PAGO_PROV AND ID_TAREA_REL_081 = IDTAREAREL;
		
		SELECT IMPORTE_TAREA_094,
		IMPORTE_RECARGO_FORMATO_094,
		IMPORTE_RECARGO_APREMIO_094
		INTO PROVFORUPDATE
		FROM AA79B94T00
		WHERE ID_TAREA_094 = TAREAREVPAGOPROVEEDORES;
		
		IT := PROVFORUPDATE.IMPORTE_TAREA_094;
		IFOR := PROVFORUPDATE.IMPORTE_RECARGO_FORMATO_094;
		IA := PROVFORUPDATE.IMPORTE_RECARGO_APREMIO_094;
		
		--importePenalizacionRetraso
		IR := (IT + IFOR + IA) * (POR_PENALIZACION/100);
		
		--importeTotalPagar
		ITP := (IT + IFOR + IA) - IR;
				
		--importeBasePagar
		IBP := ITP * 100/(100 + IVA);
		
		--importeCorrespondienteIVA
		IMPIVA := ITP - IBP;
		
		UPDATE AA79B94T00 SET 
		IND_PENALIZACION_094 = IND_PENALIZACION,
		POR_PENALIZACION_094 = POR_PENALIZACION,
		IMPORTE_PENALIZACION_094 = IR,  
		IMPORTE_BASE_094 = IBP, 
		IMPORTE_IVA_094 = IMPIVA,  
		IMPORTE_TOTAL_094 = ITP
		WHERE ID_TAREA_094 = TAREAREVPAGOPROVEEDORES;
		
    	IF (NIVEL_CALIDAD IS NOT NULL) THEN
			SELECT POR_PENALIZACION_0B5 INTO POR_PENALIZACION_CALIDAD FROM AA79BB5T00 WHERE NIVEL_0B5 = NIVEL_CALIDAD AND ESTADO_0B5 = 'A';	
			
			--importePenalizacionCalidad
			IC := ROUND((ITP) * (POR_PENALIZACION_CALIDAD/100),2);
			
			--importeTotalPagar
			ITP := (IT + IFOR + IA) - IR - IC;
					
			--importeBasePagar
			IBP := ITP * 100/(100 + IVA);
			
			--importeCorrespondienteIVA
			IMPIVA := ITP - IBP;
			
			UPDATE AA79B94T00 SET 
			NIVEL_CALIDAD_094 = NIVEL_CALIDAD,
			POR_PENAL_CALIDAD_094 = POR_PENALIZACION_CALIDAD,
			IMP_PENAL_CALIDAD_094 = IC,
			IMPORTE_BASE_094 = IBP, 
			IMPORTE_IVA_094 = IMPIVA,  
			IMPORTE_TOTAL_094 = ITP
			WHERE ID_TAREA_094 = TAREAREVPAGOPROVEEDORES;
		END IF;
	
	ELSE IF (TIPOTAREA = ENTREGA_CLIENTE_REVISION) THEN
	
		SELECT ID_TAREA_081 INTO TAREAREVPAGOPROVEEDORES FROM AA79B81T00 
		WHERE ID_TAREA_REL_081 = (Select tareaRev.ID_TAREA_081 FROM AA79B81T00 tareaEntr
		JOIN AA79B81T00 tareaRev ON tareaRev.ANYO_081 = tareaEntr.ANYO_081 AND 
		tareaRev.NUM_EXP_081 = tareaEntr.NUM_EXP_081 AND tareaRev.ID_TIPO_TAREA_081 = TAREA_REVISAR AND 
		tareaRev.ORDEN_081 < tareaEntr.ORDEN_081 
		WHERE tareaEntr.ID_TAREA_081 = ID_TAREA 
		ORDER BY tareaRev.ORDEN_081 DESC FETCH first 1 ROWS ONLY 
		)  AND ID_TIPO_TAREA_081 = TAREA_PAGO_PROV; 

		SELECT IMPORTE_TAREA_094,
		IMPORTE_RECARGO_FORMATO_094,
		IMPORTE_RECARGO_APREMIO_094
		INTO PROVFORUPDATE
		FROM AA79B94T00
		WHERE ID_TAREA_094 = TAREAREVPAGOPROVEEDORES;
		
		IT := PROVFORUPDATE.IMPORTE_TAREA_094;
		IFOR := PROVFORUPDATE.IMPORTE_RECARGO_FORMATO_094;
		IA := PROVFORUPDATE.IMPORTE_RECARGO_APREMIO_094;
		
		--importePenalizacionRetraso
		IR := (IT + IFOR + IA) * (POR_PENALIZACION/100);
		
		--importeTotalPagar
		ITP := (IT + IFOR + IA) - IR;
				
		--importeBasePagar
		IBP := ITP * 100/(100 + IVA);
		
		--importeCorrespondienteIVA
		IMPIVA := ITP - IBP;
		
		/**
		IF (NIVEL_CALIDAD IS NOT NULL) THEN
			SELECT POR_PENALIZACION_0B5 INTO POR_PENALIZACION_CALIDAD FROM AA79BB5T00 WHERE NIVEL_0B5 = NIVEL_CALIDAD AND ESTADO_0B5 = 'A';
			IF (IND_PENALIZACION = 'S' OR POR_PENALIZACION_CALIDAD != 0) THEN 
				IND_ALBARAN := 'N';
			ELSE
				IND_ALBARAN := 'S';
			END IF;
		ELSE
			IF (IND_PENALIZACION = 'S') THEN 
				IND_ALBARAN := 'N';
			ELSE
				IND_ALBARAN := 'S';
			END IF;
		END IF;
		*/
		IND_ALBARAN := 'N';
		
		UPDATE AA79B94T00 SET 
		IND_PENALIZACION_094 = IND_PENALIZACION,
		POR_PENALIZACION_094 = POR_PENALIZACION,
		IMPORTE_PENALIZACION_094 = IR,  
		IMPORTE_BASE_094 = IBP, 
		IMPORTE_IVA_094 = IMPIVA,  
		IMPORTE_TOTAL_094 = ITP,
		IND_ALBARAN_094 = IND_ALBARAN
		WHERE ID_TAREA_094 = TAREAREVPAGOPROVEEDORES;
    
    IF (NIVEL_CALIDAD IS NOT NULL) THEN
			--importePenalizacionCalidad
			IC := ROUND((ITP) * (POR_PENALIZACION_CALIDAD/100),2);
			
			--importeTotalPagar
			ITP := (IT + IFOR + IA) - IR - IC;
					
			--importeBasePagar
			IBP := ITP * 100/(100 + IVA);
			
			--importeCorrespondienteIVA
			IMPIVA := ITP - IBP;
			
			UPDATE AA79B94T00 SET 
			NIVEL_CALIDAD_094 = NIVEL_CALIDAD,
			POR_PENAL_CALIDAD_094 = POR_PENALIZACION_CALIDAD,
			IMP_PENAL_CALIDAD_094 = IC,
			IMPORTE_BASE_094 = IBP, 
			IMPORTE_IVA_094 = IMPIVA,  
			IMPORTE_TOTAL_094 = ITP
			WHERE ID_TAREA_094 = TAREAREVPAGOPROVEEDORES;
		END IF;
	
	END IF;
  
		END IF;

	END IF;

END;
/


create or replace FUNCTION CREAR_TAREAS_EXPEDIENTE(
		   ANYO IN   NUMBER,
		   NUM_EXP IN NUMBER,
		   USUARIO IN VARCHAR2 )
		RETURN NUMBER
	IS

		ID_GRUPO_TRABAJO NUMBER(3);
		DNI_RESPONSABLE VARCHAR2(10);
		NUM_TOTAL_PAL_IZO NUMBER(6);

		ESTADO_TAREA NUMBER(1);
		ESTADO_ASIGNACION_TAREA NUMBER(1):= 1;
		ESTADO_PDTE_ACEPTACION_TAREA NUMBER(1):= 2;
		ESTADO_ACEPTACION_TAREA NUMBER(1):= 3;
		TAREA_PENDIENTE_EJECUCION NUMBER(1):= 1;
		PROYECTO_TRADOS NUMBER(2):= 1;
		ID_TAREA NUMBER(20):= 0;
		DESC_TAREA_ES VARCHAR2(150);
		DESC_TAREA_EU VARCHAR2(150);

		ESTADO_BITACORA NUMBER(3);
		EXPEDIENTE_EN_CURSO NUMBER(1):= 3;
		FASE_EXP_PDTE_PROY_TRADOS NUMBER(1):= 6;

		ID_PROCESO_EMAIL NUMBER(20):= 0;
		NUM_EMAIL NUMBER(2):= 1;
		TIPO_AVISO NUMBER(2):= 5;

		EMAIL_DESTINATARIO VARCHAR2(256);
		TIPO_REVISION VARCHAR2(1):= 'R';
		TIPO_TRADUCCION VARCHAR2(1):= 'T';
		ID_TIPO_EXPEDIENTE VARCHAR2(1);
		DNI_TECNICO VARCHAR2(10);
		TITULO_EXPEDIENTE VARCHAR2(150);
		ORIGEN VARCHAR2(1);
		APLIC_ORIGEN VARCHAR2(6);
		ENTREGA_CLIENTE_REVISION NUMBER(2):= 20;
		ORDEN NUMBER(2):= 1;

		OSAKIDETZA VARCHAR2(50);
		UPV VARCHAR2(50);
		TIPO_ENTIDAD VARCHAR2(1);
    	ID_ENTIDAD NUMBER(8);

        TIENETAREAS NUMBER(4);

		RETORNO NUMBER(20);
		FECHA_INICIO DATE;
		FECHA_FIN DATE;

		CONTADOR NUMBER(2):=0;
		CONTADORTRADOS NUMBER(2):=0;

		LIM_PALABRAS NUMBER(6);

	BEGIN

		-- obtenemos el limite de palabras IZO  a partir del cual se realiza presupuesto y proyecto trados
		select PAL_TRADOS_003 into LIM_PALABRAS from AA79B03T00 where ID_003 = 0;

		SELECT ID_TIPO_EXPEDIENTE_051, ORIGEN_051, APLIC_ORIGEN_051, DNI_TECNICO_051, TITULO_051 into ID_TIPO_EXPEDIENTE, ORIGEN, APLIC_ORIGEN, DNI_TECNICO, TITULO_EXPEDIENTE FROM AA79B51T00 WHERE ANYO_051 = ANYO AND NUM_EXP_051 = NUM_EXP;

        SELECT  COUNT(1) into TIENETAREAS FROM AA79B81T00 WHERE ANYO_081 =  ANYO AND NUM_EXP_081 = NUM_EXP;

		IF (ID_TIPO_EXPEDIENTE = TIPO_TRADUCCION AND TIENETAREAS = 0) THEN

			ID_GRUPO_TRABAJO:= OBTENER_GRUPO_TRABAJO(ANYO,NUM_EXP);

			SELECT DNI_RESPONSABLE_026 into DNI_RESPONSABLE FROM AA79B26T00 WHERE ID_026 = ID_GRUPO_TRABAJO;

			SELECT NUM_TOTAL_PAL_IZO_053 into NUM_TOTAL_PAL_IZO FROM AA79B53T00 WHERE ANYO_053 = ANYO AND NUM_EXP_053 = NUM_EXP;

			IF (NUM_TOTAL_PAL_IZO >= LIM_PALABRAS) THEN


				SELECT COUNT(1) INTO CONTADORTRADOS FROM AA79B81T00 WHERE ID_TIPO_TAREA_081 = PROYECTO_TRADOS AND ANYO_081 = ANYO AND NUM_EXP_081 = NUM_EXP;
			 	IF (CONTADORTRADOS = 0) THEN

					SELECT AA79B81Q00.NEXTVAL INTO ID_TAREA FROM dual;

					ESTADO_TAREA:= ESTADO_PDTE_ACEPTACION_TAREA;
			    	IF (DNI_RESPONSABLE = USUARIO) THEN
			    		ESTADO_TAREA:= ESTADO_ACEPTACION_TAREA;
			    	END IF;

			        INSERT INTO AA79B81S01 (ANYO_081, DNI_RECURSO_081, ESTADO_ASIGNACION_081, ESTADO_EJECUCION_081, FECHA_ACEPTACION_081, FECHA_ASIGNACION_081, FECHA_FIN_081, FECHA_INICIO_081, HORAS_PREVISTAS_081, ID_TAREA_081, ID_TAREA_REL_081, ID_REQUERIMIENTO_081, IND_NO_CONFORMIDAD_081, IND_FACTURABLE_081, ID_LOTE_081, NUM_EXP_081, OBSERV_081, ORDEN_081, RECURSO_ASIGNACION_081, ID_TIPO_TAREA_081, TIPO_ENTIDAD_081, ID_ENTIDAD_081, DNI_ASIGNADOR_081, ID_TIPO_REVISION_081) VALUES (ANYO,DNI_RESPONSABLE,ESTADO_TAREA,TAREA_PENDIENTE_EJECUCION,null,null,SYSDATE + 30 / (24*60),SYSDATE,'00:30',ID_TAREA,null,null,'N','N',null,NUM_EXP,null,0,'I',PROYECTO_TRADOS,null,null,null,null);

			        INSERT INTO AA79B82T00 (ID_TAREA_082) values (ID_TAREA);

			        insert into AA79B83T00 (ID_TAREA_083, ID_DOC_083)
					select ID_TAREA, ID_DOC_056 from AA79B56T00 where ANYO_056 = ANYO  AND
					NUM_EXP_056 = NUM_EXP AND ID_DOC_REL_056 is null AND ID_DOC_VERSION_056 is null;

					SELECT nvl(max(ID_ESTADO_BITACORA_059),0) + 1 INTO ESTADO_BITACORA FROM AA79B59S01 t1 WHERE NUM_EXP_059 = NUM_EXP AND ANYO_059 = ANYO;

			        INSERT INTO AA79B59S01 (ANYO_059, NUM_EXP_059, ID_ESTADO_BITACORA_059, ID_ESTADO_EXP_059, ID_FASE_EXPEDIENTE_059, DATO_ADIC_059, INFO_ADIC_059, ID_RECHAZO_059, ID_REQUERIMIENTO_059, FECHA_ALTA_059) VALUES (ANYO,NUM_EXP,ESTADO_BITACORA,EXPEDIENTE_EN_CURSO,FASE_EXP_PDTE_PROY_TRADOS,null,null,null,null, SYSDATE);

			        UPDATE AA79B51S01 SET ESTADO_BITACORA_051= ESTADO_BITACORA WHERE ANYO_051 = ANYO AND NUM_EXP_051 = NUM_EXP;

			        IF (DNI_RESPONSABLE != USUARIO) THEN

				        SELECT AA79BB2Q00.NEXTVAL INTO ID_PROCESO_EMAIL FROM dual;

				        SELECT DESC_ES_015, DESC_EU_015 into DESC_TAREA_ES, DESC_TAREA_EU FROM AA79B15T00 WHERE ID_015 = PROYECTO_TRADOS;

				        SELECT EMAIL_031 into EMAIL_DESTINATARIO FROM X54JAPI_DATOS_CONTACTO WHERE DNI_031 = DNI_RESPONSABLE;

				        INSERT INTO AA79BB2T00 (ID_PROCESO_EMAIL_0B2, NUM_EMAIL_0B2, ID_TIPO_AVISO_0B2, ANYO_0B2, NUM_EXP_0B2, TITULO_EXP_0B2, ID_TAREA_0B2, ID_TIPO_TAREA_0B2, DESC_TAREA_ES_0B2, DESC_TAREA_EU_0B2, DNI_DESTINATARIO_0B2, EMAIL_DESTINATARIO_0B2) VALUES (ID_PROCESO_EMAIL, NUM_EMAIL, TIPO_AVISO, ANYO, NUM_EXP, TITULO_EXPEDIENTE, ID_TAREA, PROYECTO_TRADOS, DESC_TAREA_ES, DESC_TAREA_EU, DNI_RESPONSABLE, EMAIL_DESTINATARIO);

				        NUM_EMAIL:= NUM_EMAIL + 1;

				        RETORNO:= ID_PROCESO_EMAIL;

				    ELSE

				     	RETORNO:= NULL;

				    END IF;
				END IF;

		    ELSE

		    	RETORNO:= NULL;

			END IF;
		END IF;



	SELECT VALOR_000 into OSAKIDETZA FROM AA79B00T00 WHERE ID_000 = 4;
	SELECT VALOR_000 into UPV FROM AA79B00T00 WHERE ID_000 = 5;
	SELECT TIPO_ENTIDAD_054, ID_ENTIDAD_054 into TIPO_ENTIDAD, ID_ENTIDAD FROM AA79B54T00 WHERE ANYO_054 = ANYO AND NUM_EXP_054 = NUM_EXP;

	IF ((ORIGEN = 'W' AND APLIC_ORIGEN = 'P43' AND ID_TIPO_EXPEDIENTE = TIPO_REVISION AND TIPO_ENTIDAD = 'B' AND (ID_ENTIDAD = OSAKIDETZA OR ID_ENTIDAD = UPV)) AND TIENETAREAS = 0) THEN


		SELECT COUNT(1) INTO CONTADOR FROM AA79B81T00 WHERE ID_TIPO_TAREA_081 = ENTREGA_CLIENTE_REVISION AND ANYO_081 = ANYO AND NUM_EXP_081 = NUM_EXP;
	 	IF (CONTADOR = 0) THEN
			SELECT AA79B81Q00.NEXTVAL INTO ID_TAREA FROM dual;

	 		ESTADO_TAREA:= ESTADO_PDTE_ACEPTACION_TAREA;
	    	IF (DNI_TECNICO = USUARIO) THEN
	    		ESTADO_TAREA:= ESTADO_ACEPTACION_TAREA;
	    	END IF;

	    	IF (CONTADORTRADOS = 0 AND NUM_TOTAL_PAL_IZO > LIM_PALABRAS) THEN
	    		FECHA_INICIO:= SYSDATE + 30 / (24*60);
	    		FECHA_FIN:= SYSDATE + 60 / (24*60);
	    	ELSE
	    		FECHA_INICIO:= SYSDATE;
	    		FECHA_FIN:= SYSDATE + 30 / (24*60);
	    	END IF;

			INSERT INTO AA79B81S01 (ANYO_081, DNI_RECURSO_081, ESTADO_ASIGNACION_081, ESTADO_EJECUCION_081, FECHA_ACEPTACION_081, FECHA_ASIGNACION_081, FECHA_FIN_081, FECHA_INICIO_081, HORAS_PREVISTAS_081, ID_TAREA_081, ID_TAREA_REL_081, ID_REQUERIMIENTO_081, IND_NO_CONFORMIDAD_081, IND_FACTURABLE_081, ID_LOTE_081, NUM_EXP_081, OBSERV_081, ORDEN_081, RECURSO_ASIGNACION_081, ID_TIPO_TAREA_081, TIPO_ENTIDAD_081, ID_ENTIDAD_081, DNI_ASIGNADOR_081, ID_TIPO_REVISION_081) VALUES (ANYO,DNI_TECNICO,ESTADO_TAREA,TAREA_PENDIENTE_EJECUCION,null,null,FECHA_FIN,FECHA_INICIO,'00:30',ID_TAREA,null,null,'N','N',null,NUM_EXP,null,1,'I',ENTREGA_CLIENTE_REVISION,null,null,null,1);
		    INSERT INTO AA79B82T00 (ID_TAREA_082) values (ID_TAREA);

		    insert into AA79B83T00 (ID_TAREA_083, ID_DOC_083)
			select ID_TAREA, ID_DOC_056 from AA79B56T00 where ANYO_056 = ANYO  AND
			NUM_EXP_056 = NUM_EXP AND ID_DOC_REL_056 is null AND ID_DOC_VERSION_056 is null;
		 END IF;

	END IF;



	RETURN RETORNO;

END;
/

exit;
