package filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@WebFilter(urlPatterns = {"/principal/*"}) //Intercepta todas as requisições que vierem do projeto ou mapeamento
public class FilterAutenticacao implements Filter {
     


	public FilterAutenticacao() {
        super();
        
    }

	//Encerra os processos quando o servidor é parado
	public void destroy() {
		
	}

	//Intercepta todas as requisições e da as respostas no sistema
	//Tudo passara por aqui, por exemplo: Validação de autenticação, dar commit e rolback de transações no banco, validar e refazer redirecionamento de paginas, etc
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		String usuarioLogado = (String) session.getAttribute("usuario");
		
		String urlParaAutenticar = req.getServletPath(); //Url que esta sendo acessada
		
		/*Validar se está logado, se nao, redirecionar pra tela de login*/
		if(usuarioLogado == null && !urlParaAutenticar.equalsIgnoreCase("/principal/ServletLogin")){
			RequestDispatcher redireciona = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
			request.setAttribute("msg", "Por favor, realize o login");
			redireciona.forward(request, response);
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	//Iniicia os processos ou recursos quando o servidor sobe o projeto
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
