package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnectionBanco;
import model.ModelLogin;

public class DAOUsuarioRepository {
	
	private Connection connection;

	public DAOUsuarioRepository() {
		connection = SingleConnectionBanco.getConnection();
	}
	
	public ModelLogin gravarUsuario(ModelLogin objetoUsuario, Long userLogado) throws Exception {
		if(objetoUsuario.isNovo()) {
				String sql = "INSERT INTO model_login(login, senha, nome, email, usuario_id, perfil, sexo, cep, logradouro, bairro, localidade, uf, numero) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, objetoUsuario.getLogin());
				statement.setString(2, objetoUsuario.getSenha());
				statement.setString(3, objetoUsuario.getNome());
				statement.setString(4, objetoUsuario.getEmail());
				statement.setLong(5, userLogado);
				statement.setString(6, objetoUsuario.getPerfil());
				statement.setString(7, objetoUsuario.getSexo());
				statement.setString(8, objetoUsuario.getCep());
				statement.setString(9, objetoUsuario.getLogradouro());
				statement.setString(10, objetoUsuario.getBairro());
				statement.setString(11, objetoUsuario.getLocalidade());
				statement.setString(12, objetoUsuario.getUf());
				statement.setString(13, objetoUsuario.getNumero());
				
				statement.execute();
				connection.commit();
				
				if(objetoUsuario.getFotoUser() != null && !objetoUsuario.getFotoUser().isEmpty()) {
					sql = "UPDATE MODEL_LOGIN SET fotouser = ? , extensaofotouser = ? WHERE login = ?";
					
					statement = connection.prepareStatement(sql);
					statement.setString(1, objetoUsuario.getFotoUser());
					statement.setString(2, objetoUsuario.getExtensaoFotoUser());
					statement.setString(3, objetoUsuario.getLogin());
					
					statement.execute();
					connection.commit();
				}
				
				
			}else {
				String sql = "UPDATE model_login SET login=?, senha=?, nome=?, email=?, perfil=?, sexo=?, cep=?, logradouro=?, bairro=?, localidade=?, uf=?, numero=? WHERE id = "+objetoUsuario.getId()+";";
				PreparedStatement statement = connection.prepareStatement(sql);
				statement.setString(1, objetoUsuario.getLogin());
				statement.setString(2, objetoUsuario.getSenha());
				statement.setString(3, objetoUsuario.getNome());
				statement.setString(4, objetoUsuario.getEmail());
				statement.setString(5, objetoUsuario.getPerfil());
				statement.setString(6, objetoUsuario.getSexo());
				statement.setString(7, objetoUsuario.getCep());
				statement.setString(8, objetoUsuario.getLogradouro());
				statement.setString(9, objetoUsuario.getBairro());
				statement.setString(10, objetoUsuario.getLocalidade());
				statement.setString(11, objetoUsuario.getUf());
				statement.setString(12, objetoUsuario.getNumero());
				
				statement.executeUpdate();
				connection.commit();
				
				if(objetoUsuario.getFotoUser() != null) {
					sql = "UPDATE MODEL_LOGIN SET fotouser = ? , extensaofotouser = ? WHERE id = ?";
					
					statement = connection.prepareStatement(sql);
					statement.setString(1, objetoUsuario.getFotoUser());
					statement.setString(2, objetoUsuario.getExtensaoFotoUser());
					statement.setLong(3, objetoUsuario.getId());
					
					statement.execute();
					connection.commit();
				}
			}
			return this.consultarUsuario(objetoUsuario.getLogin(), userLogado);
	}
	
	
	public List<ModelLogin> buscarUsuarioLista(String nome, Long userLogado) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login WHERE upper(nome) LIKE upper('%"+nome+"%') and useradmin is false and usuario_id = " + userLogado + " order by nome limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			
			retorno.add(modelLogin);
		}
		return retorno;
	}
	
	
	
		public List<ModelLogin> buscarUsuarioListaOffSet(String nome, Long userLogado, String offset) throws Exception{
				
				List<ModelLogin> retorno = new ArrayList<ModelLogin>();
				
				String sql = "SELECT * FROM model_login WHERE upper(nome) LIKE upper('%"+nome+"%') and useradmin is false and usuario_id = " + userLogado + " order by nome offset " + offset + " limit 5";
				PreparedStatement statement = connection.prepareStatement(sql);
				ResultSet resultado = statement.executeQuery();
				while(resultado.next()) {
					ModelLogin modelLogin = new ModelLogin();
					modelLogin.setEmail(resultado.getString("email"));
					modelLogin.setId(resultado.getLong("id"));
					modelLogin.setLogin(resultado.getString("login"));
					modelLogin.setNome(resultado.getString("nome"));
					modelLogin.setSexo(resultado.getString("sexo"));
					modelLogin.setPerfil(resultado.getString("perfil"));
					
					retorno.add(modelLogin);
				}
				return retorno;
			}
	
	public int buscarUsuarioListaTotalPagina(String nome, Long userLogado) throws Exception{
		
		String sql = "SELECT COUNT(1) AS Total FROM model_login WHERE upper(nome) LIKE upper('%"+nome+"%') and useradmin is false and usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		resultado.next();
		Double cadastros = resultado.getDouble("Total");
		Double porpagina = 5.0;
		Double pagina = cadastros/porpagina;
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();
	}
	
	public ModelLogin consultarUsuario(String login, Long userLogado) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper(?) and useradmin is false and usuario_id = " + userLogado + " limit 5";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		ResultSet resultado =  statement.executeQuery();
		
		while(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotoUser(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));			
			
		}
		
		
		return modelLogin; 
		
	}
	
