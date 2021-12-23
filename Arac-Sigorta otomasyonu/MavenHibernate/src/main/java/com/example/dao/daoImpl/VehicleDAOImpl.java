package com.example.dao.daoImpl;

import java.util.List;

import javax.persistence.Query;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.example.dao.VehicleDAO;
import com.example.model.Accident;
import com.example.model.Customer;
import com.example.model.Vehicle;
import com.example.util.HibernateUtility;


public class VehicleDAOImpl implements VehicleDAO{
	
	Session session = HibernateUtility.getSessionFactory().openSession();
	
	public void saveVehicle(Vehicle vehic) {
		Transaction transaction = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(vehic);
			
			// operation 2
			session.get(Vehicle.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void insertVehicle() {
		Transaction transaction = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();

			String hql = "INSERT INTO Vehicle (brand,licencePlate,model) "
					+ "SELECT brand, licencePlate,model FROM Vehicle";
			Query query = session.createQuery(hql);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void updateVehicle(Vehicle vehic) {
		Transaction transaction = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();

			// save the student object
			String hql = "UPDATE Vehicle set brand = :brand ,licencePlate = :licencePlate ,model= :model " + "WHERE id = :vehicleId";
			Query query = session.createQuery(hql);
			query.setParameter("brand", vehic.getBrand());
			query.setParameter("licencePlate", vehic.getLicencePlate());
			query.setParameter("model", vehic.getModel());
			query.setParameter("vehicleId", 1);
			int result = query.executeUpdate();
			System.out.println("Rows affected: " + result);

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public void deleteVehicle(int id) {

		Transaction transaction = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Customer cust = session.get(Customer.class, id);
			if (cust != null) {
				String hql = "DELETE FROM Vehicle " + "WHERE id = :vehicleId";
				Query query = session.createQuery(hql);
				query.setParameter("vehicleId", id);
				int result = query.executeUpdate();
				System.out.println("Rows affected: " + result);
			}

			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	public Vehicle getVehicles(int id) {

		Transaction transaction = null;
		Vehicle vehic = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Vehicle V WHERE V.id = :vehicleId";
			Query query = session.createQuery(hql);
			query.setParameter("vehicleId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				vehic = (Vehicle) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return vehic;
	}

	public List<Vehicle> getVehicles() {
		
			return session.createQuery("from Vehicle", Vehicle.class).list();
	}

     
	
	public Vehicle findcountVehicle(int id) {
		
		
		Transaction transaction = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();

			Vehicle vehicle=session.get(Vehicle.class, id);
			Customer cust=session.get(Customer.class, id);
			
			if (cust != null) {			
				String hql = "select count(v) from Vehicle v where v.customer.customerId = :customer_customerId  ";
				Query query = session.createQuery(hql);
				query.setParameter("customer_customerId", id);
				
				//List result=query.getResultList();
				Long result =(Long)query.getSingleResult();


				System.out.println(cust.getCustomerId()+" id no'lu musterinin "+result +" tane araci vardir.");				
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
			}
			e.printStackTrace();
		}
		return null;
	}
	
/*	
	@Override
	 public Vehicle countMaxDamage() {
		
		Transaction transaction = null;
		
		String hql = " max(v.vehicleId) from Vehicle as v where v.customer.customerId = :customer_customerId order by v.vehicleId ";
		List<Vehicle> list = session.createQuery(hql).list();
		for(int i=0; i<list.size(); i++) {
			Vehicle ve = (Vehicle) list.get(i);
			System.out.println(ve.vehicleId()+", "+ ve.getBookName()+
					","+book.getWriter()+", "+book.getPrice());
		}				
		return null;
}*/

	@Override
		public  List<Vehicle> countMaxVehicle(int id) {
			
			Transaction transaction = null;
			List<Vehicle> vehic=null;
			Vehicle vehicle=session.get(Vehicle.class, id);
			Customer cust=session.get(Customer.class, id);
			
			try {	      
				      transaction = session.beginTransaction();
				      
			    	  vehic = session.createQuery("v.vehicleId FROM Vehicle as v WHERE v.customer.customerId = :customer_customerId ORDER BY v.vehicleId ").setMaxResults(10).list(); 
			    	  Criteria cr = session.createCriteria(Vehicle.class);

			    	// To get distinct count of a property.
			    	cr.setProjection(Projections.countDistinct("vehicleId"));

			    	// To get maximum of a property.
			    	cr.setProjection(Projections.max("vehicleId"));
			    	
			    	((Query) vehic).setParameter("customer_customerId", id);
			    	
			    	  for (Iterator<Vehicle> iterator = vehic.iterator(); 
			        		  iterator.hasNext();){
			        	  Vehicle vehicl = (Vehicle) iterator.next(); 
			        	 System.out.print("MUSTERI ID: " + vehicle.getCustomer().getCustomerId()+"\t"); 
			             System.out.print("ARAC ID: " + vehicle.getVehicleId()+"\t"); 
			             System.out.print("  KAZA ID: " + vehicle.getAccidents()+"\n"); 
			          }
			       }
			      catch (Exception e) {
			    	  if (transaction !=null) {
			    		  transaction.rollback();
			    	  }
			    	  e.printStackTrace(); 
			      }
			   return vehic;
			}
	}	
