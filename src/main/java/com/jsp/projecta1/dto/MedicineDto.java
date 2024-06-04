package com.jsp.projecta1.dto;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Component
public class MedicineDto {
	private int medicineId;
	private String medicineName;
	private double cost;
	private LocalDate expiryDate;
	private int stockQuantity;
	private String manufacturer;
	private String description;
	
	@ManyToOne
	private MedicalStoreDto medicalStoreDto;
}
