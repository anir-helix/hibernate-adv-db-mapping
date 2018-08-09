package com.anir.hbm.mapping.one2many.dbFetchType;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2many.entity.Course;
import com.anir.hbm.mapping.one2many.entity.Instructor;
import com.anir.hbm.mapping.one2many.entity.InstructorDetail;

public class EgarVsLazyFetchDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg-one2many.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();

		// Use Session object to save java object
		try {

			// Start a transaction
			System.out.println("\n ==> Start a transaction");
			session.beginTransaction();
			
			/***********************************
			 * Here we demonstrated the Concept of Hibernate Fetch type
			 * To run test this feature go to Entity 'Instructor'
			 * change the FetchType.
			 * Then Run the program and Watch the console log of both
			 * Hibernate and Syso Print .
			 * 
			 *  You'll see of EAGER Loading all the Courses data will be loaded
			 *  with Instructor data at first time.
			 *  
			 *  For LAZY Loading Courses data will be loaded after, when we call
			 *  course's getter method. First only Instructor data will be loaded.
			 ***********************************/

			int id = 1;
			// Get instructor from DB
			Instructor instructorData = session.get(Instructor.class, id );

			System.out.println("\n DataLoading ==> Instructor data from object: " + instructorData);
			
			
			// Get Courses for the Instructor 
			System.out.println("\n DataLoading ==> Courses for the Instructor : "+ instructorData.getCourses());

			// Commit the transaction
			session.getTransaction().commit();
			System.out.println("\n ==> commit trans done. Success!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
			System.out.println("\n ==> finally block, factory closed!");
		}

	}
}
