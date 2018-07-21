package com.anir.hbm.mapping.one2one.bidir;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2one.bidir.entity.Instructor;
import com.anir.hbm.mapping.one2one.bidir.entity.InstructorDetail;

/**
 * Int this Class we are going to Implement Get Data based on One-2-One
 * bi-directional Mapping Here we are going to get 'Instructor' data based on
 * 'Instructor Detail'
 * 
 * @author OPTLPTP219
 *
 */
public class GetDemo {

	public static void main(String[] args) {
		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();

		// Use Session object to save java object
		try {
			// Start a transaction
			System.out.println("\n ==> Start a transaction");
			session.beginTransaction();

			// Instructor Details by ID
			int id = 2;
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

			System.out.println("\n ==> Founded Instructor Detail by ID: " + instructorDetail);
			System.out.println(
					"\n ==> Instructor data from InstructorDetail object: " + instructorDetail.getInstructor());

			// Commit the transaction
			session.getTransaction().commit();
			System.out.println("\n ==> commit trans done. Success!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Handle Leak issue : close both session and factory
			session.close();
			factory.close();
			System.out.println("\n ==> finally block, factory closed!");
		}

	}

}
