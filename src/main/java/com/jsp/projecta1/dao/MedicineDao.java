package com.jsp.projecta1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.projecta1.entity.Medicine;
import com.jsp.projecta1.repo.MedicineRepo;

@Repository
public class MedicineDao {
@Autowired
private MedicineRepo repo;

public Medicine saveMedicine(Medicine medicine) {
	// TODO Auto-generated method stub
	return repo.save(medicine);
}

public Medicine updateMedicine(int medicineId, Medicine medicine) {
	Optional<Medicine> optional=repo.findById(medicineId);
	if(optional.isPresent()) {
		
		
		medicine.setMedicineId(medicineId);
		medicine.setMedicalStore(optional.get().getMedicalStore());
	
		return repo.save(medicine);
	}
	return null;
}

public Medicine findMedicineById(int medicineId) {
	Optional<Medicine> optional=repo.findById(medicineId);
	if(optional.isPresent()) {
		return optional.get();
	}
	return null;
}

public Medicine deleteMedicineById(int medicineId) {
	Optional<Medicine> optional=repo.findById(medicineId);
	if(optional.isPresent()) {
		repo.deleteById(medicineId);
		return optional.get();
	}
	return null;
}
}
