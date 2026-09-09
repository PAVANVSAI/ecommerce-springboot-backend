package com.ecommerce.www.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="addresses",schema="ecommerce")
public class Addresses {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="address_id")
	private Long addressId;
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	@Column(name="address_line")
	private String addressLine;
	@Column(name="city")
	private String city;
	@Column(name="state")
	private String state;
	@Column(name="pincode")
	private String pincode;
	public void setUser(User user) {
		this.user=user;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine=addressLine;
	}
	public void setCity(String city) {
		this.city=city;
	}
	public void setState(String state) {
		this.state=state;
	}
	public void setPincode(String pincode) {
		this.pincode=pincode;
	}
	public Long getAddressId() {
		return addressId;
	}
	public User getUser() {
		return user;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPincode() {
		return pincode;
	}
}
