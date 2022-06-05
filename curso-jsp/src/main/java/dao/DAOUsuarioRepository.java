package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objetoUsuario) throws SQLException {
			if(objetoUsuario.isNovo()) {
				String sql = "INSERT INTO model_login(login, senha, nome, email) VALUES (?, ?, ?, ?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, objetoUsuario.getLogin());
				statement.setString(2, objetoUsuario.getSenha());
				statement.setString(3, objetoUsuario.getNome());
				statement.setString(4, objetoUsuario.getEmail());
				
				statement.execute();
				connection.commit();
				
			}else {
				String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=? WHERE id = "+objetoUsuario.getId()+";";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, objetoUsuario.getLogin());
				statement.setString(2, objetoUsuario.getSenha());
				statement.setString(3, objetoUsuario.getNome());
				statement.setString(4, objetoUsuario.getEmail());
				
				statement.executeUpdate();
				connection.commit();
			}
				
			
			
			
			
			return this.consultarUsuario(objetoUsuario.getLogin());
	}
	
	public ModelLogin consultarUsuario(String login) throws SQLException {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper(?)";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		ResultSet resultado =  statement.executeQuery();
		
		while(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
		}
		
		
		return modelLogin; 
		
	}
	
	public boolean validaLogin(String login) throws Exception {
		String sql = "SELECT COUNT(1) > 0 as existe FROM model_login WHERE upper(login) = upper('" + login + "')";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado =  statement.executeQuery();
		
		resultado.next();
		return resultado.getBoolean("existe");
	}
	
	public void deletarUsuario(String login) throws Exception {
		String sql = "DELETE FROM model_login WHERE login = ?;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		statement.executeUpdate();
		connection.commit();
	}
	
}
