package com.jsp.projecta1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.projecta1.entity.Address;
import com.jsp.projecta1.repo.AddressRepo;

@Repository
public class AddressDao {

	@Autowired
	private AddressRepo repo;

	public Address saveAddress(Address address) {
		// TODO Auto-generated method stub
		return repo.save(address);
	}

	public Address updateAddress(int addressId, Address address) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
//			id is present so i cam update the data
//	before updating what are all the things that uyou need to set	
//			set the id 
			address.setAddressId(addressId);
			address.setCustomer(optional.get().getCustomer());
			address.setMedicalStore(optional.get().getMedicalStore());
			return repo.save(address);
		}
		return null;
	}

	public Address findAdddress(int addressId) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Address deleteAddress(int addressId) {
		Optional<Address> optional=repo.findById(addressId);
		if(optional.isPresent()) {
			repo.deleteById(addressId);
			return optional.get();
		}
		return null;
	}
	
	
	
}
