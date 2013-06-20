package org.springframework.data.mybatis.domain;

import java.util.List;

public class Customer extends AbstractEntity {
	private String firstName;
	private String lastName;
	private EmailAddress emailAddress;
	private List<Address> addresses;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public EmailAddress getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(EmailAddress emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	@Override
	public String toString() {
		return "Customer: [" + getId() + "] " + firstName + " " + lastName + " " + emailAddress + " " + addresses;
	}
}
