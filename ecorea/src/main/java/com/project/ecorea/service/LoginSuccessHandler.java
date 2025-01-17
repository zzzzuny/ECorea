package com.project.ecorea.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.project.ecorea.dao.UserDao;
import com.project.ecorea.entity.Corp;
import com.project.ecorea.entity.Member;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	private UserDao dao;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String id = authentication.getName();
		
		Member member = dao.memberFindById(id);
		Corp corp = null;
		if(member!=null) {
			dao.memberInfoUpdate(Member.builder().id(id).failcnt(0).build());
		} else if(member == null) {
			corp = dao.corpFindById(id);
			if(corp!=null)
				dao.corpInfoUpdate(Corp.builder().id(id).failcnt(0).build());			
		}
		
		SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
		String password = request.getParameter("pw");
		
		HttpSession session = request.getSession();
		session.setAttribute("login", true);
		if(password.length()>=20) {
			session.setAttribute("login", "임시비밀번호로 로그인하셨습니다. 비밀번호를 변경해주세요");
			new DefaultRedirectStrategy().sendRedirect(request, response, "/");
		} else {
			session.setAttribute("login", "");
			String destination = (savedRequest!=null)? savedRequest.getRedirectUrl() : "/";
			new DefaultRedirectStrategy().sendRedirect(request, response, destination);
		}
	}
}
