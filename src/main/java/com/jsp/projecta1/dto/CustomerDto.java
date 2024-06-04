package com.jsp.projecta1.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@Component
public class CustomerDto {
	private int customerid;
	private String customerName;
	@OneToMany
	private List<AddressDto> addressDtos;
	
	@OneToMany
	private List<BookingsDto> bookingsDtos;
}
