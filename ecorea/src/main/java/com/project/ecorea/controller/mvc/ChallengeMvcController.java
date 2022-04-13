package com.project.ecorea.controller.mvc;


import org.springframework.beans.factory.annotation.Autowired;

import java.security.*;

import javax.servlet.http.*;

import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.project.ecorea.dto.*;
import com.project.ecorea.entity.*;
import com.project.ecorea.service.*;

import lombok.*;

@Controller
@AllArgsConstructor
public class ChallengeMvcController {
	
	@Autowired
	private ChallengeService service;

	/* 기업 회원 : 챌린지 등록 화면 */
	@GetMapping("/challenge/corp/challengeUpload")
	public void challengeUpload() {
	}
	
	/* 기업 회원 : 챌린지 등록 */
	@PostMapping("/challenge/corp/challengeUpload")
	public String challengeUpload(ChallengeDto.ChallengeUpload challenge) {
		String loginId = "녹색당";
		service.challengeUpload(challenge, loginId);
		return "redirect:/challenge/corp/challengeList";
	} 
	
	/* 기업 회원 : 챌린지 수정 화면 */
	@GetMapping("/challenge/corp/challengeUpdate")
	public ModelAndView challengeUpdate(Integer cno) {
		return new ModelAndView().addObject("challenge", service.challengeUpdateView(123));
	}
	
	/* 기업 회원 : 챌린지 수정 */
	@PostMapping("/challenge/corp/challengeUpdate")
	public String challengeUpdate(Challenge challenge) {
		service.challengeUpdate(challenge);
		return "redirect:/challenge/corp/challengeList";
	}
	
	/* 전체 유저 : 챌린지 목록 출력 */
	@GetMapping("/challenge/challengeList")
	public ModelAndView readchallengeList() {
		return new ModelAndView().addObject("challenge", service.readchallengeList());
	}
	
	/* 기업 회원 : 챌린지 목록 출력 */
	@GetMapping("/challenge/corp/challengeList")
	public ModelAndView readCorpChallengeList(/*Principal principal*/) {
		String loginId = "녹색당";
		return new ModelAndView("challenge/corp/challengeList").addObject("challenge", service.readCorpChallengeList(loginId));
	}
	
	/* 전체 유저 : 챌린지 상세 페이지 출력 */
	@GetMapping("/challenge/member/challengeDetail")
	public ModelAndView readUserDetail(Integer cno, HttpSession session, HttpServletRequest request) {
		if(session.getAttribute("login")==null) {
			return new ModelAndView("challenge/member/challengeDetail").addObject("challenge", service.readUserDetail(cno));
		}
		
		if(request.isUserInRole("ROLE_MEMBER")) {
			return new ModelAndView("challenge/member/challengeDetail").addObject("challenge", service.readUserDetail(cno));
		} else {
			return new ModelAndView("challenge/corp/challengeDetail").addObject("challenge", service.readUserDetail(cno));
		}
	}
}