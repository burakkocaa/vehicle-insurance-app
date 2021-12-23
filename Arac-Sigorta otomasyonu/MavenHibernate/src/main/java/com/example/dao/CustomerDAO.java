package com.example.dao;

import java.util.List;

import com.example.model.Customer;

public interface CustomerDAO {
	
	public void saveCustomer(Customer cust);
	
    public void insertCustomer();
	
    public List<Customer> getCustomers();

	public void updateCustomer(Customer cust);
	
	public void deleteCustomer(int id);
	
	public Customer getCustomer(int id) ;
	
	public  List<Customer> LastHundredCustomer();
}
