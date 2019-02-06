package br.com.planningtravel.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="travels")
public class Travel extends Auditable<Long> {

	private static final long serialVersionUID = -5825905403274267157L;

	private String place;
	
	private String picture;
	
	@OneToMany(
			mappedBy = "travel", 
			cascade = {CascadeType.ALL}, 
			fetch = FetchType.LAZY, 
			orphanRemoval=true
	)
	//@JsonManagedReference
	private Set<Wish> wishes;
	
	public Travel() {

	}

	public Travel(String place, String picture, Set<Wish> wishes) {
		super();
		this.place = place;
		this.picture = picture;
		this.wishes = wishes;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Set<Wish> getWishes() {
		if (this.wishes == null) {
			this.wishes = new HashSet<>();
		}
		return wishes;
	}

	public void setWishes(Set<Wish> wishes) {
		this.wishes = wishes;
	}
	
	public void addWishes(Wish wishes) {
		if (this.wishes == null) {
			this.wishes = new HashSet<>();
		}
		this.wishes.add(wishes);
	}

	public boolean hasWishes() {
		return this.wishes != null && this.wishes.size() > 0;
	}	

}
