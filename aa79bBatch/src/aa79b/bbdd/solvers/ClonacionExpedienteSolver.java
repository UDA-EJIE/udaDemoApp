package aa79b.bbdd.solvers; 

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import aa79b.bbdd.BaseSql;
import aa79b.bbdd.BaseSqlTransaccional;
import aa79b.bbdd.RowMapper;
import aa79b.util.beans.Clonacion;
import aa79b.util.common.AccesoBD;
import aa79b.util.common.AccesoBDTransaccional;
import aa79b.util.common.MailTextConstants;

/**
 * @author javarona
 *
 */
public class ClonacionExpedienteSolver {
		
	
	/*
	 * ROW_MAPPERS
	 */
	private final RowMapper<Clonacion> rwMap = new RowMapper<Clonacion>() {
		@Override
		public Clonacion mapRow(ResultSet resultSet, int rowNum) throws SQLException {
			final Clonacion clon = new Clonacion();
			clon.setId(resultSet.getLong("ID"));
			clon.setAnyoOrig(resultSet.getLong("ANYOORIG"));
			clon.setNumExpOrig(resultSet.getInt("NUMEXPORIG"));
			clon.setAnyoClon(resultSet.getLong("ANYOCLON"));
			clon.setNumExpClon(resultSet.getInt("NUMEXPCLON"));
			return clon;
		}
	};

	/**
	 *
	 * @param baseSqlTransaccional BaseSql
	 * @return List<DocumentosExpediente>
	 * @throws Exception e
	 */
	public List<Clonacion> leerInfoClonacion(BaseSqlTransaccional baseSqlTransaccional, Long idClonacion) throws Exception{
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();

		query.append("SELECT ID_0A8 ID, ANYO_ORIG_0A8 ANYOORIG, NUM_EXP_ORIG_0A8 NUMEXPORIG, ANYO_CLON_0A8 ANYOCLON, NUM_EXP_CLON_0A8 NUMEXPCLON ");
		query.append(" FROM AA79BA8S01 t1 WHERE ID_0A8 = ?");
		params.add(idClonacion);
		
		return new AccesoBDTransaccional().fncLanzaBusqueda(query.toString(), params.toArray(), this.rwMap, baseSqlTransaccional);
	}
	
	
	public Long getIdEstadoBitacoraOriginal(BaseSqlTransaccional baseSql, Clonacion clonacion) throws Exception {
        StringBuilder query = new StringBuilder();
        final List<Object> params = new ArrayList<Object>();
        query.append("SELECT ID_ESTADO_BITACORA_059 FROM AA79B59S01 ");
     	query.append("JOIN AA79B51S01 ON ANYO_059 = ANYO_051 AND NUM_EXP_059 = NUM_EXP_051 AND ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059 ");
     	query.append("WHERE ANYO_059 = ? AND NUM_EXP_059 = ?");
     	params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
        return Long.valueOf(new AccesoBDTransaccional().fncConsultaEspecifica(query.toString(), params.toArray(), baseSql));
    }


	public void copiaTabla51(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
		
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		query.append("INSERT INTO AA79B51S01 "); 
		query.append(" (ANYO_051,NUM_EXP_051,ID_TIPO_EXPEDIENTE_051,ORIGEN_051,FECHA_ALTA_051,TITULO_051,ESTADO_BITACORA_051,DNI_TECNICO_051,DNI_ASIGNADOR_051,IND_PRIORITARIO_051,FECHA_BAJA_051,ESTADO_BAJA_051,OBSV_BAJA_051) "); 
		query.append(" SELECT ? ,? ,ID_TIPO_EXPEDIENTE_051,'O',SYSDATE,TITULO_051,null,DNI_TECNICO_051,DNI_ASIGNADOR_051,IND_PRIORITARIO_051,FECHA_BAJA_051,ESTADO_BAJA_051,OBSV_BAJA_051 "); 
		query.append(" FROM AA79B51S01 WHERE ANYO_051 = ? AND NUM_EXP_051 = ? "); 
		
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
		
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}

