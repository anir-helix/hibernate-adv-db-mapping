package com.anir.hbm.mapping.one2many.dbfetchtype.lazyfetch;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.anir.hbm.mapping.one2many.entity.Course;
import com.anir.hbm.mapping.one2many.entity.Instructor;
import com.anir.hbm.mapping.one2many.entity.InstructorDetail;

/**
 * There is a big disadvantage in Lazy Loading; In some cases we need to handle
 * lazily-initialized objects with a special care or you might end up with an
 * exception due to delay initialization. 
 * As per our previous example, we know when lazy loading is enabled, 
 * Course data won't be initialized and loaded into a memory 
 * until an explicit call is made to it.
 * 
 * Thus to Resolve this problem we have 2 options
 * 1. Initialize or load the object before closing the session
 * 2. Hibernate Query with HQL
 * 
 */
public class FetchJoinDemo {
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

			lazyLoadingSolution1(session);	// OPTION-1
			lazyLoadingSolution2(factory);	// OPTION-2

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// add clean up code
            session.close();
			factory.close();
			System.out.println("\n ==> finally block, factory closed!");
		}

	}

	/**
	 * Option1: Initialize or load the object before closing the session
	 * 
	 * @param session
	 */
	private static void lazyLoadingSolution1(Session session) {
		// Start a transaction
		System.out.println("\n Opt1 ==> Start a transaction");
		session.beginTransaction();

		int id = 1;
		// Get instructor from DB
		Instructor instructorData = session.get(Instructor.class, id);
		System.out.println("\n Opt1 DataLoading ==> Instructor data from object: " + instructorData);

		// Get Courses for the Instructor First
		System.out.println("\n Opt1 DataLoading ==> Courses for the Instructor : " + instructorData.getCourses());

		// Commit the transaction
		session.getTransaction().commit();
		System.out.println("\n Opt1 ==> commit trans done. Success!");

		// Recall for Course Object
		System.out.println("\n Opt1 re-DataLoading ==> Courses for the Instructor : " + instructorData.getCourses());
	}
	
	/**
	 * Option 2: Hibernate Query with HQL
	 * 
	 * @param session
	 * @param factory 
	 */
	private static void lazyLoadingSolution2(final SessionFactory factory) {
		// Case 1: get the course data after we commit a transaction
		Session session = factory.getCurrentSession();
		hqlCase1(session);
		
		// Case 2: get the course data after we commit and CLOSED! a transaction
		session = factory.getCurrentSession();
		hqlCase2(session, factory);
    }

	/**
	 * @param session
	 */
	private static void hqlCase1(final Session session) {
		// Start a transaction
		System.out.println("\n Opt2-Case 1 ==> Start a transaction");
		session.beginTransaction();

		int id = 1;
		// Get instructor from DB using HQL
		Query<Instructor> query = session.createQuery("select instr from Instructor instr "
				+"JOIN FETCH instr.courses "
				+ "where instr.id= :instructorID", Instructor.class);
		
		//Set Query parameter, i.e.; instructorID
		query.setParameter("instructorID", id);
		
		Instructor instructorData = query.getSingleResult();
		System.out.println("\n Opt2-Case 1 DataLoading ==> Instructor data from object: " + instructorData);

		// Commit the transaction
		session.getTransaction().commit();
		System.out.println("\n Opt2-Case 1 ==> commit trans done. Success!");
		
		// Recall and Get Course Object
		System.out.println("\n Opt2-Case 1 re-DataLoading ==> Courses for the Instructor : " + instructorData.getCourses());
	}

	private static void hqlCase2(Session session, SessionFactory factory) {
		// start a transaction
        session.beginTransaction();
                    
        // get the instructor from db
        int theId = 1;
        Instructor tempInstructor = session.get(Instructor.class, theId);                    
        
        System.out.println("Opt2-Case 2: Instructor: " + tempInstructor);    
        
        // commit transaction
        session.getTransaction().commit();
        
        // close the session
        session.close();

        System.out.println("\nOpt2-Case 2: The session is now closed!\n");

        //
        // THIS HAPPENS SOMEWHERE ELSE / LATER IN THE PROGRAM

        // YOU NEED TO GET A NEW SESSION
        //
        
        System.out.println("\n\nOpt2-Case 2: Opening a NEW session \n");

        session = factory.getCurrentSession();
        
        session.beginTransaction();
        
        // get courses for a given instructor
        Query<Course> query = session.createQuery("select c from Course c "
                                                + "where c.instructor.id=:theInstructorId",    
                                                Course.class);
        
        query.setParameter("theInstructorId", theId);
        
        List<Course> tempCourses = query.getResultList();
        
        System.out.println("\ntempCourses: " + tempCourses);
        
        // now assign to instructor object in memory
        tempInstructor.setCourses(tempCourses);
        
        System.out.println("\nOpt2-Case 2: Courses: " + tempInstructor.getCourses());
        
        session.getTransaction().commit();
        
        System.out.println("Opt2-Case 2: Done!");
	}
 
}