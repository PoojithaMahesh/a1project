package com.jsp.projecta1.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.projecta1.dao.AddressDao;
import com.jsp.projecta1.dto.AddressDto;
import com.jsp.projecta1.dto.CustomerDto;
import com.jsp.projecta1.dto.MedicalStoreDto;
import com.jsp.projecta1.entity.Address;
import com.jsp.projecta1.exception.AddressIdNotFoundException;
import com.jsp.projecta1.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao dao;

	@Autowired
	private AddressDto addressDto;

	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address) {
		Address dbAddress = dao.saveAddress(address);

		AddressDto addressDto = this.modelMapper.map(dbAddress, AddressDto.class);

		if (dbAddress.getMedicalStore() != null) {
			MedicalStoreDto medicalStoreDto = this.modelMapper.map(dbAddress.getMedicalStore(), MedicalStoreDto.class);
			addressDto.setMedicalStoreDto(medicalStoreDto);
		}
		if (dbAddress.getCustomer() != null) {
			CustomerDto customerDto = this.modelMapper.map(dbAddress.getCustomer(), CustomerDto.class);
			addressDto.setCustomerDto(customerDto);
		}

		ResponseStructure<AddressDto> structure = new ResponseStructure<>();
		structure.setMessage("Address Saved successfully");
		structure.setHttpStatus(HttpStatus.CREATED.value());
		structure.setData(addressDto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(int addressId, Address address) {
		Address dbAddress = dao.updateAddress(addressId, address);
		if (dbAddress != null) {
//			id is present
			AddressDto addressDto = this.modelMapper.map(dbAddress, AddressDto.class);

			if (dbAddress.getMedicalStore() != null) {
				MedicalStoreDto medicalStoreDto = this.modelMapper.map(dbAddress.getMedicalStore(),
						MedicalStoreDto.class);
				addressDto.setMedicalStoreDto(medicalStoreDto);
			}
			if (dbAddress.getCustomer() != null) {
				CustomerDto customerDto = this.modelMapper.map(dbAddress.getCustomer(), CustomerDto.class);
				addressDto.setCustomerDto(customerDto);
			}

			ResponseStructure<AddressDto> structure = new ResponseStructure<>();
			structure.setMessage("Address Updated successfully");
			structure.setHttpStatus(HttpStatus.OK.value());
			structure.setData(addressDto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.OK);

		} else {

			throw new AddressIdNotFoundException("Sorry failed to update the Address");
		}
	}

	public ResponseEntity<ResponseStructure<AddressDto>> findAddress(int addressId) {
		Address dbAddress = dao.findAdddress(addressId);
		if (dbAddress != null) {
//			id is present
			AddressDto addressDto = this.modelMapper.map(dbAddress, AddressDto.class);

			if (dbAddress.getMedicalStore() != null) {
				MedicalStoreDto medicalStoreDto = this.modelMapper.map(dbAddress.getMedicalStore(),
						MedicalStoreDto.class);
				addressDto.setMedicalStoreDto(medicalStoreDto);
			}
			if (dbAddress.getCustomer() != null) {
				CustomerDto customerDto = this.modelMapper.map(dbAddress.getCustomer(), CustomerDto.class);
				addressDto.setCustomerDto(customerDto);
			}

			ResponseStructure<AddressDto> structure = new ResponseStructure<>();
			structure.setMessage("Address fetched successfully");
			structure.setHttpStatus(HttpStatus.FOUND.value());
			structure.setData(addressDto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.FOUND);

		} else {

			throw new AddressIdNotFoundException("Sorry failed to fetch the Address");
		}
	}

	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(int addressId) {
		Address dbAddress=dao.deleteAddress(addressId);
		if (dbAddress != null) {
//			id is present
			AddressDto addressDto = this.modelMapper.map(dbAddress, AddressDto.class);

			if (dbAddress.getMedicalStore() != null) {
				MedicalStoreDto medicalStoreDto = this.modelMapper.map(dbAddress.getMedicalStore(),
						MedicalStoreDto.class);
				addressDto.setMedicalStoreDto(medicalStoreDto);
			}
			if (dbAddress.getCustomer() != null) {
				CustomerDto customerDto = this.modelMapper.map(dbAddress.getCustomer(), CustomerDto.class);
				addressDto.setCustomerDto(customerDto);
			}

			ResponseStructure<AddressDto> structure = new ResponseStructure<>();
			structure.setMessage("Address deleted successfully");
			structure.setHttpStatus(HttpStatus.FORBIDDEN.value());
			structure.setData(addressDto);
			return new ResponseEntity<ResponseStructure<AddressDto>>(structure, HttpStatus.FORBIDDEN);

		} else {

			throw new AddressIdNotFoundException("Sorry failed to delete the Address");
		}
	}
}
