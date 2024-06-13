package com.jsp.projecta1.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.projecta1.dao.BookingDao;
import com.jsp.projecta1.dao.CustomerDao;
import com.jsp.projecta1.dao.MedicineDao;
import com.jsp.projecta1.dto.BookingsDto;
import com.jsp.projecta1.dto.CustomerDto;
import com.jsp.projecta1.dto.MedicineDto;
import com.jsp.projecta1.entity.Bookings;
import com.jsp.projecta1.entity.Customer;
import com.jsp.projecta1.entity.Medicine;
import com.jsp.projecta1.enums.BookingStatus;
import com.jsp.projecta1.exception.CustomerIdNotFoundException;
import com.jsp.projecta1.exception.MedicineIdNotFoundException;
import com.jsp.projecta1.util.ResponseStructure;

@Service
public class BookingService {
@Autowired
private BookingDao dao;
@Autowired
private CustomerDao customerDao;
@Autowired
private MedicineDao medicineDao;
@Autowired
private ModelMapper mapper;

public ResponseEntity<ResponseStructure<BookingsDto>> saveBooking(int customerId, int medicinId, Bookings bookings) {
Customer dbCustomer=customerDao.findCustomerById(customerId);
if(dbCustomer!=null) {
	
//	customer is present
	bookings.setCustomer(dbCustomer);
	Medicine dbMedicine=medicineDao.findMedicineById(medicinId);
	if(dbMedicine!=null) {
		List<Medicine> medicines=new ArrayList<Medicine>();
		medicines.add(dbMedicine);
		bookings.setMedicines(medicines);
		bookings.setOrderDate(LocalDate.now());
		bookings.setExpectedDate(LocalDate.now().plusDays(10));
		bookings.setBookingStatus(BookingStatus.ACTIVE);
//		i just want to save booking and alos keep this in mind that you have 
//		estbalished bidirection relationship with Booking and Medicine
		Bookings dbBookings=dao.saveBooking(bookings);
//		update the customer details
		List<Bookings> bookingsList=new  ArrayList<Bookings>();
		dbCustomer.setBookings(bookingsList);
		customerDao.updateCustomer(customerId, dbCustomer);
		ResponseStructure<BookingsDto> structure=new ResponseStructure<>();
		structure.setMessage("Booking added successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		BookingsDto bookingsDto=this.mapper.map(dbBookings, BookingsDto.class);
		
		CustomerDto customerDto=this.mapper.map(dbBookings.getCustomer(),CustomerDto.class);
		bookingsDto.setCustomerDto(customerDto);
		
		List<MedicineDto> medicineDtos=new ArrayList<MedicineDto>();
		for(Medicine medicine:dbBookings.getMedicines()) {
			MedicineDto medicineDto=this.mapper.map(medicine, MedicineDto.class);
			medicineDtos.add(medicineDto);
		}
		
		bookingsDto.setMedicineDtos(medicineDtos);
		structure.setData(bookingsDto);
		
		
		return new ResponseEntity<ResponseStructure<BookingsDto>>(structure,HttpStatus.CREATED);
	}else {
		throw new MedicineIdNotFoundException("Sorry failed to add Booking");
	}
	
}else {
	throw new CustomerIdNotFoundException("Sorry failed to add Booking");
}
}
}
