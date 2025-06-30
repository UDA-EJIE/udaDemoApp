package aa79b.mains;

import aa79b.batch.ClonarExpediente;

/**
 * w22ValidacionTest
 * @author hterron
 *
 */
public class TestClonacion {

	/**
	 * w22ValidacionTest
	 */
	private TestClonacion(){
	}
	/**
	 * @autor hterron
	 * @param args String[]
	 */
	public static void main(String[] args) {
		
//		String DNS = "jdbc:oracle:thin:@ejld1180:1530/x54.ejie.eus";
		String DNS = "jdbc:oracle:thin:@172.16.0.59:1521:ora12";
		String USUARIO_XLNETS = "aa79b";
		String PASSWORD = "aa79b";
		String DB_DRIVER = "oracle.jdbc.OracleDriver";
		String idClonacion0A8 = "89";
	
 
		String[] params = new String[]{
				USUARIO_XLNETS,
				PASSWORD,
				DB_DRIVER ,
				DNS, idClonacion0A8
		};	
		
		ClonarExpediente.main(params);

	}

	
}
