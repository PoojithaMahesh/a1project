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
import com.jsp.projecta1.exception.BookingAlreadyCancelledException;
import com.jsp.projecta1.exception.BookingAlreadyDeliveredException;
import com.jsp.projecta1.exception.BookingCanCancelNow;
import com.jsp.projecta1.exception.BookingIdNotFoundException;
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

public ResponseEntity<ResponseStructure<BookingsDto>> cancelBooking(int bookingId) {
	Bookings dbBookings=dao.findBooking(bookingId);
	if(dbBookings!=null) {
//		booking is present then i can cancel a booking
		
		LocalDate cantCancelledDate=dbBookings.getExpectedDate().minusDays(2);
		if(dbBookings.getBookingStatus().equals(BookingStatus.CANCELLED)) {
			throw new BookingAlreadyCancelledException("Sorry failed to cancel Booking");
		}else if(dbBookings.getBookingStatus().equals(BookingStatus.DELIVERED)){
			throw new BookingAlreadyDeliveredException("Sorry failed to cancel Booking");
		}else if(LocalDate.now().equals(cantCancelledDate)||LocalDate.now().isAfter(cantCancelledDate)) {
			throw new BookingCanCancelNow("Sorry failed to cancel Booking");
		}else {
		dao.deleteBooking(bookingId);
		ResponseStructure<BookingsDto> structure=new ResponseStructure<>();
		structure.setMessage("Booking cancelled successfully");
		structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
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
		
		return new ResponseEntity<ResponseStructure<BookingsDto>>(structure,HttpStatus.FORBIDDEN);		
		}
	}else {
		throw new BookingIdNotFoundException("Sorry failed to cancel Booking");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
}
