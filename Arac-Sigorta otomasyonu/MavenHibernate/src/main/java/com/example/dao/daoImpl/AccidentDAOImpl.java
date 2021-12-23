package com.example.dao.daoImpl;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.dao.AccidentDAO;
import com.example.model.Accident;
import com.example.model.Customer;
import com.example.model.Vehicle;
import com.example.util.HibernateUtility;

import java.util.List;
import java.util.Iterator;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.SharedSessionContract;

public class AccidentDAOImpl implements AccidentDAO{

	Session session = HibernateUtility.getSessionFactory().openSession();
			
	@Override
	public void saveAccident(Accident ac) {
		
		Transaction transaction = null;
		try  {
			// start a transaction
			transaction = session.beginTransaction();
			
			// operation 1
			Object object = session.save(ac);
			
			// operation 2
			session.get(Accident.class, (Serializable) object);
			
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
	}

	@Override
	public Accident insertAccident(Accident ac) {
		Transaction transaction = null;
		try {
			// start a transaction
			transaction = session.beginTransaction();

			String hql = "INSERT INTO Accident (damage,date) "
					+ "SELECT damage,date FROM Accident";
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
		return ac;
				
	}


	public void updateAccident(Accident ac) {
		Transaction transaction = null;
		try  {
			// start a transaction
			transaction = session.beginTransaction();

			// save the student object
			String hql = "UPDATE Accident set damage = :damage ,date = :date" + "WHERE id = :accidentId";
			Query query = session.createQuery(hql);
			query.setParameter("damage", ac.getDamage());
			query.setParameter("date", ac.getDate());
			query.setParameter("accidentId", 1);
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

	public void deleteAccident(int id) {

		Transaction transaction = null;
		try  {
			// start a transaction
			transaction = session.beginTransaction();

			// Delete a student object
			Accident acc = session.get(Accident.class, id);
			if (acc != null) {
				String hql = "DELETE FROM Accident " + "WHERE id = :accidentId";
				Query query = session.createQuery(hql);
				query.setParameter("accidentId", id);
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

	public Accident getAccidents(int id) {

		Transaction transaction = null;
		Accident acc = null;
		try  {
			// start a transaction
			transaction = session.beginTransaction();

			// get an object
			String hql = " FROM Accident A WHERE A.id = :accidentId";
			Query query = session.createQuery(hql);
			query.setParameter("accidentId", id);
			List results = query.getResultList();
			
			if (results != null && !results.isEmpty()) {
				acc = (Accident) results.get(0);
			}
			// commit transaction
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return acc;
	}

	public List<Accident> getAccidents() {
		try (Session session = HibernateUtility.getSessionFactory().openSession()) {
			return session.createQuery("from Accident", Accident.class).list();
		}
	}

	@Override
	 public Accident countVehicleAccident(int id) {
		
		Vehicle vehicle=session.get(Vehicle.class, id);
		
		Query query = session.createQuery("select count(*) as cnt from Accident a where a.accidentId in"
				+ "(select accidents.accidentId from Vehicle v inner join v.accidents accidents where v.vehicleId=:vehicle_id)    ");
		query.setParameter("vehicle_id", id);
		
		Long result =(Long)query.getSingleResult();
		
		System.out.println(vehicle.getVehicleId()+" id no'lu araç "+result +" tane kazaya karışmıştır.");

		return null;
	}
	
/*	
	@Override
	 public Accident countCustomerAccident(int id) {
		
		Customer customer=session.get(Customer.class, id);
		Vehicle vehicle=session.get(Vehicle.class, id);
		Accident accid=session.get(Accident.class, id);
		
		Query query = session.createQuery("(select c.customerId ac.accidentId from Accident ac"
				+ "inner join Vehicle v On vehicleId=ac.accidentId"
				+ "inner join Customer c On c.customerId=v.vehicleId  where c.customerId=:customer_id)    ");
		query.setParameter("customer_id", id);
		
		Long result =(Long)query.getSingleResult();
		
		System.out.println(customer.getCustomerId()+" id no'lu musteri "+result +" tane kazaya karışmıştır.");

		return accid;
	}*/

}
