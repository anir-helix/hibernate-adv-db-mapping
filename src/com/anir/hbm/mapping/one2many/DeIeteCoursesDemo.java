package com.anir.hbm.mapping.one2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2many.entity.Course;
import com.anir.hbm.mapping.one2many.entity.Instructor;
import com.anir.hbm.mapping.one2many.entity.InstructorDetail;

public class DeIeteCoursesDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg-one2many.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();

		// Use Session object to save java object
		try {

			// Start a transaction
			System.out.println("\n ==> Start a transaction");
			session.beginTransaction();

			int id = 13;
			// Get Course from DB
			/***********************************
			 * Get Course Data by ID
			 ***********************************/
			Course courseData = session.get(Course.class, id);

			System.out.println("\n ==> Instructor data from object: " + courseData);

			// Delete Courses for the Instructor
			System.out.println("\n ==> Deleting Courses...");
			session.delete(courseData);

			// Commit the transaction
			session.getTransaction().commit();
			System.out.println("\n ==> commit trans done. Success!");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
			System.out.println("\n ==> finally block, factory closed!");
			System.out.println("\n\t==>Deletion done Now Run GetInsturctorCoursesDemo class to test if the Cascade worked!");
		}

	}
}
