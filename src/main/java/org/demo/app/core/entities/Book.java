package org.demo.app.core.entities;

import java.math.BigDecimal;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Book {
	
	@Min(value = 1, message = "Id must be at least 1")
	private long id;
	
	@NotBlank(message = "Title is required")
	private String title;
	
	@Min(value = 5, message = "Price must be at least 5") 
	@Max(value=500, message = "Price must be at most 500")
	private BigDecimal price;

	// Constructors
	public Book() {
	}

	public Book(int id, String title, BigDecimal price) {
		this.id = id;
		this.title = title;
		this.price = price;
	}

	// Getters and Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book{" + "id=" + id + ", title='" + title + '\'' + ", price='" + price + '\'' + '}';
	}
}
