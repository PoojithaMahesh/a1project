package com.jsp.projecta1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.projecta1.dao.AddressDao;
import com.jsp.projecta1.dao.AdminDao;
import com.jsp.projecta1.dao.MedicalStoreDao;
import com.jsp.projecta1.entity.Address;
import com.jsp.projecta1.entity.Admin;
import com.jsp.projecta1.entity.MedicalStore;
import com.jsp.projecta1.exception.AddressIdNotFoundException;
import com.jsp.projecta1.exception.AdminIdNotFoundException;
import com.jsp.projecta1.exception.MedicalStoreIdNotFoundException;
import com.jsp.projecta1.util.ResponseStructure;

@Service
public class MedicalStoreService {

	@Autowired
	private MedicalStoreDao dao;
	@Autowired
	private AddressDao addressDao;
	@Autowired
	private AdminDao adminDao;

	public ResponseEntity<ResponseStructure<MedicalStore>> saveMedicalStore(int addressId, int adminId,
			MedicalStore medicalStore) {
		Address dbAddress=addressDao.findAdddress(addressId);
		if(dbAddress!=null) {
//			address is present
//			then you can do with the further operation
			medicalStore.setAddress(dbAddress);
			Admin dbAdmin=adminDao.findAdminById(adminId);
			if(dbAdmin!=null) {
//				admin is also valid admin thenan save the data
				medicalStore.setAdmin(dbAdmin);
				MedicalStore dbMedicalStore=dao.saveMedicalStore(medicalStore);
				dbAddress.setMedicalStore(medicalStore);
				addressDao.updateAddress(addressId, dbAddress);
				ResponseStructure<MedicalStore> structure=new ResponseStructure<>();
				structure.setMessage("Data saved successfully");
				structure.setHttpStatus(HttpStatus.CREATED.value());
				structure.setData(dbMedicalStore);
				return new ResponseEntity<ResponseStructure<MedicalStore>>(structure,HttpStatus.CREATED);
			}else {
				throw new AdminIdNotFoundException("Sorry failed to establish the medicalstore");
			}
		}else {
			throw new AddressIdNotFoundException("Sorry failed to establish the medicalStore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStore>> updateMedicalStore(int storeId, MedicalStore medicalStore) {
		MedicalStore dbMedicalStore=dao.updateMedicalStore(storeId,medicalStore);
		if(dbMedicalStore!=null) {
//			id is present
			ResponseStructure<MedicalStore> structure=new ResponseStructure<>();
			structure.setMessage("Data updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(dbMedicalStore);
			return new ResponseEntity<ResponseStructure<MedicalStore>>(structure,HttpStatus.OK);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to update medicalstore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStore>> findMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=dao.findMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			id is present
			ResponseStructure<MedicalStore> structure=new ResponseStructure<>();
			structure.setMessage("Data fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicalStore);
			return new ResponseEntity<ResponseStructure<MedicalStore>>(structure,HttpStatus.FOUND);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to fetch medicalstore");
		}
	}

	public ResponseEntity<ResponseStructure<MedicalStore>> deleteMedicalStore(int storeId) {
		MedicalStore dbMedicalStore=dao.deleteMedicalStore(storeId);
		if(dbMedicalStore!=null) {
//			id is present
			ResponseStructure<MedicalStore> structure=new ResponseStructure<>();
			structure.setMessage("Data deleted successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicalStore);
			return new ResponseEntity<ResponseStructure<MedicalStore>>(structure,HttpStatus.FOUND);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to delete medicalstore");
		}
	}
}
