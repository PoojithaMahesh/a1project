package com.jsp.projecta1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.projecta1.dto.BookingsDto;
import com.jsp.projecta1.entity.Bookings;
import com.jsp.projecta1.service.BookingService;
import com.jsp.projecta1.util.ResponseStructure;

@RestController
@RequestMapping("/booking")
public class BookingController {
@Autowired
private BookingService service;

@PostMapping
public ResponseEntity<ResponseStructure<BookingsDto>> addBooking(@RequestParam int medicinId,@RequestParam int customerId,
		@RequestBody Bookings bookings){
	return service.saveBooking(customerId,medicinId,bookings);
}
}
