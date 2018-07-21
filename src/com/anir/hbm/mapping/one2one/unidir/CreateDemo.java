package com.anir.hbm.mapping.one2one.unidir;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2one.unidir.entity.Instructor;
import com.anir.hbm.mapping.one2one.unidir.entity.InstructorDetail;

public class CreateDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();

		// Use Session object to save java object
		try {
			// create a Object
			System.out.println("\n ==> create both Object");
			Instructor instructor1 = new Instructor("Anirban", "maiti", "am@email.com");
			Instructor instructor2 = new Instructor("Ask", "Jhon", "adc@email.com");
			
			InstructorDetail instructorDetail1 = new InstructorDetail("http://am.video.in", "coding");
			InstructorDetail instructorDetail2 = new InstructorDetail("http://akj.video.in", "music");

			// Associate the objects
			instructor1.setInstructorDetail(instructorDetail1);
			instructor2.setInstructorDetail(instructorDetail2);

			// Start a transaction
			System.out.println("\n ==> Start a transaction");
			session.beginTransaction();

			// save the object
			// Note: Here we only need to save instructor Object
			// Now one thing that's really important here is that this will also
			// save the details object because of CascadeType.ALL.
			//
			// So CascadeType.ALL means it's going to save the actual object and
			// also any associated objects.
			//
			// That same operation is going to cascade down to any associated
			// objects.
			//
			// So, for saving, for deleting and so on.
			//
			// It'll cascade to any object that's associated with it. So, really
			// all we have to do is say save Instructor and it'll also save the
			// associated object. In this case the
			// InstructorDetail object.

			System.out.println("\n ==> save the  object for Instructor");
			session.save(instructor1);
			session.save(instructor2);

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