public ModelLogin consultarUsuarioLogado(String login) throws Exception {
		
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
			modelLogin.setUseradmin(resultado.getBoolean("useradmin"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotoUser(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}
		
		
		return modelLogin; 
		
	}
	
public ModelLogin consultarUsuario(String login) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE upper(login) = upper(?) and useradmin is false";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, login);
		
		ResultSet resultado =  statement.executeQuery();
		
		while(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotoUser(resultado.getString("fotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
		}
		
		
		return modelLogin; 
		
	}
	
	public ModelLogin consultarUsuarioPorId(String id, Long userLogado) throws Exception {
		
		ModelLogin modelLogin = new ModelLogin();
		
		String sql = "SELECT * FROM model_login WHERE id = ? and useradmin is false and usuario_id = ?";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.setLong(2, userLogado);
		
		ResultSet resultado =  statement.executeQuery();
		
		while(resultado.next()) {
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setSenha(resultado.getString("senha"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			modelLogin.setFotoUser(resultado.getString("fotouser"));
			modelLogin.setExtensaoFotoUser(resultado.getString("extensaofotouser"));
			modelLogin.setCep(resultado.getString("cep"));
			modelLogin.setLogradouro(resultado.getString("logradouro"));
			modelLogin.setBairro(resultado.getString("bairro"));
			modelLogin.setLocalidade(resultado.getString("localidade"));
			modelLogin.setUf(resultado.getString("uf"));
			modelLogin.setNumero(resultado.getString("numero"));
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
	
	public void deletarUsuario(String id) throws Exception {
		String sql = "DELETE FROM model_login WHERE id = ? and useradmin is false;";
		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setLong(1, Long.parseLong(id));
		statement.executeUpdate();
		connection.commit();
	}
	
	public List<ModelLogin> buscarUsuarioListaTela(Long userLogado) throws Exception{
		
		List<ModelLogin> retorno = new ArrayList<ModelLogin>();
		
		String sql = "SELECT * FROM model_login where useradmin is false and usuario_id = " + userLogado + " order by nome limit 5";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		while(resultado.next()) {
			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setEmail(resultado.getString("email"));
			modelLogin.setId(resultado.getLong("id"));
			modelLogin.setLogin(resultado.getString("login"));
			modelLogin.setNome(resultado.getString("nome"));
			modelLogin.setPerfil(resultado.getString("perfil"));
			modelLogin.setSexo(resultado.getString("sexo"));
			//modelLogin.setSenha(resultado.getString("senha"));
			
			retorno.add(modelLogin);
		}
		return retorno;
	}
	
	public int totalPagina(Long userLogado) throws Exception {
		
		String sql = "SELECT COUNT(1) as Total FROM model_login WHERE usuario_id = " + userLogado;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		resultado.next();
		Double cadastros = resultado.getDouble("Total");
		Double porpagina = 5.0;
		Double pagina = cadastros/porpagina;
		Double resto = pagina % 2;
		
		if(resto > 0) {
			pagina++;
		}
		
		return pagina.intValue();
		
	}
	
	public List<ModelLogin> buscarUsuarioListaTelaPaginada(Long userLogado, Integer offset) throws Exception{
			
			List<ModelLogin> retorno = new ArrayList<ModelLogin>();
			
			String sql = "SELECT * FROM model_login where useradmin is false and usuario_id = " + userLogado + " ORDER BY nome OFFSET " + offset + " LIMIT 5";
			
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			while(resultado.next()) {
				ModelLogin modelLogin = new ModelLogin();
				modelLogin.setEmail(resultado.getString("email"));
				modelLogin.setId(resultado.getLong("id"));
				modelLogin.setLogin(resultado.getString("login"));
				modelLogin.setNome(resultado.getString("nome"));
				modelLogin.setPerfil(resultado.getString("perfil"));
				modelLogin.setSexo(resultado.getString("sexo"));
				//modelLogin.setSenha(resultado.getString("senha"));
				
				retorno.add(modelLogin);
			}
			return retorno;
		}
	
	
	
	
}
