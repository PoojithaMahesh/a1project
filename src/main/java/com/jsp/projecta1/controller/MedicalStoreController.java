package com.jsp.projecta1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.projecta1.entity.MedicalStore;
import com.jsp.projecta1.service.MedicalStoreService;
import com.jsp.projecta1.util.ResponseStructure;

@RestController
@RequestMapping("/store")
public class MedicalStoreController {

	@Autowired
	private MedicalStoreService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<MedicalStore>> saveMedicalStore(@RequestParam int adminId,@RequestParam int addressId,
			@RequestBody MedicalStore medicalStore){
		return service.saveMedicalStore(addressId,adminId,medicalStore);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<MedicalStore>> updateMedicalStore(@RequestBody int storeId,@RequestBody MedicalStore medicalStore){
		return service.updateMedicalStore(storeId,medicalStore);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<MedicalStore>> findMedicalStore(@RequestBody int storeId){
		return service.findMedicalStore(storeId);
	}
	@DeleteMapping
	public ResponseEntity<ResponseStructure<MedicalStore>> deleteMedicalStore(@RequestBody int storeId){
		return service.deleteMedicalStore(storeId);
	}
}
