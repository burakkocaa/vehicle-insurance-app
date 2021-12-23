package com.example.dao;
import java.util.List;

import com.example.dao.daoImpl.VehicleDAOImpl;
import com.example.model.Vehicle;
import com.example.model.Customer;
import com.example.model.Accident;

public interface VehicleDAO {
	
public void saveVehicle(Vehicle vehic);
	
    public void insertVehicle();
	
    public List<Vehicle> getVehicles();

	public void updateVehicle(Vehicle vehic);
	
	public void deleteVehicle(int id);
	
	public Vehicle getVehicles(int id) ;
	
	public Vehicle findcountVehicle(int id);

	List<Vehicle> countMaxVehicle(int id);
	

}
