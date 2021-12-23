package com.example.dao;

import java.util.List;

import com.example.model.Accident;
import com.example.model.Customer;
import com.example.model.Vehicle;

public interface AccidentDAO {
		
	public void saveAccident(Accident ac);
	
    public List<Accident> getAccidents();

	public void updateAccident(Accident ac);
	
	public void deleteAccident(int id);

	public Accident insertAccident(Accident ac);
	
	public Accident countVehicleAccident(int id);
	
	//public Accident countCustomerAccident(int id);
	
}
