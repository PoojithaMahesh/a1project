package com.jsp.projecta1.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Component
public class BookingsDto {
	private int bookingId;
	private LocalDate orderDate;
	private int quantity;
	private String paymentMode;
	private LocalDate expectedDate;
	@ManyToMany
	private List<MedicineDto> medicineDtos;
	
	@ManyToOne
	private CustomerDto customerDto;
}
