package com.springboot.insurance_app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PolicyHolder {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
private int id;
	private String name;
	private String contact;

	private String panNumber;
	private Integer age;
public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}


@Override
public String toString() {
	return "PolicyHolder [id=" + id + ", name=" + name + ", contact=" + contact + ", panNumber=" + panNumber
			+ ", age=" + age + "]";
}

}
