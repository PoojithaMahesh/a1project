package com.jsp.projecta1.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.projecta1.entity.Admin;
import com.jsp.projecta1.repo.AdminRepo;

@Repository
public class AdminDao {

	@Autowired
	private AdminRepo repo;

	public Admin saveAdmin(Admin admin) {
		return repo.save(admin);
	}

	public Admin updateAdmin(int adminId, Admin admin) {
// how to update the admin
//		find the admin
//		update the admin if id is present and return updated admin details
//		else you need to return null;
		Optional<Admin> optional=repo.findById(adminId);
		if(optional.isPresent()) {
//			something is present inside that optional 
//			but in this case that sonmething is your admin
			admin.setAdminId(adminId);
			return repo.save(admin);
		}else {
//			id is present and optional is empty
			return null;
		}
	}

	public Admin findAdminById(int adminId) {
		Optional<Admin> optional=repo.findById(adminId);
		if(optional.isPresent()) {
//			id is present
			return optional.get();
		}
		return null;
	}

	public Admin deletetAdminById(int adminId) {
		Optional<Admin> optional=repo.findById(adminId);
		Admin admin=optional.get();
		if(optional.isPresent()) {
//			id is present and you can delete the data
			repo.deleteById(adminId);
			return optional.get();
			
		}
		return null;
	}

	public Admin findAdminByEmail(String email) {
		Optional<Admin> optional=repo.findByEmail(email);
		if(optional.isPresent()) {
//			that admin is present with that email
			return optional.get();
		}
		return null;
	}

	public List<Admin> findAllAdmin() {
		List<Admin> admins=repo.findAll();
		return admins;
	}
}
