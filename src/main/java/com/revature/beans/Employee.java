package com.revature.beans;

public class Employee {
	// The fields of an Employee object
	private int id;
	private String name;
	private String address;
	private String phone;
	private String email;
	private String password;
	private double availableReimbursement;
	private int supervisorId;
	private int headId;
	private int benCoId;
	private boolean isSupervisor;
	private boolean isHead;
	private boolean isBenCo;
	
	//An empty constructor
	public Employee() {
		
	}

	// Getter and setter methods for the id field
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	// Getter and setter methods for the name field
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	// Getter and setter methods for the address field
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	// Getter and setter methods for the phone field
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	// Getter and setter methods for the email field
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	// getter and setter methods for the password field
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	// Getter and setter methods for the availableReimbursement field
	public double getAvailableReimbursement() {
		return availableReimbursement;
	}
	public void setAvailableReimbursement(double availableReimbursement) {
		this.availableReimbursement = availableReimbursement;
	}

	// Getter and setter methods for the supervisorId field
	public int getSupervisorId() {
		return supervisorId;
	}
	public void setSupervisorId(int supervisorId) {
		this.supervisorId = supervisorId;
	}

	// Getter and setter methods for the headId field
	public int getHeadId() {
		return headId;
	}
	public void setHeadId(int headId) {
		this.headId = headId;
	}

	// Getter and setter methods for the benCoId field
	public int getBenCoId() {
		return benCoId;
	}
	public void setBenCoId(int benCoId) {
		this.benCoId = benCoId;
	}

	// Getter and setter methods for the isSupervisor field
	public boolean isSupervisor() {
		return isSupervisor;
	}
	public void setSupervisor(boolean isSupervisor) {
		this.isSupervisor = isSupervisor;
	}

	// Getter and setter methods for the isHead field
	public boolean isHead() {
		return isHead;
	}
	public void setHead(boolean isHead) {
		this.isHead = isHead;
	}

	// Getter and setter methods for the isBenCo field
	public boolean isBenCo() {
		return isBenCo;
	}
	public void setBenCo(boolean isBenCo) {
		this.isBenCo = isBenCo;
	}
<<<<<<< HEAD

=======
<<<<<<< HEAD
<<<<<<< HEAD
	
=======

>>>>>>> ed4c19b395be48303ea5d2b50fae57261aaac459
>>>>>>> a4e13e3ea662f521fddb934d6a54cc6107fd9160
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email="
				+ email + ", password=" + password + ", availableReimbursement=" + availableReimbursement
				+ ", supervisorId=" + supervisorId + ", headId=" + headId + ", benCoId=" + benCoId + ", isSupervisor="
				+ isSupervisor + ", isHead=" + isHead + ", isBenCo=" + isBenCo + "]";
	}
<<<<<<< HEAD
	
=======
<<<<<<< HEAD
=======
>>>>>>> 7b70f8a4828261d33ad79825f492008e610c1147
=======
	
	
>>>>>>> ed4c19b395be48303ea5d2b50fae57261aaac459
>>>>>>> a4e13e3ea662f521fddb934d6a54cc6107fd9160
}
