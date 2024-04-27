 package in.ashokit.entity;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="user_dtls")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private long phno;
	private String email;
	private String pwd;
	private String updatedPwd;
	
	
	@CreationTimestamp
	private LocalDate createdDate;
	
	@UpdateTimestamp
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
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhno() {
		return phno;
	}
	public void setPhno(long phno) {
		this.phno = phno;
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
	public String getUpdatedPwd() {
		return updatedPwd;
	}
	public void setUpdatedPwd(String updatedPwd) {
		this.updatedPwd = updatedPwd;
	}
	
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
