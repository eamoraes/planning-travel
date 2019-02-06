package br.com.planningtravel.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.planningtravel.enums.WishType;

@Entity
@Table(name="wishes")
public class Wish extends Auditable<Long> {

	private static final long serialVersionUID = 2266744343585581537L;

	@Enumerated(EnumType.STRING)
	private WishType type;
	
	private String description;
	
	private BigDecimal value;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "travel_id")
	//@JsonBackReference
	private Travel travel;
	
	public Wish() {

	}

	public Wish(WishType type, String description, BigDecimal value, Travel travel) {
		super();
		this.type = type;
		this.description = description;
		this.value = value;
		this.travel = travel;
	}

	public WishType getType() {
		return type;
	}

	public void setType(WishType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public Travel getTravel() {
		return travel;
	}

	public void setTravel(Travel travel) {
		this.travel = travel;
	}
	
}
