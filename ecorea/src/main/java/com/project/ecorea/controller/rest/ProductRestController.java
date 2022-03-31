package com.project.ecorea.controller.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.ecorea.service.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProductRestController {
	
	private final ProductService service;

	/* 재고 확인 (수량 변경 시 필요) */
	@GetMapping("/product/productList/checkstock")
	public ResponseEntity<Boolean> checkStock(Integer pno, Integer count) {
		Boolean stock = service.checkStock(pno, count);
		if (stock == false)
			return ResponseEntity.status(HttpStatus.CONFLICT).body(stock);
		return ResponseEntity.ok(stock);
	}
	
}
