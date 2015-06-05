package org.springframework.data.mybatis.domain;

public class EmailAddress {

	private String value;

	protected EmailAddress() {
	}

	public EmailAddress(String emailAddress) {
		this.value = emailAddress;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmailAddress that = (EmailAddress) o;
		return !(value != null ? !value.equals(that.value) : that.value != null);

	}

	@Override
	public int hashCode() {
		return value != null ? value.hashCode() : 0;
	}

	@Override
	public String toString() {
		return value;
	}
}
