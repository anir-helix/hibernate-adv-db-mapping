package com.anir.hbm.mapping.one2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2many.entity.Course;
import com.anir.hbm.mapping.one2many.entity.Instructor;
import com.anir.hbm.mapping.one2many.entity.InstructorDetail;

public class CreateCoursesDemo {
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
			
			int id = 1;
			//Get instructor from DB
			/***********************************
			 * 		Get Instructor Data by ID
			 ***********************************/
			Instructor instructorData = session.get(Instructor.class, id );

			System.out.println("\n ==> Instructor data from object: " + instructorData);
			
			//Create some courses
			Course course1 = new Course("Coding in Java");
			Course course2 = new Course("Coding in Python3");
			
			// Add courses to instructor 
			instructorData.add(course1);
			instructorData.add(course2);

			// save the courses
			session.save(course1);
			session.save(course2);
			System.out.println("\n ==> save the  object for Instructor");

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
