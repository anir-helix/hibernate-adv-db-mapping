package com.anir.hbm.mapping.one2one.bidir;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2one.bidir.entity.Instructor;
import com.anir.hbm.mapping.one2one.bidir.entity.InstructorDetail;

/**
 * Int this Class we are going to Implement Get Data based on One-2-One
 * Bi-directional Mapping Here we are going to Delete 'Instructor' data, based
 * on Cascading 'Instructor Detail' will be deleted also vice-versa
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
			int id = 4;
			// For Bi-directional deletion of both Table data Entry
			// to do so change the code in InstructorDetails class also
			deleteWholeDataByInstructorDetails(session, id);

			id = 5;
			//For deletion of InstructorDetails table entry
			deleteInstructorDetailsByID(session, id);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			factory.close();
			System.out.println("\n ==> finally block, factory closed!");
		}

	}

	/**
	 * This function delete Data By InstructorDetails. Here we find the
	 * InstructorDetails by ID, then delete that. based on bi-directional Mapping
	 * type Instructor Data will also get deleted
	 * 
	 * @param session
	 * @param id
	 */
	public static void deleteWholeDataByInstructorDetails(final Session session, final int id) {
		// Start a transaction
		System.out.println("\n ==> Start a transaction");
		session.beginTransaction();

		// get data by ID
		InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

		System.out.println("\n ==> Founded instructorDetail by ID: " + instructorDetail);

		// Delete the Instructor
		if (instructorDetail != null) {
			System.out.println("\n ==> Deleting that InstructorDetails and Instructor...");

			// Note By deleting instructor we are also deleting
			// instructor data.
			// Because we have a CascadeType set up for CascadeType.ALL
			// with Bi-directional mapping.
			// So the CascadeType of all applies to saving and also deleting
			// the actual object. So the details object will also get
			// deleted with this code that we have here for session.delete.

			session.delete(instructorDetail);
		}

		// Commit the transaction
		session.getTransaction().commit();
		System.out.println("\n ==> commit trans done. Success!");
	}

	/**
	 * This function delete Data By InstructorDetails ID. Here we find the
	 * InstructorDetails by ID, then delete only that. Here based on bi-directional
	 * Mapping type Instructor Data will not get deleted.
	 * 
	 * @param session
	 * @param id
	 */
	public static void deleteInstructorDetailsByID(final Session session, final int id) {
		// Start a transaction
		System.out.println("\n ==> Start a transaction");
		session.beginTransaction();

		// get data by ID
		InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

		System.out.println("\n ==> Founded instructorDetail by ID: " + instructorDetail);

		// Delete the Instructor
		if (instructorDetail != null) {
			System.out.println("\n ==> Deleting that InstructorDetails...");

			// Note By deleting instructor we are also deleting
			// instructor data.
			// Because we have a not set CascadeType set up for CascadeType.ALL
			// with Bi-directional mapping.
			// So specified CascadeTypes applies to saving and also deleting
			// the actual object. So the Details object will only get
			// deleted with this code that we have here for session.delete.

			// First Remove Associated Object Reference (Instructor.InstructorDetail)
			// Break Bi-Directional link
			instructorDetail.getInstructor().setInstructorDetail(null);

			session.delete(instructorDetail);
		}

		// Commit the transaction
		session.getTransaction().commit();
		System.out.println("\n ==> commit trans done. Success!");
	}
}
