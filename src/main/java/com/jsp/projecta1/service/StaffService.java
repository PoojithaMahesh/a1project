package com.jsp.projecta1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.projecta1.dao.AdminDao;
import com.jsp.projecta1.dao.MedicalStoreDao;
import com.jsp.projecta1.dao.StaffDao;
import com.jsp.projecta1.dto.AdminDto;
import com.jsp.projecta1.dto.MedicalStoreDto;
import com.jsp.projecta1.dto.StaffDto;
import com.jsp.projecta1.entity.Admin;
import com.jsp.projecta1.entity.MedicalStore;
import com.jsp.projecta1.entity.Staff;
import com.jsp.projecta1.exception.AdminIdNotFoundException;
import com.jsp.projecta1.exception.MedicalStoreIdNotFoundException;
import com.jsp.projecta1.exception.StaffIdNotFoundException;
import com.jsp.projecta1.util.ResponseStructure;

@Service
public class StaffService {

	@Autowired
	private StaffDao dao;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private MedicalStoreDao storeDao;
	@Autowired
	private ModelMapper mapper;

	public ResponseEntity<ResponseStructure<StaffDto>> saveStaff(int adminId, int storeId, Staff staff) {
		Admin dbAdmin=adminDao.findAdminById(adminId);
		if(dbAdmin!=null) {
			staff.setAdmin(dbAdmin);
			MedicalStore dbMedicalStore=storeDao.findMedicalStore(storeId);
			if(dbMedicalStore!=null) {
				staff.setMedicalStore(dbMedicalStore);
				Staff dbStaff=dao.saveStaff(staff);
				
				StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
				
				MedicalStoreDto storeDto=this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
				staffDto.setMedicalStoreDto(storeDto);
				
				AdminDto adminDto=this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
				staffDto.setAdminDto(adminDto);
				
				ResponseStructure<StaffDto> structure=new ResponseStructure<>();
				structure.setMessage("Staff SignedUp successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(staffDto);
				return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.CREATED);
				
			}else {
				throw new MedicalStoreIdNotFoundException("Sorry failed to add medicalstore");
			}
			
		}else {
			throw new AdminIdNotFoundException("Sorry failed to save the staff");
		}
	}

	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
		Staff dbStaff=dao.updateStaff(staffId,staff);
		if(dbStaff!=null) {
//			id is present and the data updated successfully
			StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
			
			MedicalStoreDto storeDto=this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(storeDto);
			
			AdminDto adminDto=this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.OK);
		}else {
//			raise exception
			throw new StaffIdNotFoundException("Sorry failed to update Staff");
		}
	}

	public ResponseEntity<ResponseStructure<StaffDto>> findStaff(int staffId) {
		Staff dbStaff=dao.findStaffById(staffId);
		if(dbStaff!=null) {
//			id is present and the data updated successfully
			StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
			
			MedicalStoreDto storeDto=this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(storeDto);
			
			AdminDto adminDto=this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FOUND);
		}else {
//			raise exception
			throw new StaffIdNotFoundException("Sorry failed to fetch Staff");
		}
	}

	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbStaff=dao.deleteStaffById(staffId);
		if(dbStaff!=null) {
//			id is present and the data updated successfully
			StaffDto staffDto=this.mapper.map(dbStaff, StaffDto.class);
			
			MedicalStoreDto storeDto=this.mapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class);
			staffDto.setMedicalStoreDto(storeDto);
			
			AdminDto adminDto=this.mapper.map(dbStaff.getAdmin(), AdminDto.class);
			staffDto.setAdminDto(adminDto);
			
			ResponseStructure<StaffDto> structure=new ResponseStructure<>();
			structure.setMessage("Staff deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(staffDto);
			return new ResponseEntity<ResponseStructure<StaffDto>>(structure,HttpStatus.FORBIDDEN);
		}else {
//			raise exception
			throw new StaffIdNotFoundException("Sorry failed to delete Staff");
		}
	}
	
}
