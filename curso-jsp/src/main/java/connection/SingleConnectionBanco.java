package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnectionBanco {
	
	private static String banco = "jdbc:postgresql://localhost:5432/curso-jsp?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "Z0rr0b@tmak";
	private static Connection connection = null;
	
	public static Connection getConnection() {
		return connection;
	}
	
	static {
		conectar();
	}
	
	public SingleConnectionBanco() { //Conecta quando tem instância
		conectar();
	}
	
	private static void conectar() {
		try {
			
			if(connection == null) {
				//DriverManager.registerDriver(new org.postgresql.Driver()); //Carrega drive de conexão do banco
				Class.forName("org.postgresql.Driver");//Carrega drive de conexão do banco2
				connection = DriverManager.getConnection(banco, user, senha);
				connection.setAutoCommit(false); //Para nao efetuar alteração no banco sem nosso comando
				System.out.println("Banco conectado com sucesso");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
