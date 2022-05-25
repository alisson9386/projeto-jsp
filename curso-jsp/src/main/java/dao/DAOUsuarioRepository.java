package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public void gravarUsuario(ModelLogin objetoUsuario) throws SQLException {
		
			
			String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?)";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, objetoUsuario.getLogin());
			statement.setString(2, objetoUsuario.getSenha());
			statement.setString(3, objetoUsuario.getNome());
			statement.setString(4, objetoUsuario.getEmail());
			
			statement.execute();
			connection.commit();		
	}
	
}
