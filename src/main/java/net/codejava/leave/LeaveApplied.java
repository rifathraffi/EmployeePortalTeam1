package net.codejava.leave;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "leaves")
public class LeaveApplied {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="empid",nullable = false, unique = true)
	private Long empid;
	
	@Column(name="email",nullable = false, unique = true, length = 45)
	private String email;
	
//	@Column(name = "first_name", nullable = false, length = 20)
//	private String firstName;
//	
//	@Column(name = "last_name", nullable = false, length = 20)
//	private String lastName;
	@Column(name="start_date",nullable = false)
	private String startDate;
	
	@Column(name="end_date",nullable = false)
	private String endDate;
	
	@Column(name="num_of_days",nullable = false)
	private Integer numOfDays;
	
	@Column(name="reason",nullable = false)
	private String reason;
	
	@Column(name="status")
	private String status;

	public LeaveApplied() {}
	
	public LeaveApplied(Long id, Long empid, String email, String startDate, String endDate, Integer numOfDays,
			String reason, String status) {
		super();
		this.id = id;
		this.empid = empid;
		this.email = email;
		this.startDate = startDate;
		this.endDate = endDate;
		this.numOfDays = numOfDays;
		this.reason = reason;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEmpid() {
		return empid;
	}

	public void setEmpid(Long empid) {
		this.empid = empid;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Integer getNumOfDays() {
		return numOfDays;
	}

	public void setNumOfDays(Integer numOfDays) {
		this.numOfDays = numOfDays;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
}
