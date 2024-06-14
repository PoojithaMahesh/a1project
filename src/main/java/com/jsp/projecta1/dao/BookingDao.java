package com.jsp.projecta1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.projecta1.entity.Bookings;
import com.jsp.projecta1.repo.BookingRepo;

@Repository
public class BookingDao {
@Autowired
private BookingRepo repo;

public Bookings saveBooking(Bookings bookings) {
	return repo.save(bookings);
}

public Bookings findBooking(int bookingId) {
	if(repo.findById(bookingId).isPresent()) {
		return repo.findById(bookingId).get();
	}
	return null;
}

public void deleteBooking(int bookingId) {
	repo.deleteById(bookingId);
	
}
}
