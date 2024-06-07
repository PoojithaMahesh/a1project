package com.jsp.projecta1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.projecta1.dao.MedicalStoreDao;
import com.jsp.projecta1.dao.MedicineDao;
import com.jsp.projecta1.entity.MedicalStore;
import com.jsp.projecta1.entity.Medicine;
import com.jsp.projecta1.exception.MedicalStoreIdNotFoundException;
import com.jsp.projecta1.exception.MedicineIdNotFoundException;
import com.jsp.projecta1.exception.MedicineNameNotFoundException;
import com.jsp.projecta1.util.ResponseStructure;

@Service
public class MedicineService {
@Autowired
private MedicineDao dao;
@Autowired
private MedicalStoreDao storeDao;

public ResponseEntity<ResponseStructure<Medicine>> saveMedicine(int storeId, Medicine medicine) {
	MedicalStore dbMedicalStore=storeDao.findMedicalStore(storeId);
	if(dbMedicalStore!=null) {
//		store is present i can add a medicine
		medicine.setMedicalStore(dbMedicalStore);
		Medicine dbMedicine=dao.saveMedicine(medicine);
		ResponseStructure<Medicine> structure=new ResponseStructure<>();
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setMessage("MEdicine added successfully");
		structure.setData(dbMedicine);
		return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.CREATED);
	}else {
//		throw exceptiob
		throw new MedicalStoreIdNotFoundException("Sorry failed to add medicine");
	}
}

public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(int medicineId, Medicine medicine) {
	Medicine dbMedicine=dao.updateMedicine(medicineId,medicine);
	if(dbMedicine!=null) {
//		id is present and the data updated successfully
		ResponseStructure<Medicine> structure=new ResponseStructure<>();
		structure.setHttpStatus(HttpStatus.OK.value());
		structure.setMessage("MEdicine updated successfully");
		structure.setData(dbMedicine);
		return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.OK);
	}else {
		throw new MedicineIdNotFoundException("Sorry failed to update medicine");
	}
}

public ResponseEntity<ResponseStructure<Medicine>> findMedicine(int medicineId) {
	Medicine dbMedicine=dao.findMedicineById(medicineId);
	if(dbMedicine!=null) {
//		id is present and the data updated successfully
		ResponseStructure<Medicine> structure=new ResponseStructure<>();
		structure.setHttpStatus(HttpStatus.FOUND.value());
		structure.setMessage("MEdicine fetched successfully");
		structure.setData(dbMedicine);
		return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.FOUND);
	}else {
		throw new MedicineIdNotFoundException("Sorry failed to fetch the medicine");
	}
}

public ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(int medicineId) {
	Medicine dbMedicine=dao.deleteMedicineById(medicineId);
	if(dbMedicine!=null) {
//		id is present and the data updated successfully
		ResponseStructure<Medicine> structure=new ResponseStructure<>();
		structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
		structure.setMessage("MEdicine deleted successfully");
		structure.setData(dbMedicine);
		return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.FORBIDDEN);
	}else {
		throw new MedicineIdNotFoundException("Sorry failed to delete the medicine");
	}
}

public ResponseEntity<ResponseStructure<Medicine>> findMedicineByName(String medicineName) {
	Medicine dbMedicine=dao.findByName(medicineName);
	if(dbMedicine!=null) {
//		id is present and the data updated successfully
		ResponseStructure<Medicine> structure=new ResponseStructure<>();
		structure.setHttpStatus(HttpStatus.FOUND.value());
		structure.setMessage("MEdicine fetched successfully");
		structure.setData(dbMedicine);
		return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.FOUND);
	}else {
		throw new MedicineNameNotFoundException("Sorry failed to fetch the medicine");
	}
}

}
