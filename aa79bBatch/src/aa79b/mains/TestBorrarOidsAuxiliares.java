package aa79b.mains;

import aa79b.batch.BorrarOidsAuxiliares;

/**
 * w22ValidacionTest
 * @author hterron
 *
 */
public class TestBorrarOidsAuxiliares {

	/**
	 * w22ValidacionTest
	 */
	private TestBorrarOidsAuxiliares(){
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
	
 
		String[] params = new String[]{
				USUARIO_XLNETS,
				PASSWORD,
				DB_DRIVER ,
				DNS
		};	
		
		BorrarOidsAuxiliares.main(params);

	}

	
}
