package com.jsp.projecta1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.projecta1.entity.Customer;
import com.jsp.projecta1.repo.CustomerRepo;

@Repository
public class CustomerDao {
@Autowired
private CustomerRepo repo;

public Customer saveCustomer(Customer customer) {
	return repo.save(customer);
}

public Customer updateCustomer(int customerId, Customer customer) {
	Optional<Customer> optional=repo.findById(customerId);
	if(optional.isPresent()) {
		
		customer.setCustomerid(customerId);
		customer.setAddresses(optional.get().getAddresses());
		customer.setBookings(optional.get().getBookings());
		return repo.save(customer);
	}
	return null;
}

public Customer findCustomerById(int customerId) {
	Optional<Customer> optional=repo.findById(customerId);
	if(optional.isPresent()) {
		return optional.get();
	}
	return null;
}

public Customer deleteCustomerByid(int customerId) {
	Optional<Customer> optional=repo.findById(customerId);
	if(optional.isPresent()) {
		repo.delete(optional.get());
		return optional.get();
	}
	return null;
}

}
