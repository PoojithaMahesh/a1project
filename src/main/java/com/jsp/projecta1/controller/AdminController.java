package com.jsp.projecta1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.projecta1.dto.AdminDto;
import com.jsp.projecta1.entity.Admin;
import com.jsp.projecta1.service.AdminService;
import com.jsp.projecta1.util.ResponseStructure;

@RestController
public class AdminController {

	@Autowired
	private AdminService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<AdminDto>> signUpAdmin(@RequestBody Admin admin){
		return service.signUpAdmin(admin);
	}
	@PutMapping("/update")
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(@RequestParam int adminId, @RequestBody Admin admin){
		return service.updateAdmin(adminId,admin);
	}
	
}
