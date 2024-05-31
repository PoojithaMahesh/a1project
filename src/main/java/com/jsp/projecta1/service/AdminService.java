package com.jsp.projecta1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.projecta1.dao.AdminDao;
import com.jsp.projecta1.dto.AdminDto;
import com.jsp.projecta1.entity.Admin;
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
	
}
