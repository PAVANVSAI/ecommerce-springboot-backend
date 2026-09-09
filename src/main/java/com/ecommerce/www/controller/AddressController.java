package com.ecommerce.www.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.www.entity.Addresses;
import com.ecommerce.www.service.AddressService;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {
	private AddressService addressService;
	public AddressController(AddressService addressService) {
		this.addressService=addressService;
	}
	@GetMapping("/{id}")
	public ResponseEntity<Addresses> getAddresseById(@PathVariable Long id) {
		Addresses address=addressService.getAddressById(id);
		return ResponseEntity.ok(address);
	}
	@GetMapping
	public ResponseEntity<List<Addresses>> getAllAddresses() {
		List<Addresses> addresses=addressService.getAllAddresses();
		return ResponseEntity.ok(addresses);
	}
	@PostMapping
	public ResponseEntity<Addresses> createAddress(@RequestBody Addresses addresses) {
		Addresses createdaddress=addressService.createAddress(addresses);
		return new ResponseEntity<>(createdaddress,HttpStatus.CREATED);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
		addressService.deleteAddress(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping("/{id}")
	public Addresses updateAddresse(@PathVariable Long id,@RequestBody Addresses addresses) {
		Addresses existingAddress=addressService.getAddressById(id);
		existingAddress.setAddressLine(addresses.getAddressLine());
		existingAddress.setCity(addresses.getCity());
		existingAddress.setState(addresses.getState());
		existingAddress.setPincode(addresses.getPincode());
		existingAddress.setUser(addresses.getUser());
		return addressService.createAddress(existingAddress);
	}
}
