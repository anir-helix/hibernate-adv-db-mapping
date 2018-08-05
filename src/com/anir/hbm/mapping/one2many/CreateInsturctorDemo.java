package com.anir.hbm.mapping.one2many;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2many.entity.Course;
import com.anir.hbm.mapping.one2many.entity.Instructor;
import com.anir.hbm.mapping.one2many.entity.InstructorDetail;

public class CreateInsturctorDemo {
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
			// create a Object
			System.out.println("\n ==> create both Object");
			Instructor instructor1 = new Instructor("Anirban", "maiti", "am@email.com");
			
			InstructorDetail instructorDetail1 = new InstructorDetail("http://am.video.in", "coding");

			// Associate the objects
			instructor1.setInstructorDetail(instructorDetail1);

			// Start a transaction
			System.out.println("\n ==> Start a transaction");
			session.beginTransaction();

			// save the object
			System.out.println("\n ==> save the  object for Instructor");
			session.save(instructor1);

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
