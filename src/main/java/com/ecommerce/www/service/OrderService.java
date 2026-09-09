package com.ecommerce.www.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.www.entity.Addresses;
import com.ecommerce.www.entity.Order;
import com.ecommerce.www.entity.User;
import com.ecommerce.www.exeception.ResourceNotFoundException;
import com.ecommerce.www.repository.AddressRepository;
import com.ecommerce.www.repository.OrderRepository;
import com.ecommerce.www.repository.UserRepository;

@Service
public class OrderService {

    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private AddressRepository addressRepository;

    public OrderService(OrderRepository orderRepository,
                        UserRepository userRepository,
                        AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Order Not Found With Id: " + id));
    }

    public Order createOrder(Order order) {

        Long userId = order.getUser().getUserId();

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "User Not Found With Id: " + userId));

        Long addressId = order.getAddress().getAddressId();

        Addresses address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                    new ResourceNotFoundException(
                        "Address Not Found With Id: " + addressId));

        order.setUser(user);
        order.setAddress(address);

        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}