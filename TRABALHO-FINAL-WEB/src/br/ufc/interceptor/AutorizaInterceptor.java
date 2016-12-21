package br.ufc.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AutorizaInterceptor extends HandlerInterceptorAdapter{
	@Override
	public boolean preHandle(HttpServletRequest request,
							HttpServletResponse response, 
							Object handler) throws Exception {

		String uri = request.getRequestURI();		

		if(uri.endsWith("loginFormulario") || uri.endsWith("login") || uri.contains("resources")  ){
			return true;
		}
		
		if(request.getSession().getAttribute("funcionario_logado")!=null){
			return true;
		}
		
		response.sendRedirect("loginFormulario");
		return false;
	}

}
