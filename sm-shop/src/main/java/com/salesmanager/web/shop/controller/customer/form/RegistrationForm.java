package com.salesmanager.web.shop.controller.customer.form;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.salesmanager.web.utils.FieldMatch;

@FieldMatch.List({
    @FieldMatch(first="password",second="checkPassword",message="password.notequal")
    
})
public class RegistrationForm implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Email (message="{messages.invalid.email}")
    @NotEmpty(message="{NotEmpty.customer.emailAddress}")
	private String emailAddress;
	
	@NotEmpty(message="{NotEmpty.saleRequestForm.customerName}")
	private String firstName;
	
	@NotEmpty(message="{NotEmpty.saleRequestForm.customerMobile}")
	private String lastName;
	
	@NotEmpty(message="{NotEmpty.saleRequestForm.messageBody}")
	private String gender;
	
	@NotEmpty(message="{NotEmpty.saleRequestForm.messageBody}")
	private String country;
	
	@Size(min=6, message="{registration.password.not.empty}")
	private String password;
	
	@Size(min=6, message="{registration.password.not.empty}")
	private String checkPassword;
	
	private String city;
	
	private String birthdate;
	
	private String interests;
	
	private String zone;
	
	private String address;
	
	private String stateProvince;
	
	private String recaptcha_challenge_field;
	private String recaptcha_response_field;
	
	@NotEmpty(message="{validaion.recaptcha.not.matched}")
    @Size( min=1,message="{validaion.recaptcha.not.matched}")
	public String getRecaptcha_challenge_field()
    {
        return recaptcha_challenge_field;
    }
    public void setRecaptcha_challenge_field( final String recaptcha_challenge_field )
    {
        this.recaptcha_challenge_field = recaptcha_challenge_field;
    }
    
   
    @NotEmpty(message="{validaion.recaptcha.not.matched}")
    @Size( min=1,message="{validaion.recaptcha.not.matched}")
    public String getRecaptcha_response_field()
    {
        return recaptcha_response_field;
    }
    public void setRecaptcha_response_field( final String recaptcha_response_field )
    {
        this.recaptcha_response_field = recaptcha_response_field;
    }
	public String getCheckPassword() {
		return checkPassword;
	}
	public void setCheckPassword(String checkPassword) {
		this.checkPassword = checkPassword;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInterests() {
		return interests;
	}

	public void setInterests(String interests) {
		this.interests = interests;
	}

	public String getZone() {
		return zone;
	}

	public void setZone(String zone) {
		this.zone = zone;
	}

	public String getStateProvince() {
		return stateProvince;
	}

	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	

}
