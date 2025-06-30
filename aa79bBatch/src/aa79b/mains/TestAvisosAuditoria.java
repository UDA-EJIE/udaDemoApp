package aa79b.mains;

import aa79b.batch.AvisosAuditorias;

public class TestAvisosAuditoria {

	/**
	 * @param args
	 */
	public static void main(String[] args) {


		String dbUsuario= "AA79B";
		String dbPassword = "AA79B";
		String dbDriver = "oracle.jdbc.OracleDriver";
		String dbServerLocal = "jdbc:oracle:thin:@172.16.0.59:1521:ORA12";
		String dbServerDesa = "jdbc:oracle:thin:@ejld1180:1530/x54.ejie.eus";



		String[] params = new String[]{
				dbUsuario, dbPassword, dbDriver, dbServerDesa};

		AvisosAuditorias.main(params);


	}
}
