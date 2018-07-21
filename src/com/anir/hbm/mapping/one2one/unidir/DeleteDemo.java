package com.anir.hbm.mapping.one2one.unidir;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2one.unidir.entity.Instructor;
import com.anir.hbm.mapping.one2one.unidir.entity.InstructorDetail;

/**
 * Int this Class we are going to Implement Get Data based on One-2-One
 * Uni-directional Mapping Here we are going to Delete 'Instructor' data, based
 * on Cascading 'Instructor Detail' will be deleted also not vice-versa
 * 
 * @author OPTLPTP219
 *
 */
public class DeleteDemo {
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
			// Start a transaction
			System.out.println("\n ==> Start a transaction");
			session.beginTransaction();

			// Instructor by ID
			int id = 1;
			Instructor instructor = session.get(Instructor.class, id);

			System.out.println("\n ==> Founded Instructor by ID: " + instructor);

			// Delete the Instructor
			if (instructor != null) {
				System.out.println("\n ==> Deleting that Instructor...");

				// Note By deleting instructor we are also deleting
				// instructorDetails.
				// Because we have a CascadeType set up for all.So the
				// CascadeType of all applies to saving and also deleting the
				// actual object. So the details object will also get deleted
				// with this code that we have here for session.delete.

				session.delete(instructor);
			}

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
