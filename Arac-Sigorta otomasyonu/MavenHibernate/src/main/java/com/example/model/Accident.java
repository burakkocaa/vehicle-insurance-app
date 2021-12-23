package com.example.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCIDENT")
public class Accident implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ACCIDENT_ID")
	private int accidentId;
	
	@Column(name = "DAMAGE", length = 40)
	private String damage;
 
	public String getDamage() {
		return damage;
	}

	public void setDamage(String damage) {
		this.damage = damage;
	}

	@Column(name = "DATE")
	private String date;
	
	
	public int getAccidentId() {
		return accidentId;
	}

	public void setAccidentId(int accidentId) {
		this.accidentId = accidentId;
	}
	
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	
	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}

	@ManyToMany	(cascade = CascadeType.ALL)
	@JoinTable(name = "VEHICLE_ACCIDENT" , joinColumns = @JoinColumn(name = "VEHICLE_ID"), 
	inverseJoinColumns = @JoinColumn(name = "ACCIDENT_ID"))
	
	private List<Vehicle> vehicles = new ArrayList<Vehicle>();
	
	public Accident() {
		// TODO Auto-generated constructor stub
	}
 
	public Accident(String damage, String date) {
		this.damage = damage;
		this.date = date;		
	}
 
	@Override
	public String toString() {
		return "Accident [accidentId=" + accidentId + ", damage=" + damage + ", date=" + date +"]";
	}
}
