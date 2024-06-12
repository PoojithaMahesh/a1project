package com.jsp.projecta1.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.projecta1.dao.AddressDao;
import com.jsp.projecta1.dao.CustomerDao;
import com.jsp.projecta1.dto.AddressDto;
import com.jsp.projecta1.dto.CustomerDto;
import com.jsp.projecta1.entity.Address;
import com.jsp.projecta1.entity.Customer;
import com.jsp.projecta1.exception.AddressIdNotFoundException;
import com.jsp.projecta1.exception.CustomerIdNotFoundException;
import com.jsp.projecta1.util.ResponseStructure;

@Service
public class CustomerService {
@Autowired
private CustomerDao dao;
@Autowired
private AddressDao addressDao;
@Autowired
private ModelMapper mapper;

public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(int addressId, Customer customer) {
	Address dbAddress=addressDao.findAdddress(addressId);
	if(dbAddress!=null) {
//		he is giving a valid address
		List<Address>  addresses=new ArrayList<Address>();
		addresses.add(dbAddress);
		customer.setAddresses(addresses);
//		update my address details also
		dbAddress.setCustomer(customer);
		addressDao.updateAddress(addressId, dbAddress);
//		save the custoner now
		Customer dbCustomer=dao.saveCustomer(customer);
		CustomerDto customerDto=this.mapper.map(dbCustomer, CustomerDto.class);
		List<AddressDto> addressDtos=new ArrayList<>();
	    for(Address address:dbCustomer.getAddresses()) {
	    	AddressDto addressDto=this.mapper.map(address, AddressDto.class);
	    	addressDtos.add(addressDto);
	    }
	    customerDto.setAddressDtos(addressDtos);
		ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
		structure.setMessage("Customer SignedUp successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(customerDto);
		return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
		
		
	}else {
		throw new AddressIdNotFoundException("Sorry failed to signup");
	}

}

public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(int customerId, Customer customer) {
	Customer dbCustomer=dao.updateCustomer(customerId,customer);
	if(dbCustomer!=null) {
		CustomerDto customerDto=this.mapper.map(dbCustomer, CustomerDto.class);
		List<AddressDto> addressDtos=new ArrayList<>();
	    for(Address address:dbCustomer.getAddresses()) {
	    	AddressDto addressDto=this.mapper.map(address, AddressDto.class);
	    	addressDtos.add(addressDto);
	    }
	    customerDto.setAddressDtos(addressDtos);
		ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
		structure.setMessage("Customer Updated successfully");
		structure.setHttpStatus(HttpStatus.OK.value());
		structure.setData(customerDto);
		return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
		
	}else {
		throw new CustomerIdNotFoundException("Sorry failed to updateCustomer");
		
	}
}

public ResponseEntity<ResponseStructure<CustomerDto>> findCustomer(int customerId) {
	Customer dbCustomer=dao.findCustomerById(customerId);
	if(dbCustomer!=null) {
		CustomerDto customerDto=this.mapper.map(dbCustomer, CustomerDto.class);
		List<AddressDto> addressDtos=new ArrayList<>();
	    for(Address address:dbCustomer.getAddresses()) {
	    	AddressDto addressDto=this.mapper.map(address, AddressDto.class);
	    	addressDtos.add(addressDto);
	    }
	    customerDto.setAddressDtos(addressDtos);
		ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
		structure.setMessage("Customer Fetched successfully");
		structure.setHttpStatus(HttpStatus.FOUND.value());
		structure.setData(customerDto);
		return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.FOUND);
		
	}else {
		throw new CustomerIdNotFoundException("Sorry failed to fetch customer");
		
	}
}

public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(int customerId) {
	Customer dbCustomer=dao.deleteCustomerByid(customerId);
	if(dbCustomer!=null) {
		CustomerDto customerDto=this.mapper.map(dbCustomer, CustomerDto.class);
		List<AddressDto> addressDtos=new ArrayList<>();
	    for(Address address:dbCustomer.getAddresses()) {
	    	AddressDto addressDto=this.mapper.map(address, AddressDto.class);
	    	addressDtos.add(addressDto);
	    }
	    customerDto.setAddressDtos(addressDtos);
		ResponseStructure<CustomerDto> structure=new ResponseStructure<>();
		structure.setMessage("Customer deleted successfully");
		structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
		structure.setData(customerDto);
		return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.FORBIDDEN);
		
	}else {
		throw new CustomerIdNotFoundException("Sorry failed to delete Customer");
		
	}
}
}
