package com.jsp.projecta1.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class MedicalStoreDto {
	private int storeId;
	private String storeName;
	private String managerName;
	private long phone;
	
	@OneToOne
	private AdminDto  adminDto;
	
	@OneToOne
	private AddressDto addressDto;
}