	public void copiaTabla53(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		query.append(" INSERT INTO AA79B53S01 ( "); 
		query.append(" ANYO_053,NUM_EXP_053,IND_PUBLICAR_BOPV_053,IND_PREVISTO_BOPV_053,ID_IDIOMA_053,IND_CONFIDENCIAL_053,IND_CORREDACCION_053,TEXTO_053,TIPO_REDACCION_053,PARTICIPANTES_053, "); 
		query.append(" REF_TRAMITAGUNE_053,NUM_TOTAL_PAL_IZO_053,NUM_TOTAL_PAL_SOLIC_053,FECHA_FINAL_SOLIC_053,FECHA_FINAL_PROP_053,FECHA_FINAL_IZO_053,IND_FACTURABLE_053, "); 
		query.append(" ID_RELEVANCIA_053,IND_URGENTE_053,IND_DIFICIL_053,IND_PRESUPUESTO_053,IND_OBSERVACIONES_053,IND_PUBLICADO_BOE_053,URL_BOE_053,FECHA_ENTREGA_REAL_053 "); 
		query.append(" ) "); 
		query.append(" SELECT "); 
		query.append(" ? ,? ,IND_PUBLICAR_BOPV_053,IND_PREVISTO_BOPV_053,ID_IDIOMA_053,IND_CONFIDENCIAL_053,IND_CORREDACCION_053,TEXTO_053,TIPO_REDACCION_053,PARTICIPANTES_053, "); 
		query.append(" REF_TRAMITAGUNE_053,NUM_TOTAL_PAL_IZO_053,NUM_TOTAL_PAL_SOLIC_053,FECHA_FINAL_SOLIC_053,FECHA_FINAL_PROP_053,FECHA_FINAL_IZO_053,IND_FACTURABLE_053, "); 
		query.append(" ID_RELEVANCIA_053,IND_URGENTE_053,IND_DIFICIL_053,IND_PRESUPUESTO_053,IND_OBSERVACIONES_053,IND_PUBLICADO_BOE_053,URL_BOE_053,FECHA_ENTREGA_REAL_053 "); 
		query.append(" FROM AA79B53S01	WHERE ANYO_053 = ? AND NUM_EXP_053 = ? "); 
	
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
	
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	public void copiaTabla54(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		query.append(" INSERT INTO AA79B54S01 ( "); 
		query.append(" ANYO_054,NUM_EXP_054,DNI_SOLICITANTE_054,TIPO_ENTIDAD_054,ID_ENTIDAD_054,IND_VIP_054,DNI_REPRESENTANTE_054 "); 
		query.append(" ) SELECT "); 
		query.append(" ?,?,DNI_SOLICITANTE_054,TIPO_ENTIDAD_054,ID_ENTIDAD_054,IND_VIP_054,DNI_REPRESENTANTE_054 "); 
		query.append(" FROM AA79B54S01 WHERE ANYO_054 = ? AND NUM_EXP_054 = ? "); 
		
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
		
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	
	// observaciones
	public int copiaTabla55(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		query.append(" INSERT INTO AA79B55S01 ( "); 
		query.append(" ANYO_055,NUM_EXP_055,OBSERVACIONES_055,OID_FICHERO_055,EXTENSION_DOC_055,CONTENT_TYPE_055,NOMBRE_FICHERO_055,TAMANO_DOC_055 "); 
		query.append(" ) SELECT "); 
		query.append(" ?,?,OBSERVACIONES_055,OID_FICHERO_055,EXTENSION_DOC_055,CONTENT_TYPE_055,NOMBRE_FICHERO_055,TAMANO_DOC_055 "); 
		query.append(" FROM AA79B55S01 WHERE ANYO_055 = ? AND NUM_EXP_055 = ? "); 
		
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
		
		return new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}		
	
	
	// exp relacionados - tabla 57
	public int copiaTabla57(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		query.append(" INSERT INTO AA79B57S01 ( "); 
		query.append(" ANYO_057,NUM_EXP_057,ANYO_EXP_REL_057,NUM_EXP_REL_057 "); 
		query.append(" ) SELECT "); 
		query.append(" ?,?,ANYO_EXP_REL_057,NUM_EXP_REL_057 "); 
		query.append(" FROM AA79B57S01 WHERE ANYO_057 = ? AND NUM_EXP_057 = ? "); 
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
		return new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}		
	// exp relacionados - tabla 57
	public void relacionarExpedientes57(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
		final StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		query.append(" INSERT INTO AA79B57S01 ( "); 
		query.append(" ANYO_057,NUM_EXP_057,ANYO_EXP_REL_057,NUM_EXP_REL_057 "); 
		query.append(" )  VALUES (?,?,?,?)"); 
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
		
		params = new ArrayList<Object>();
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
		
	}		
					
	// facturacion - tabla 58
	public void copiaTabla58(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		query.append(" INSERT INTO AA79B58S01 (ID_058,ANYO_058,NUM_EXP_058,TIPO_ENTIDAD_ASOC_058,ID_ENTIDAD_ASOC_058,DNI_CONTACTO_058,POR_FACTURA_058) "); 
				query.append(" SELECT AA79B58Q00.NEXTVAL,ANYO_058,NUM_EXP_058,TIPO_ENTIDAD_ASOC_058,ID_ENTIDAD_ASOC_058,DNI_CONTACTO_058,POR_FACTURA_058 "); 
						query.append(" FROM ( SELECT ? ANYO_058,? NUM_EXP_058,TIPO_ENTIDAD_ASOC_058,ID_ENTIDAD_ASOC_058,DNI_CONTACTO_058,POR_FACTURA_058 FROM AA79B58S01 WHERE ANYO_058 =? AND NUM_EXP_058 =? ) "); 
	    
	    
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}	
	
	
		// exp relacionados - tabla 62
		public int copiaTabla62(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
			final StringBuilder query = new StringBuilder();
			final List<Object> params = new ArrayList<Object>();
			query.append(" INSERT INTO AA79B62S01 ( "); 
			query.append(" ANYO_062,NUM_EXP_062,ID_METADATO_062 "); 
			query.append(" ) SELECT "); 
			query.append(" ?,?,ID_METADATO_062 "); 
			query.append(" FROM AA79B62S01 WHERE ANYO_062 = ? AND NUM_EXP_062 = ? "); 
			params.add(clonacion.getAnyoClon());
			params.add(clonacion.getNumExpClon());
			params.add(clonacion.getAnyoOrig());
			params.add(clonacion.getNumExpOrig());
			return new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
		}	
		// exp relacionados - tabla 63
		public int copiaTabla63(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
			final StringBuilder query = new StringBuilder();
			final List<Object> params = new ArrayList<Object>();
			query.append(" INSERT INTO AA79B63S01 ( "); 
			query.append(" ANYO_063,NUM_EXP_063,DNI_RECEPTOR_063,TIPO_ENTIDAD_063,ID_ENTIDAD_063 "); 
			query.append(" ) SELECT "); 
			query.append(" ?,?,DNI_RECEPTOR_063,TIPO_ENTIDAD_063,ID_ENTIDAD_063 "); 
			query.append(" FROM AA79B63S01 WHERE ANYO_063 = ? AND NUM_EXP_063 = ? "); 
			params.add(clonacion.getAnyoClon());
			params.add(clonacion.getNumExpClon());
			params.add(clonacion.getAnyoOrig());
			params.add(clonacion.getNumExpOrig());
			return new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
		}	
	
	
	
	
	/**
	 * Creacion de la bitácora del expediente clonado - tabla 59 - y la asocia al expediente (T51)
	 * @param baseSql
	 * @param clonacion
	 * @return
	 * @throws Exception
	 */
	public void creacionBitacoraClonado(BaseSqlTransaccional baseSql, Clonacion clonacion ) throws Exception {
		StringBuilder query = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		
		query.append(" INSERT INTO AA79B59S01 ( ");
		query.append(" ANYO_059,NUM_EXP_059,ID_ESTADO_BITACORA_059,ID_ESTADO_EXP_059,ID_FASE_EXPEDIENTE_059,DATO_ADIC_059,INFO_ADIC_059,FECHA_ALTA_059 ");
		query.append(" ) SELECT  ?, ?, 1,ID_ESTADO_EXP_059,ID_FASE_EXPEDIENTE_059,DATO_ADIC_059,?,SYSDATE ");
		query.append(" FROM AA79B59S01 ");
		query.append(" JOIN AA79B51S01 ON ANYO_059 = ANYO_051 AND NUM_EXP_059 = NUM_EXP_051 AND ESTADO_BITACORA_051 = ID_ESTADO_BITACORA_059 ");
		query.append(" WHERE ANYO_059 = ? AND NUM_EXP_059 = ? ");
		
		params.add(clonacion.getAnyoClon());
		params.add(clonacion.getNumExpClon());
		params.add(MailTextConstants.EXPEDIENTE_CLONADO_EU);
		params.add(clonacion.getAnyoOrig());
		params.add(clonacion.getNumExpOrig());
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}		
					
					
	public void updateEstadoTablaA8(BaseSql baseSql, Long idClonacion, int nuevoEstado ) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		query.append(" UPDATE AA79BA8S01 SET ESTADO_CLON_0A8=? WHERE ID_0A8=? "); 
		
		params.add(nuevoEstado);
		params.add(idClonacion);
		
		new AccesoBD().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	public void updateEstadoTablaA8(BaseSqlTransaccional baseSql, Long idClonacion, int nuevoEstado ) throws Exception {
		final StringBuilder query = new StringBuilder();
		final List<Object> params = new ArrayList<Object>();
		
		query.append(" UPDATE AA79BA8S01 SET ESTADO_CLON_0A8=? WHERE ID_0A8=? "); 
		
		params.add(nuevoEstado);
		params.add(idClonacion);
		
		new AccesoBDTransaccional().fncLanzaMovimiento(query.toString(), params.toArray(), baseSql);
	}
	
}
