package com.springboot.cud.operation.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
private String id;
private String fName;
private String lName;
private String email;
private Integer pinCode;
private Date birthDate;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getfName() {
	return fName;
}
public void setfName(String fName) {
	this.fName = fName;
}
public String getlName() {
	return lName;
}
public void setlName(String lName) {
	this.lName = lName;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public Integer getPinCode() {
	return pinCode;
}
public void setPinCode(Integer pinCode) {
	this.pinCode = pinCode;
}
public Date getBirthDate() {
	return birthDate;
}
public void setBirthDate(Date birthDate) {
	this.birthDate = birthDate;
}
public Boolean getIsActive() {
	return isActive;
}
public void setIsActive(Boolean isActive) {
	this.isActive = isActive;
}
private Boolean isActive;
}
