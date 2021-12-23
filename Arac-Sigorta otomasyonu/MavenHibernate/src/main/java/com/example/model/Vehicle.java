package com.example.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "VEHICLE")
public class Vehicle {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VEHICLE_ID")
	private int vehicleId;
	
	@Column(name = "MODEL", length = 40)
	private String model;
 
	@Column(name = "LICENCEPLATE")
	private String licencePlate;
	
	@Column(name = "BRAND")
	private String brand;
	
	@Column
	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getLicencePlate() {
		return licencePlate;
	}

	public void setLicencePlate(String licencePlate) {
		this.licencePlate = licencePlate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Customer customer;

	
	@ManyToMany(mappedBy = "vehicles")
	private List<Accident> accidents = new ArrayList<Accident>();
	
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Accident> getAccidents() {
		return accidents;
	}

	public void setAccidents(List<Accident> accidents) {
		this.accidents = accidents;
	}

	public Vehicle() {
		// TODO Auto-generated constructor stub
	}
 
	public Vehicle(String brand, String licencePlate,String model) {
		this.model = brand;
		this.licencePlate = licencePlate;	
		this.brand = model;
	}
 
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", brand=" + brand + ", licencePlate=" + licencePlate +  ", model=" + model +"]";
	}
}
