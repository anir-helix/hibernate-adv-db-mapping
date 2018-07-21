package com.anir.hbm.mapping.one2one.unidir;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.anir.hbm.mapping.one2one.unidir.entity.Instructor;
import com.anir.hbm.mapping.one2one.unidir.entity.InstructorDetail;

/**
 * Int this Class we are going to Implement Get Data based on One-2-One Uni-directional Mapping
 * Here we are going to get 'Instructor Detail' data based on 'Instructor'
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
			System.out.println("Start a transaction");
			session.beginTransaction();

			// Instructor and Details by ID
			int id = 2;

			/************************************
			 * 		Get InstructorDetail by ID
			 ************************************/
			InstructorDetail instructorDetail = session.get(InstructorDetail.class, id);

			System.out.println("\n ==> Founded Instructor Detail by ID: " + instructorDetail);
			

			/***********************************
			 * 		Get Instructor Data by ID
			 ***********************************/
			Instructor instructorData = session.get(Instructor.class, id);

			System.out.println("\n ==> Instructor data from object: " + instructorData);

			// Check if both Instructor Details are same or not!!

			if (instructorData.getInstructorDetail().getHobby().equals(instructorDetail.getHobby())) {
				System.out.println("\n ==> Founded Instructor Detail both are same: " + instructorDetail.getHobby());
			}
			
			// Commit the transaction
			session.getTransaction().commit();
			System.out.println("commit trans done. Success!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			factory.close();
			System.out.println("finally block, factory closed!");
		}

	}

}
