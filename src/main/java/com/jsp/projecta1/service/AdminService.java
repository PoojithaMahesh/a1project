package com.jsp.projecta1.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.projecta1.dao.AdminDao;
import com.jsp.projecta1.dto.AdminDto;
import com.jsp.projecta1.entity.Admin;
import com.jsp.projecta1.exception.ADminPasswordInvalidException;
import com.jsp.projecta1.exception.AdminEmailInvalidException;
import com.jsp.projecta1.exception.AdminIdNotFoundException;
import com.jsp.projecta1.util.ResponseStructure;

@Service
public class AdminService {

	@Autowired
	private AdminDao dao;

	public ResponseEntity<ResponseStructure<AdminDto>> signUpAdmin(Admin admin) {
		Admin dbAdmin=dao.saveAdmin(admin);
		AdminDto adminDto=new AdminDto();
		adminDto.setAdminId(dbAdmin.getAdminId());
		adminDto.setAdminName(dbAdmin.getAdminName());
		
		ResponseStructure<AdminDto>  structure=new ResponseStructure<>();
		structure.setMessage("Admin saved successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int adminId, Admin admin) {
		Admin dbAdmin=dao.updateAdmin(adminId,admin);
		if(dbAdmin!=null) {
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminId(dbAdmin.getAdminId());
			adminDto.setAdminName(dbAdmin.getAdminName());
//			id is present and admin updated successfully
			ResponseStructure<AdminDto> structure=new ResponseStructure<AdminDto>();
			structure.setMessage("Data updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}else {
//			id is not present
			throw new AdminIdNotFoundException("Sorry failed to update the data");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> findAdminById(int adminId) {
		Admin dbAdmin=dao.findAdminById(adminId);
		if(dbAdmin!=null) {
//			id is present
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminId(dbAdmin.getAdminId());
			adminDto.setAdminName(dbAdmin.getAdminName());
			
			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Admin Data fetched successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}else {
			throw new AdminIdNotFoundException("Sorry failed to fetch the data");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdminById(int adminId) {
		Admin dbAdmin=dao.deletetAdminById(adminId);
		if(dbAdmin!=null) {
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminId(dbAdmin.getAdminId());
			adminDto.setAdminName(dbAdmin.getAdminName());
			
			ResponseStructure<AdminDto> structure=new ResponseStructure<>();
			structure.setMessage("Admin Data deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FORBIDDEN);
		}else {
			throw new AdminIdNotFoundException("Sorry failed to delete the data");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> loginAdmin(String email, String password) {
		Admin dbAdmin=dao.findAdminByEmail(email);
		if(dbAdmin!=null) {
//			that admin is present with this email
			
//		to check with the password
			if(dbAdmin.getAdminPassword().equals(password)) {
//				perfect password
//				login success
				AdminDto adminDto=new AdminDto();
				adminDto.setAdminId(dbAdmin.getAdminId());
				adminDto.setAdminName(dbAdmin.getAdminName());
				ResponseStructure<AdminDto> structure=new ResponseStructure<>();
				structure.setMessage("Admin LoggedIn successfully");
				structure.setHttpStatus(HttpStatus.OK.value());
				structure.setData(adminDto);
				return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
			}else {
//				login failure
				throw new ADminPasswordInvalidException("Sorry failed to Login please try again");
			}
		}else {
//			admin is not present with that email 
//			it is invalid email
			throw new AdminEmailInvalidException("Sorry failed to Login");
		}
		
	}

	public ResponseEntity<ResponseStructure<List<AdminDto>>> findAllAdmin() {
		List<Admin> admins=dao.findAllAdmin();
		List<AdminDto> adminDtos=new ArrayList<AdminDto>();
		for(Admin admin:admins) {
			AdminDto adminDto=new AdminDto();
			adminDto.setAdminId(admin.getAdminId());
			adminDto.setAdminName(admin.getAdminName());
			adminDtos.add(adminDto);
		}
	
		ResponseStructure<List<AdminDto>> structure=new ResponseStructure<>();
		structure.setMessage("Admin data fetched  successfully");
		structure.setHttpStatus(HttpStatus.FOUND.value());
		structure.setData(adminDtos);
		return new ResponseEntity<ResponseStructure<List<AdminDto>>>(structure,HttpStatus.FOUND);
		
		
		
	}
	
}
