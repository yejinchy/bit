package com.myspring.springBBS.Login;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class LoginController {
	
	@Autowired
	LoginDAO loginDAO;
	
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@ResponseBody
	@RequestMapping(value="loginCheck.do", method=RequestMethod.POST)
	public String loginCheck(HttpServletRequest request, HttpSession session)
	{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		logger.info("LoginController");
		System.out.println("2��° id:"+id+"\t pw:"+pw);
		
		boolean ck = loginDAO.loginCheck(id, pw);
		if(ck) {
			System.out.println("3��° OK789 ���� Ȯ��");
			LoginDTO login = loginDAO.login(id, pw);
			session.setAttribute("login",login);			
			return "success";
		}else {
			System.out.println("3��° ����Ȯ�� �Ұ�\n");
			return "";
		}		
	}//end
	
	@ResponseBody
	@RequestMapping(value="loginChange.do", method=RequestMethod.POST)
	public String loginChange(HttpServletRequest request, HttpSession session)
	{
		session = request.getSession();	
		LoginDTO login = (LoginDTO)session.getAttribute("login");
		if(login != null) {		
			System.out.println("OK789 session idȮ��:"+login.getS_id());
			return login.getS_id();
		}else {
			System.out.println("1��° session login���� : " + login);
			return "null";
		}
	}//end

	@RequestMapping(value="logout.do")
	public String logout(HttpServletRequest request, HttpSession session){
		session = request.getSession();	
		LoginDTO login = (LoginDTO)session.getAttribute("login");
		loginDAO.logout(login.getS_id());
		session.invalidate();  	//���� �ʱ�ȭ
		return "redirect:/index.jsp";
	}//end
	
}//LoginController class END