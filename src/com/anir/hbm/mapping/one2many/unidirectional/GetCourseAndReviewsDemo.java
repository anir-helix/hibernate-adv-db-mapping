package com.anir.hbm.mapping.one2many.unidirectional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2many.entity.Course;
import com.anir.hbm.mapping.one2many.entity.Instructor;
import com.anir.hbm.mapping.one2many.entity.InstructorDetail;
import com.anir.hbm.mapping.one2many.entity.Review;

public class GetCourseAndReviewsDemo {
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		// Create Session Factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg-one2many.xml")
				.addAnnotatedClass(Instructor.class).addAnnotatedClass(InstructorDetail.class)
				.addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		// Create Session
		Session session = factory.getCurrentSession();

		// Use Session object to save java object
		try {

			// Start a transaction
			System.out.println("\n ==> Start a transaction");
			session.beginTransaction();

			//Get course with ID, 
			//Note: set you ID as per your DB entry
			int id=15;
			
			Course course = session.get(Course.class, id);

			System.out.println("\n ==> Get the object for Course:\n");
			System.out.println(course);
			System.out.println("Reviews:" + course.getReviews());

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
