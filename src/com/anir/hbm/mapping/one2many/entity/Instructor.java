package com.anir.hbm.mapping.one2many.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="instructor")
public class Instructor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
	private String firstName;
	
	@Column(name="last_name")
	private String lastName;
	
	@Column
	private String email;

	@OneToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="instructor_detail_id")
    private InstructorDetail instructorDetail;

	/****************************************************************************
	 * Here We done Mapping in respect to 'instructor' property field define
	 * in Course class 
	 * 
	 * Here on Deletion of any Instructor A Course should not get Deleted
	 * thus, here we don't used the CascaseType.REMOVE directly
	 * 
	 ***************************************************************************/
	
	/***************************************************************************
	 * Here we have Also represent the Data fetch type
	 * i.e.; Eager Type and Lazy Type
	 * We have to mention the FetchType based on that Hibernate will load the
	 * data on demand or whole set at first call.
	 *  
	***************************************************************************/
	
	@OneToMany(mappedBy="instructor", fetch= FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH })
	private List<Course> courses;
	
	/**
	 * 
	 */
	public Instructor() {
	}

	/**
	 * @param firstName
	 * @param lastName
	 * @param email
	 */
	public Instructor(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return the instructorDetail
	 */
	public InstructorDetail getInstructorDetail() {
		return instructorDetail;
	}

	/**
	 * @return the courses
	 */
	public List<Course> getCourses() {
		return courses;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @param instructorDetail the instructorDetail to set
	 */
	public void setInstructorDetail(InstructorDetail instructorDetail) {
		this.instructorDetail = instructorDetail;
	}

	
	/**
	 * @param courses the courses to set
	 */
	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Instructor [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ "]";
	}

	// Adding a Convenience method for Bi-directional relationship
	public void add(Course tempCourse) {
		if(this.courses == null ) {
			this.courses = new ArrayList<>();
		}
		
		this.courses.add(tempCourse);
		
		tempCourse.setInstructor(this);
	}
	
}
