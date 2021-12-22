package com.myspring.springBBS.Login;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//public class LoginInterceptor implements HandlerInterceptor {
public class LoginInterceptor extends HandlerInterceptorAdapter{
  private static final Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
			HttpSession session = request.getSession();
			
			LoginDTO boxer = null;
			if(session.getAttribute("login") instanceof LoginDTO)
			boxer = (LoginDTO)session.getAttribute("login");
			System.out.println("LoginInterceptor�μ� boxer " + boxer);
			//�α����� �ȵ� ���¿��� ��û�� ���� �� �α��� �� �� ��û�� �������� �̵��ϵ��� �ϱ� ���ؼ� ��û�� �ּҸ� ���ǿ� ������ �� �α����� �������� �� �� �ּҷ� �̵��ϵ��� �ϸ� �ȴ�.
			String uri = request.getRequestURI();
			System.out.println("LoginInterceptor�μ�  uri " + uri);
	        
	          //������Ʈ �̸����� ã�ƿ��⶧���� �ּҸ�  http://127.0.0.1:8081/order/~~.do
	          int idx = uri.lastIndexOf("/");
			  uri = uri.substring(idx+1);
			
			  System.out.println("LoginInterceptor�μ�  uri "+uri);
	           //�Ķ���� ã�ƿ���  ����Ʈ���� ����Ŭ���ϸ� idx������ �������� 
			String query = request.getQueryString();
			System.out.println("LoginInterceptor�μ�  query=" + query);
			
			//�Ķ���Ͱ� ������ ��û �ּҴ� �״�� 
			if(query==null || query.equals("null")){
				query="";
			}
			else{
				query="?"+query;
			}
			uri = uri + query;
			
			//���ǿ� uri ���� 
			session.setAttribute("LOGIN",uri);
			boolean adminCheck = false;
			//�α����� �ȵ� ��� �α��� �������� �̵�
			if(boxer!=null){
				adminCheck=true;	
			}else {
				response.setContentType("text/html; charset=utf-8");
				response.getWriter().append("<script>"
								+ "alert('LoginInterceptor�μ� �α������ʿ��մϴ�.');"
								  + "</script>").flush();
				adminCheck=false;
			}
			return adminCheck;
	}//end

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)		throws Exception {
		//��Ʈ�ѷ��� �޼ҵ� ������ , ȭ��ó���� ó�� 
        logger.info("=======================afterCompletion start========================");
		logger.info("=======================afterCompletion end=========================");

	}//end
			
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)		throws Exception {
		//��Ʈ�ѷ��� �޼ҵ� ������ ,1ó�� 
	    logger.info("=======================postHandle start======================");
		logger.info("=======================postHandle end=========================");	
	}//end

   
}//LoginInterceptor class END
