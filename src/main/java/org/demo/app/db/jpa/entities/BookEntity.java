package org.demo.app.db.jpa.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="BOOK" )
public class BookEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @Column(name="id", nullable=false)
	private long id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="title")
	private String title ;

    @Column(name="price")
	private BigDecimal price;

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

    
}