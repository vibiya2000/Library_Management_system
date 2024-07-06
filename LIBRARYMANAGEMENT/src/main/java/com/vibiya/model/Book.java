package com.vibiya.model;

import jakarta.persistence.Column;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "BookDetails")
public class Book {

	@Id
	@Column(unique = true)
	private Integer bookId;
	private String author;
	private String title;
	private boolean availability;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User borrowedBy;

	Book() {

	}

	public Book(int bookId, String author, String title, boolean availability, User borrowedBy) {
		super();
		this.bookId = bookId;
		this.author = author;
		this.title = title;
		this.availability = availability;
		this.borrowedBy = borrowedBy;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	public User getBorrowedBy() {
		return borrowedBy;
	}

	public void setBorrowedBy(User borrowedBy) {
		this.borrowedBy = borrowedBy;
	}

}
