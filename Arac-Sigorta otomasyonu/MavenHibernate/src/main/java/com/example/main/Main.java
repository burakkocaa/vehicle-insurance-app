package com.example.main;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.SystemException;
import javax.transaction.Transaction;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.model.Accident;
import com.example.model.Customer;
import com.example.model.Vehicle;
import com.example.dao.AccidentDAO;
import com.example.dao.CustomerDAO;
import com.example.dao.VehicleDAO;

import com.example.util.HibernateUtility;
import com.example.dao.daoImpl.CustomerDAOImpl;
import com.example.dao.daoImpl.VehicleDAOImpl;
import com.example.dao.daoImpl.AccidentDAOImpl;

public class Main {

	public static void main(String[] args) {
	
		CustomerDAOImpl cdao = new CustomerDAOImpl();
		AccidentDAOImpl acdao=new AccidentDAOImpl();
		VehicleDAOImpl vehicdao=new VehicleDAOImpl();
		
	 	
	 	Accident ac1 =new Accident("Az Hasarlı","01-02-2002");
	 	Accident ac2 =new Accident("Orta Hasarlı","05-11-2003");
	 	Accident ac3 =new Accident("Cok Hasarlı","01-09-1998");
	 	Accident ac4 =new Accident("Hasarsız","27-05-1973");
	 	
		Customer cst = new Customer("Ramesh", "5326562021");
		Customer c1 = new Customer("Barıs Aykan", "5546661412");
		Customer c2 = new Customer("Kemal Bol", "5468952354");
		Customer c3 = new Customer("Ferhat Pırıl", "5236322024");
		Customer c4 = new Customer("Ayse Yılmaz", "5864256521");
		
		Vehicle v1 = new Vehicle( "1992","06AH454","TOFAS");
		Vehicle v2 = new Vehicle("1993","71AU673", "KIA");
		Vehicle v3=new Vehicle("1998","58LA232","PEUGEOT");
		Vehicle v4 = new Vehicle("2000","44KE673", "RANGE-ROVER");
		Vehicle v5=new Vehicle("2003","58LA232","MERCEDES");
		Vehicle v6 = new Vehicle( "1881","05AH457","SAHIN");
		Vehicle v7 = new Vehicle("1983","64DF52", "KIA");
		Vehicle v8=new Vehicle("1978","44KA232","RENAULT");
		Vehicle v9 = new Vehicle("1997","44KE88", "RANGE-ROVER");
		Vehicle v10=new Vehicle("2012","58LP32","MERCEDES");
	 	
//-------------------------------DB DEKI SON 100 MUSTERI------------------------------------------------------------		
		//cdao.LastHundredCustomer();
//-------------------------------------BIR MUSTERININ KAC ARACI VAR --------------------------------------------------------
		//vehicdao.findcountVehicle(1);
//----------------------------------BIR ARAC KAC FARKLI KAZAYA KARISMIS----------------------------------------------------------------------------
		//acdao.countVehicleAccident(13);
//------------------------------------BIR MUSTERI KAC FARKLI KAZAYA KARISMIS---??????????????--------------------------------------------	
        //acdao.countCustomerAccident(13);	
//---------------------VERITABANINDA BULUNAN EN YÜKSEK KAZAYA SAHİP ON ARAC VE SAHIBI-----???????????????------------------------------------------------
	    //  vehicdao.countMaxVehicle(8);
//--------------------------ARAC KAZA TABLOSU DOLDURMA-----------------------------------------------------------------------	
		
	/*	v2.getAccidents().add(ac3);
		ac3.getVehicles().add(v2);

		vehicdao.saveVehicle(v2);
		acdao.insertAccident(ac3);*/
		
	//	vehicdao.saveVehicle(v22);
	//	acdao.saveAccident(ac4);
		
//------------------------------MUSTERI ARAC TABLOSU DOLDURMA-----------------------------------------------------------------
		
	/*	c3.getVehicles().add(v6);
		c3.getVehicles().add(v7);

		v6.setCustomer(c3);
		v7.setCustomer(c3);
		
		vehicdao.saveVehicle(v7);
		cdao.saveCustomer(c3);*/
		
//----------------------------------KAZA INSERT ETME--------------------------------------------------------------------------------------	
		/*acdao.saveAccident(ac2);
		acdao.saveAccident(ac3);
		acdao.saveAccident(ac4);*/		
		
		//acdao.deleteAccident(ac3);
//------------------------------------------------------------CUSTOMER CRUD-----------------------------------------------------
	 		
		/*	cdao.saveCustomer(c1);
		cdao.saveCustomer(c2);
		cdao.saveCustomer(c3);
		cdao.saveCustomer(c4);*/
		
	//	cdao.insertCustomer();
		//c.setName("Emine");
	    //customer.setPhone("5495663234");
		//customerDAOImpl.insertCustomer(c);	
			
//--------------------------------------------------------------------------------
		// update customer
	//	Customer cust2 = new Customer("Ram", "5326562022");
	//	cdao.updateCustomer(cust2);
	  //customerDAOImpl.updateCustomer(1, "Emine","5211234565");
//---------------------------------------------------------------------------		
		// get customers
	 //  List<Customer> customers = cdao.getCustomers();
		//customers.forEach(s -> System.out.println(s.getName()));
	//	customerDAOImpl.getAllCustomers().add(c);
//---------------------------------------------------------------------------------------		
		// get single customer
		//Customer customer = cdao.getCustomer(1);
		//System.out.println(customer.getName());
		
//		customer=null;
		//	customerDAOImpl.findCustomer(4);
//----------------------------------------------------------------------------		
		// delete customer
		//cdao.deleteCustomer(4);
		//customerDAOImpl.deleteCustomer(10);
			
//-------------------------------------------------------------------VEHICLE CRUD-----------------------------------------------------

		// update vehicle
	   //  Customer cust2 = new Customer("Ram", "5326562022");
	  //   cdao.updateCustomer(cust2);
	     
		//customerDAOImpl.updateCustomer(1, "Emine","5211234565");
		
	/*	vehicdao.saveVehicle(v2);
		vehicdao.saveVehicle(v3);
		vehicdao.saveVehicle(v4);
		vehicdao.saveVehicle(v5);
		vehicdao.saveVehicle(v6);
		vehicdao.saveVehicle(v7);
		vehicdao.saveVehicle(v8);
		vehicdao.saveVehicle(v9);
		vehicdao.saveVehicle(v10);*/
//---------------------------------------------------------------------------		
		// get vehicle
		// List<Customer> customers = cdao.getCustomers();
		// customers.forEach(s -> System.out.println(s.getName()));
		// cdao.getCustomers().add(c);
//---------------------------------------------------------------------------------------		
		// get single vehicle
		//Customer customer = cdao.getCustomer(1);
		//System.out.println(customer.getName());
				
//		customer=null;
		//	customerDAOImpl.findCustomer(4);
//----------------------------------------------------------------------------		
		// delete vehicle
		//cdao.deleteCustomer(4);
		//customerDAOImpl.deleteCustomer(10);	
		
	}
}