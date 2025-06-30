package com.ejie.aa79b.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ejie.aa79b.common.SqlUtils;
import com.ejie.aa79b.dao.mapper.ExpedienteInterpretacionRowMapper;
import com.ejie.aa79b.model.Expediente;
import com.ejie.aa79b.model.enums.ActivoEnum;
import com.ejie.aa79b.utils.DateUtils;
import com.ejie.aa79b.utils.Utils;

@Repository
@Transactional
public class ExpedienteInterpretacionDaoImpl extends GenericoDaoImpl<Expediente>
        implements ExpedienteInterpretacionDao {

    @Autowired()
    private ReloadableResourceBundleMessageSource msg;

    protected static final String ANYO_EXPEDIENTE_INTERPRETACION = "ANYO_052";
    protected static final String NUM_EXP_EXPEDIENTE_INTERPRETACION = "NUM_EXP_052";
    protected static final String MODO_INTERPRETACION_EXPEDIENTE_INTERPRETACION = "MODO_INTERPRETACION_052";
    protected static final String TIPO_ACTO_EXPEDIENTE_INTERPRETACION = "TIPO_ACTO_052";
    protected static final String TIPO_PETICIONARIO_EXPEDIENTE_INTERPRETACION = "TIPO_PETICIONARIO_052";
    protected static final String IND_PROGRAMADA_EXPEDIENTE_INTERPRETACION = "IND_PROGRAMADA_052";
    protected static final String FECHA_INI_EXPEDIENTE_INTERPRETACION = "FECHA_INI_052";
    protected static final String HORA_INI_EXPEDIENTE_INTERPRETACION = "HORA_INI_052";
    protected static final String FECHA_FIN_EXPEDIENTE_INTERPRETACION = "FECHA_FIN_052";
    protected static final String HORA_FIN_EXPEDIENTE_INTERPRETACION = "HORA_FIN_052";
    protected static final String DIR_NORA_EXPEDIENTE_INTERPRETACION = "DIR_NORA_052";
    protected static final String IND_PRESUPUESTO_EXPEDIENTE_INTERPRETACION = "IND_PRESUPUESTO_052";
    protected static final String PRESUPUESTO_DESC_ES_EXPEDIENTE_INTERPRETACION = "PRESUPUESTODESCES";
    protected static final String PRESUPUESTO_DESC_EU_EXPEDIENTE_INTERPRETACION = "PRESUPUESTODESCEU";
    protected static final String PERSONA_CONTACTO_EXPEDIENTE_INTERPRETACION = "PERSONA_CONTACTO_052";
    protected static final String EMAIL_CONTACTO_EXPEDIENTE_INTERPRETACION = "EMAIL_CONTACTO_052";
    protected static final String TELEFONO_CONTACTO_EXPEDIENTE_INTERPRETACION = "TELEFONO_CONTACTO_052";
    protected static final String IND_OBSERVACIONES_EXPEDIENTE_INTERPRETACION = "IND_OBSERVACIONES_052";

    private static final String[] ORDER_BY_WHITE_LIST = new String[] { ANYO_EXPEDIENTE_INTERPRETACION,
            NUM_EXP_EXPEDIENTE_INTERPRETACION, MODO_INTERPRETACION_EXPEDIENTE_INTERPRETACION,
            TIPO_ACTO_EXPEDIENTE_INTERPRETACION, TIPO_PETICIONARIO_EXPEDIENTE_INTERPRETACION,
            IND_PROGRAMADA_EXPEDIENTE_INTERPRETACION, FECHA_INI_EXPEDIENTE_INTERPRETACION,
            HORA_INI_EXPEDIENTE_INTERPRETACION, FECHA_FIN_EXPEDIENTE_INTERPRETACION, HORA_FIN_EXPEDIENTE_INTERPRETACION,
            DIR_NORA_EXPEDIENTE_INTERPRETACION, IND_PRESUPUESTO_EXPEDIENTE_INTERPRETACION,
            PRESUPUESTO_DESC_ES_EXPEDIENTE_INTERPRETACION, PRESUPUESTO_DESC_EU_EXPEDIENTE_INTERPRETACION,
            PERSONA_CONTACTO_EXPEDIENTE_INTERPRETACION, EMAIL_CONTACTO_EXPEDIENTE_INTERPRETACION,
            TELEFONO_CONTACTO_EXPEDIENTE_INTERPRETACION, IND_OBSERVACIONES_EXPEDIENTE_INTERPRETACION };

    /*
     * ROW_MAPPERS
     */
    private RowMapper<Expediente> rwMap = new ExpedienteInterpretacionRowMapper();

    private RowMapper<Expediente> rwMapPK = new RowMapper<Expediente>() {
        @Override
        public Expediente mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Expediente expediente = new Expediente();
            expediente.setAnyo(resultSet.getLong(ANYO_EXPEDIENTE_INTERPRETACION));
            expediente.setNumExp(resultSet.getInt(NUM_EXP_EXPEDIENTE_INTERPRETACION));

            return expediente;
        }
    };

    /**
     * Constructor
     */
    public ExpedienteInterpretacionDaoImpl() {
        super(Expediente.class);
    }

    /**
     * Inserts a single row in the ExpedienteInterpretacion table.
     *
     * @param expediente
     *            Expediente
     * @return Expediente
     */
    @Override
    public Expediente add(Expediente expediente) {
        Date fechaIni = expediente.getExpedienteInterpretacion().getFechaIni();
        String horaIni = expediente.getExpedienteInterpretacion().getHoraIni();

        Date fechaFin = expediente.getExpedienteInterpretacion().getFechaFin();
        String horaFin = expediente.getExpedienteInterpretacion().getHoraFin();

        // Comprobar si existe ANYO_052 + NUM_EXP_052
        String query = "INSERT INTO AA79B52S01 (ANYO_052, NUM_EXP_052, MODO_INTERPRETACION_052, TIPO_ACTO_052, "
                + "TIPO_PETICIONARIO_052, IND_PROGRAMADA_052, FECHA_INI_052, FECHA_FIN_052, DIR_NORA_052, "
                + "IND_PRESUPUESTO_052, PERSONA_CONTACTO_052, EMAIL_CONTACTO_052, TELEFONO_CONTACTO_052, IND_OBSERVACIONES_052) "
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        this.getJdbcTemplate().update(query, expediente.getAnyo(), expediente.getNumExp(),
                expediente.getExpedienteInterpretacion().getModoInterpretacion(),
                expediente.getExpedienteInterpretacion().getTipoActo(),
                expediente.getExpedienteInterpretacion().getTipoPeticionario(),
                Utils.getValueInd(expediente.getExpedienteInterpretacion().getIndProgramada()),
                DateUtils.obtFechaHoraFormateada(fechaIni, horaIni),
                DateUtils.obtFechaHoraFormateada(fechaFin, horaFin),
                expediente.getExpedienteInterpretacion().getDirNora().getDirNora(),
                Utils.getValueInd(expediente.getExpedienteInterpretacion().getIndPresupuesto()),
                expediente.getExpedienteInterpretacion().getPersonaContacto(),
                expediente.getExpedienteInterpretacion().getEmailContacto(),
                expediente.getExpedienteInterpretacion().getTelefonoContacto(),
                Utils.getValueInd(expediente.getExpedienteInterpretacion().getIndObservaciones()));

        return expediente;
    }

    /**
     * Updates a single row in the MetadatosBusqueda table.
     *
     * @param expedienteInterpretacion
     *            ExpedienteInterpretacion
     * @return ExpedienteInterpretacion
     */
    @Override
    public Expediente update(Expediente expediente) {
        Date fechaIni = expediente.getExpedienteInterpretacion().getFechaIni();
        String horaIni = expediente.getExpedienteInterpretacion().getHoraIni();

        Date fechaFin = expediente.getExpedienteInterpretacion().getFechaFin();
        String horaFin = expediente.getExpedienteInterpretacion().getHoraFin();

        String query = "UPDATE AA79B52S01 SET MODO_INTERPRETACION_052=?, TIPO_ACTO_052=?,"
                + "TIPO_PETICIONARIO_052=?, IND_PROGRAMADA_052=?, FECHA_INI_052=?, FECHA_FIN_052=?, "
                + "DIR_NORA_052=?, IND_PRESUPUESTO_052=?, PERSONA_CONTACTO_052=?, EMAIL_CONTACTO_052=?, "
                + "TELEFONO_CONTACTO_052=?, IND_OBSERVACIONES_052=? WHERE ANYO_052=? AND NUM_EXP_052=?";
        this.getJdbcTemplate().update(query, expediente.getExpedienteInterpretacion().getModoInterpretacion(),
                expediente.getExpedienteInterpretacion().getTipoActo(),
                expediente.getExpedienteInterpretacion().getTipoPeticionario(),
                Utils.getValueInd(expediente.getExpedienteInterpretacion().getIndProgramada()),
                DateUtils.obtFechaHoraFormateada(fechaIni, horaIni),
                DateUtils.obtFechaHoraFormateada(fechaFin, horaFin),
                expediente.getExpedienteInterpretacion().getDirNora().getDirNora(),
                Utils.getValueInd(expediente.getExpedienteInterpretacion().getIndPresupuesto()),
                expediente.getExpedienteInterpretacion().getPersonaContacto(),
                expediente.getExpedienteInterpretacion().getEmailContacto(),
                expediente.getExpedienteInterpretacion().getTelefonoContacto(),
                Utils.getValueInd(expediente.getExpedienteInterpretacion().getIndObservaciones()), expediente.getAnyo(),
                expediente.getNumExp());

        return expediente;
    }

    @Override
    protected String getSelect() {
        Locale es = new Locale("es");
        Locale eu = new Locale("eu");

        StringBuilder query = new StringBuilder();
        query.append("SELECT t1.ANYO_052 ANYO_052");
        query.append(", t1.NUM_EXP_052 NUM_EXP_052");
        query.append(", t1.MODO_INTERPRETACION_052 MODO_INTERPRETACION_052");
        query.append(", t1.TIPO_ACTO_052 TIPO_ACTO_052");
        query.append(", t1.TIPO_PETICIONARIO_052 TIPO_PETICIONARIO_052");
        query.append(", t1.IND_PROGRAMADA_052 IND_PROGRAMADA_052");
        query.append(", t1.FECHA_INI_052 FECHA_INI_052");
        query.append(", TO_CHAR(t1.FECHA_INI_052,'HH24:MI') HORA_INI_052");
        query.append(", t1.FECHA_FIN_052 FECHA_FIN_052");
        query.append(", TO_CHAR(t1.FECHA_FIN_052,'HH24:MI') HORA_FIN_052");
        query.append(", t1.DIR_NORA_052 DIR_NORA_052");
        query.append(", t1.IND_PRESUPUESTO_052 IND_PRESUPUESTO_052");
        query.append(", DECODE(t1.IND_PRESUPUESTO_052, '").append(ActivoEnum.SI.getValue()).append("', '")
                .append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, es)).append("', '")
                .append(ActivoEnum.NO.getValue()).append("', '")
                .append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, es)).append("') AS PRESUPUESTODESCES");
        query.append(", DECODE(t1.IND_PRESUPUESTO_052, '").append(ActivoEnum.SI.getValue()).append("', '")
                .append(this.msg.getMessage(ActivoEnum.SI.getLabel(), null, eu)).append("', '")
                .append(ActivoEnum.NO.getValue()).append("', '")
                .append(this.msg.getMessage(ActivoEnum.NO.getLabel(), null, eu)).append("') AS PRESUPUESTODESCEU");
        query.append(", t1.PERSONA_CONTACTO_052 PERSONA_CONTACTO_052");
        query.append(", t1.EMAIL_CONTACTO_052 EMAIL_CONTACTO_052");
        query.append(", t1.TELEFONO_CONTACTO_052 TELEFONO_CONTACTO_052");
        query.append(", t1.IND_OBSERVACIONES_052 IND_OBSERVACIONES_052");

        return query.toString();
    }

    @Override
    protected String getFrom() {
        return " FROM AA79B52S01 t1 ";
    }

    @Override
    protected RowMapper<Expediente> getRwMap() {
        return this.rwMap;
    }

    @Override
    protected String[] getOrderBy() {
        return ExpedienteInterpretacionDaoImpl.ORDER_BY_WHITE_LIST;
    }

    @Override
    protected String getPK() {
        return ANYO_EXPEDIENTE_INTERPRETACION + ", " + NUM_EXP_EXPEDIENTE_INTERPRETACION;
    }

    @Override
    protected RowMapper<Expediente> getRwMapPK() {
        return this.rwMapPK;
    }

    @Override
    protected String getWherePK(Expediente bean, List<Object> params) {
        params.add(bean.getAnyo());
        params.add(bean.getNumExp());
        return " WHERE t1.ANYO_052 = ? AND t1.NUM_EXP_052 = ?";
    }

    @Override
    protected String getWhere(Expediente bean, List<Object> params) {
        return this.getWhereClause(bean, params);
    }

    @Override
    protected String getWhereLike(Expediente bean, Boolean startsWith, List<Object> params, Boolean search) {
        return this.getWhereClause(bean, params);
    }

    /**
     * @param bean
     * @param params
     * @return
     */
    private String getWhereClause(Expediente bean, List<Object> params) {
        StringBuilder where = new StringBuilder(ExpedienteInterpretacionDaoImpl.STRING_BUILDER_INIT);

        if (bean != null && bean.getExpedienteInterpretacion() != null) {
            where.append(SqlUtils.generarWhereIgual(ANYO_EXPEDIENTE_INTERPRETACION, bean.getAnyo(), params));
            where.append(SqlUtils.generarWhereIgual(NUM_EXP_EXPEDIENTE_INTERPRETACION, bean.getNumExp(), params));
            where.append(SqlUtils.generarWhereIgual(MODO_INTERPRETACION_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getModoInterpretacion(), params));
            where.append(SqlUtils.generarWhereIgual(TIPO_ACTO_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getTipoActo(), params));
            where.append(SqlUtils.generarWhereIgual(TIPO_PETICIONARIO_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getTipoPeticionario(), params));
            where.append(SqlUtils.generarWhereIgual(IND_PROGRAMADA_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getIndProgramada(), params));
            where.append(SqlUtils.generarWhereIgual(FECHA_INI_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getFechaIni(), params));
            where.append(SqlUtils.generarWhereIgual(FECHA_FIN_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getFechaFin(), params));
            if (bean.getExpedienteInterpretacion().getDirNora() != null) {
                where.append(SqlUtils.generarWhereIgual(DIR_NORA_EXPEDIENTE_INTERPRETACION,
                        bean.getExpedienteInterpretacion().getDirNora().getDirNora(), params));
            }
            where.append(SqlUtils.generarWhereIgual(IND_PRESUPUESTO_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getIndPresupuesto(), params));
            where.append(SqlUtils.generarWhereIgual(PERSONA_CONTACTO_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getPersonaContacto(), params));
            where.append(SqlUtils.generarWhereIgual(EMAIL_CONTACTO_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getEmailContacto(), params));
            where.append(SqlUtils.generarWhereIgual(TELEFONO_CONTACTO_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getTelefonoContacto(), params));
            where.append(SqlUtils.generarWhereIgual(IND_OBSERVACIONES_EXPEDIENTE_INTERPRETACION,
                    bean.getExpedienteInterpretacion().getIndObservaciones(), params));
        }

        return where.toString();
    }

}
