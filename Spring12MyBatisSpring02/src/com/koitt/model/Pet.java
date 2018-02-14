package com.koitt.model;

import java.util.Date;

public class Pet {
	
	private Integer petId;
	private String petName;
	private String ownerName;
	private String petType;
	private Date birtgDate;
	
	public Pet() {}
	
	public Pet(Integer petId, String petName, String ownerName, String petType, Date birtgDate) {
		super();
		this.petId = petId;
		this.petName = petName;
		this.ownerName = ownerName;
		this.petType = petType;
		this.birtgDate = birtgDate;
	}

	public Integer getPetId() {
		return petId;
	}

	public void setPetId(Integer petId) {
		this.petId = petId;
	}

	public String getPetName() {
		return petName;
	}

	public void setPetName(String petName) {
		this.petName = petName;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public Date getBirtgDate() {
		return birtgDate;
	}

	public void setBirtgDate(Date birtgDate) {
		this.birtgDate = birtgDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Pet [petId=");
		builder.append(petId);
		builder.append(", petName=");
		builder.append(petName);
		builder.append(", ownerName=");
		builder.append(ownerName);
		builder.append(", petType=");
		builder.append(petType);
		builder.append(", birtgDate=");
		builder.append(birtgDate);
		builder.append("]");
		return builder.toString();
	}

	
	
	
}
