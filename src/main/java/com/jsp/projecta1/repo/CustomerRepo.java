package com.jsp.projecta1.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.projecta1.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

}
