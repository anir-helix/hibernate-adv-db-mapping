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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // in My Sql AUTO_INCREMENT=10, thus auto ID starts from 10.
	@Column(name = "id")
	private int id;

	@Column(name = "title")
	private String title;

	/****************************************************************************
	 * If you want to use Cascade for Specific Operation, i.e,; Here on Deletion 
	 * of any Instructor A Course should not get Deleted, here we don't used the
	 * CascaseType.REMOVE
	 ***************************************************************************/

	@ManyToOne(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
	@JoinColumn(name = "instructor_id")
	private Instructor instructor;

	/******************************************************************************
	 * As we know there will be One to many relationship for each COURSE there will
	 * be many REVIEWs. Also Cascade type is based on All, as for a deletion of
	 * COURSE all of the REVIEWs must be deleted.
	 ******************************************************************************/
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "course_id")
	private List<Review> reviews;

	/**
	 * 
	 */
	public Course() {

	}

	/**
	 * Constructor with param
	 * 
	 * @param title
	 */
	public Course(String title) {
		this.title = title;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the instructor
	 */
	public Instructor getInstructor() {
		return instructor;
	}

	/**
	 * @return the reviews
	 */
	public List<Review> getReviews() {
		return reviews;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @param instructor the instructor to set
	 */
	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	/**
	 * @param reviews the reviews to set
	 */
	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	// Adding Convenience method to add review
	public void addReview(Review review) {
		if (reviews == null) {
			reviews = new ArrayList<>();
		}

		reviews.add(review);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + "]";
	}

}
