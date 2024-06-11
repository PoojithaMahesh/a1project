package com.jsp.projecta1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.projecta1.entity.Address;
import com.jsp.projecta1.entity.MedicalStore;
import com.jsp.projecta1.repo.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {

	@Autowired
	private MedicalStoreRepo repo;

	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
		return repo.save(medicalStore);
	}

	public MedicalStore updateMedicalStore(int storeId, MedicalStore medicalStore) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			
			medicalStore.setStoreId(storeId);
			medicalStore.setAddress(optional.get().getAddress());
			medicalStore.setAdmin(optional.get().getAdmin());
			return repo.save(medicalStore);
		}
		return null;
	}

	public MedicalStore findMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			
			return optional.get();
		}
		return null;
	}

	public MedicalStore deleteMedicalStore(int storeId) {
		Optional<MedicalStore> optional=repo.findById(storeId);
		if(optional.isPresent()) {
			Address address=optional.get().getAddress();
			address.setMedicalStore(null);
			repo.delete(optional.get());
			return optional.get();
		}
		return null;
	}
}
