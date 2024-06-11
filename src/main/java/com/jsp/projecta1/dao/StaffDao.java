package com.jsp.projecta1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.projecta1.entity.Staff;
import com.jsp.projecta1.repo.StaffRepo;

@Repository
public class StaffDao {

	@Autowired
	private StaffRepo repo;

	public Staff saveStaff(Staff staff) {
		// TODO Auto-generated method stub
		return repo.save(staff);
	}

	public Staff updateStaff(int staffId, Staff staff) {
		Optional<Staff> optional=repo.findById(staffId);
		if(optional.isPresent()) {
//			staff id is present
			
			staff.setStaffId(staffId);
			staff.setMedicalStore(optional.get().getMedicalStore());
			staff.setAdmin(optional.get().getAdmin());
			return repo.save(staff);
		}
		return null;
	}

	public Staff findStaffById(int staffId) {
		Optional<Staff> optional=repo.findById(staffId);
		if(optional.isPresent()) {
//			staff id is present
			return optional.get();
		}
		return null;
	}

	public Staff deleteStaffById(int staffId) {
		Optional<Staff> optional=repo.findById(staffId);
		if(optional.isPresent()) {
//			staff id is present
			repo.delete(optional.get());
			return optional.get();
		}
		return null;
	}
}
