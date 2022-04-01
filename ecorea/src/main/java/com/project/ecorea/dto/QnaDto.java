package com.project.ecorea.dto;

import java.time.LocalDate;

import org.springframework.web.multipart.MultipartFile;

import com.project.ecorea.entity.QnaQ;

import lombok.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QnaDto {

	/* 문의 출력 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class QuestionDto {
		private Integer qqno; /* 질문 번호 */
		private String qqcategory; /* 질문 카테고리 */
		private String qqtitle; /* 제목 */
		private String qqcontent; /* 내용 */
		private String qqimg; /* 이미지 */
		private LocalDate qqregday; /* 등록일 */
		private String memberId; /* 작성자 */
		private Boolean isAnswer; /* 답변 여부 */
	}
	
	/* 문의 답변 출력 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class AnswerDto {
		private Integer qqno; /* 질문 번호 */
		private Integer qano; /* 답변 번호 */
		private String corpId; /* 답변 작성자 (기업) */
		private String qacontent; /* 답변 내용 */
	}

	/* 문의 작성 */
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	public static class QuestionUpload {
		private Integer pno; /* 상품 번호 */
		private String qqcategory; /* 질문 카테고리 */
		private MultipartFile qqimg; /* 이미지 */
		private String qqtitle; /* 제목 */
		private String qqcontent; /* 내용 */
		private String memberId; /* 작성자 */
		
		public QnaQ toEntity() {
			return QnaQ.builder().pno(pno).qqcategory(qqcategory)
					.qqtitle(qqtitle).qqregday(LocalDate.now()).memberId(memberId).build();
		}
	}
	
}
