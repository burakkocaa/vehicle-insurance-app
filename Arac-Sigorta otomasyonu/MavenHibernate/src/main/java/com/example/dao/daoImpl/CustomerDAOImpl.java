package com.example.dao.daoImpl;

import java.util.List;

import javax.persistence.Query;

import java.io.Serializable;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.dao.CustomerDAO;
import com.example.model.Customer;
import com.example.model.Vehicle;
import com.example.util.HibernateUtility;

public class CustomerDAOImpl implements CustomerDAO{
	
	public void saveCustomer(Customer cust) {
		Transaction transaction = null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(cust);
			
			// operation 2
			session.get(Customer.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}
	
	public void insertCustomer() {
		Transaction transaction = null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			String hql = "INSERT INTO Customer (Name, Phone) "
					+ "SELECT Name, Phone FROM Customer";
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

	public void updateCustomer(Customer cust) {
		Transaction transaction = null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// save the student object
			String hql = "UPDATE Customer set Name = :Name  Phone= :Phone" + "WHERE id = :customerId";
			Query query = session.createQuery(hql);
			query.setParameter("Name", cust.getName());
			query.setParameter("Phone", cust.getPhone());
			query.setParameter("customerId", 1);
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

	public void deleteCustomer(int id) {

		Transaction transaction = null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Customer cust = session.get(Customer.class, id);
			if (cust != null) {
				String hql = "DELETE FROM Customer " + "WHERE id = :customerId";
				Query query = session.createQuery(hql);
				query.setParameter("customerId", id);
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

	public Customer getCustomer(int id) {

		Transaction transaction = null;
		Customer cust = null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			// start a transaction
			transaction = session.beginTransaction();

			// get an student object
			String hql = " FROM Customer C WHERE C.id = :customerId";
			Query query = session.createQuery(hql);
			query.setParameter("customerId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				cust = (Customer) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return cust;
	}

	public List<Customer> getCustomers() {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			return session.createQuery("from Customer", Customer.class).list();
		}
	}
	
	public  List<Customer> LastHundredCustomer() {
			
		Transaction transaction = null;
		List<Customer> customers=null;
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			      
			      transaction = session.beginTransaction();
		    	  customers = session.createQuery("FROM Customer ").setMaxResults(10).list(); 
		          for (Iterator<Customer> iterator = customers.iterator(); 
		        		  iterator.hasNext();){
		        	  Customer customer = (Customer) iterator.next(); 
		             System.out.print("Name: " + customer.getName()+"\t"); 
		             System.out.print("  Phone: " + customer.getPhone()+"\n"); 
		          }
		       }
		      catch (Exception e) {
		    	  if (transaction !=null) {
		    		  transaction.rollback();
		    	  }
		    	  e.printStackTrace(); 
		      }
		   return customers;
		}
	
	
}

