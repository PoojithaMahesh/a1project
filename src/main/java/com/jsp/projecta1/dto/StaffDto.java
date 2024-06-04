package com.jsp.projecta1.dto;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class StaffDto {
	private int staffId;
	private String staffName;
	
	@OneToOne
	private MedicalStoreDto medicalStoreDto;
	
	@ManyToOne
	private AdminDto adminDto;
}
