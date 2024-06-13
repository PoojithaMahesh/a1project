package com.jsp.projecta1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.projecta1.entity.Bookings;

public interface BookingRepo extends JpaRepository<Bookings, Integer>{

}
