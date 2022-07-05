package servlets;

import java.io.IOException;
import java.util.List;

import org.apache.tomcat.jakartaee.commons.compress.utils.IOUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.DAOUsuarioRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import model.ModelLogin;

@MultipartConfig
@WebServlet(urlPatterns = { "/ServletUsuarioController"})
public class ServletUsuarioController extends ServletGenericUtil {

	private static final long serialVersionUID = 1L;

	private DAOUsuarioRepository daoUsuarioRepository = new DAOUsuarioRepository();

	public ServletUsuarioController() {
		super();
	}

	@SuppressWarnings("static-access")
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");

			if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletar")) {
				String id = request.getParameter("idUsuario");

				daoUsuarioRepository.deletarUsuario(id);
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarioListaTela(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.setAttribute("msg", "Excluído com sucesso!");
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("deletarajax")) {
				String id = request.getParameter("id");

				daoUsuarioRepository.deletarUsuario(id);

				response.getWriter().write("Excluído com sucesso!");

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarUserAjax")) {
				
				String nomeBusca = request.getParameter("nomeBusca");
				
				List<ModelLogin> dadosJsonUser = daoUsuarioRepository.buscarUsuarioLista(nomeBusca, super.getUserLogado(request));
				ObjectMapper mapper = new ObjectMapper();
				String json = mapper.writeValueAsString(dadosJsonUser);
				response.getWriter().write(json);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("buscarEditar")) {
				
				String id = request.getParameter("id");
				
				ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioPorId(id, super.getUserLogado(request));
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarioListaTela(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuário em edição.");
				request.setAttribute("modelLogin", modelLogin);
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("listarUser")) {
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarioListaTela(super.getUserLogado(request));
				
				request.setAttribute("msg", "Usuários carregados.");
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

			} else if (acao != null && !acao.isEmpty() && acao.equalsIgnoreCase("downloadFoto")) {
				
				String id = request.getParameter("id");
				ModelLogin modelLogin = daoUsuarioRepository.consultarUsuarioPorId(id, super.getUserLogado(request));
				if(modelLogin.getFotoUser() != null && !modelLogin.getFotoUser().isEmpty()) {
					response.setHeader("Content-Disposition", "attachment;filename=arquivo." + modelLogin.getExtensaoFotoUser());
					response.getOutputStream().write(new Base64().decodeBase64(modelLogin.getFotoUser().split("\\,")[1]));
				}
				
			
			}else {
			
				
				List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarioListaTela(super.getUserLogado(request));
				request.setAttribute("modelLogins", modelLogins);
				request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			String msg = "Operação realizada com sucesso!";

			String id = request.getParameter("idUsuario");
			String nome = request.getParameter("nome");
			String email = request.getParameter("email");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String perfil = request.getParameter("perfil");
			String sexo = request.getParameter("sexo");

			ModelLogin modelLogin = new ModelLogin();
			modelLogin.setId(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			modelLogin.setNome(nome);
			modelLogin.setEmail(email);
			modelLogin.setLogin(login);
			modelLogin.setSenha(senha);
			modelLogin.setPerfil(perfil);
			modelLogin.setSexo(sexo);
			
			if(ServletFileUpload.isMultipartContent(request)) {
				Part part = request.getPart("fileFoto"); //Pega foto da tela
				if(part.getSize() > 0) {
					byte [] foto = IOUtils.toByteArray(part.getInputStream());
					@SuppressWarnings("static-access")
					String imagemBase64 = "data:" + part.getContentType() + ";base64," + new Base64().encodeBase64String(foto);
					
					modelLogin.setFotoUser(imagemBase64);
					modelLogin.setExtensaoFotoUser(part.getContentType().split("\\/")[1]);
				}
			}

			if (daoUsuarioRepository.validaLogin(modelLogin.getLogin()) && modelLogin.getId() == null) {
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
				msg = "Usuário atualizado!";
			} else {
				modelLogin = daoUsuarioRepository.gravarUsuario(modelLogin, super.getUserLogado(request));
			}
			
			List<ModelLogin> modelLogins = daoUsuarioRepository.buscarUsuarioListaTela(super.getUserLogado(request));
			request.setAttribute("modelLogins", modelLogins);
			request.setAttribute("msg", msg);
			request.setAttribute("modelLogin", modelLogin);
			request.getRequestDispatcher("principal/usuario.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}

	}

}
