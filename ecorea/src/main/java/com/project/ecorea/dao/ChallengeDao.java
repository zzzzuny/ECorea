package com.project.ecorea.dao;

import org.apache.ibatis.annotations.Mapper;
import java.util.*;

import org.apache.ibatis.annotations.*;

import com.project.ecorea.entity.*;

@Mapper
public interface ChallengeDao {

	/* 기업 회원 : 챌린지 등록 */
	public void challengeUpload(Challenge challengeDto);
  
	public List<Challenge> findByChallengeAll();

	public List<Challenge> findByCorpId(String id);

	public Challenge findBycno(Integer cno);
  
}
