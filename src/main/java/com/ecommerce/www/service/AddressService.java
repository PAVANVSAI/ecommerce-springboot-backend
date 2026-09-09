package com.ecommerce.www.service;

import com.ecommerce.www.repository.AddressRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.ecommerce.www.exeception.ResourceNotFoundException;
import com.ecommerce.www.repository.UserRepository;
import com.ecommerce.www.entity.Addresses;
import com.ecommerce.www.entity.User;

@Service
public class AddressService {
	private final AddressRepository addressRepository;
	private final UserRepository userRepository;
	public AddressService(AddressRepository addressRepository,UserRepository userRepository) {
		this.addressRepository=addressRepository;
		this.userRepository=userRepository;
	}
	public List<Addresses> getAllAddresses() {
		return addressRepository.findAll();
	}
	public Addresses getAddressById(Long id) {
		return addressRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Not Found With Address Id: "+id));
	}
	public Addresses createAddress(Addresses address) {
		Long userId=address.getUser().getUserId();
		User user =userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("Not Found With Address Id: "+userId));
		address.setUser(user);
		return addressRepository.save(address);
	}
	public void deleteAddress(Long id) {
		addressRepository.deleteById(id);
	}
}
