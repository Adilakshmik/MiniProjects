package in.ashokit.dto;

import java.time.LocalDate;

import in.ashokit.entity.CityEntity;
import in.ashokit.entity.CountryEntity;
import in.ashokit.entity.StateEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class UserDto {
	
	
	private Integer userId;
	private String name;
	private String email;
	private String pwd;
	private Long phno;
	private String updatedPwd;
	
	private LocalDate createdDate;
	private LocalDate updatedDate;
	
	@JoinColumn(name="countryId")
	@ManyToOne
	private CountryEntity country;
	
	@JoinColumn(name="stateId")
	@ManyToOne
	private StateEntity state;
	
	@JoinColumn(name="cityId")
	@ManyToOne
	private CityEntity city;
	
	public CountryEntity getCountry() {
		return country;
	}
	public void setCountry(CountryEntity country) {
		this.country = country;
	}
	public StateEntity getState() {
		return state;
	}
	public void setState(StateEntity state) {
		this.state = state;
	}
	public CityEntity getCity() {
		return city;
	}
	public void setCity(CityEntity city) {
		this.city = city;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
	public Long getPhno() {
		return phno;
	}
	public void setPhno(Long phno) {
		this.phno = phno;
	}
	public String getUpdatedPwd() {
		return updatedPwd;
	}
	public void setUpdatedPwd(String updatedPwd) {
		this.updatedPwd = updatedPwd;
	}
	public LocalDate getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	public LocalDate getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(LocalDate updatedDate) {
		this.updatedDate = updatedDate;
	}

}
