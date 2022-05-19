package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOLoginRepository {
	
	private Connection connection;
	
	public DAOLoginRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public Boolean verificaSessao(ModelLogin modelLogin) throws SQLException {
		
		String sql = " SELECT * FROM model_login WHERE upper(login) = upper(?) and senha = ? ";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, modelLogin.getLogin());
		statement.setString(2, modelLogin.getSenha());
		
		ResultSet resultado = statement.executeQuery();
		
		if(resultado.next()) {
			return true; //Se autenticado
		}
		
		
		return false; //Se não autenticado
	}

}
