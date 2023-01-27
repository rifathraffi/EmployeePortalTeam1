package net.codejava.project;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectid;
	
	@Column(name="project_name")
	private String project_name;
	
	@Column(name="client_name")
	private String client_name;
	
	@Column(name="end_date")
	private Date end_date;
	
	public Project() {}

	

	
	public Project(Long projectid, String project_name, String client_name, Date end_date) {
		super();
		this.projectid = projectid;
		this.project_name = project_name;
		this.client_name = client_name;
		this.end_date = end_date;
	}


	public Long getProjectid() {
		return projectid;
	}




	public void setProjectid(Long projectid) {
		this.projectid = projectid;
	}




	public String getProject_name() {
		return project_name;
	}

	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public Date getEnd_date() {
		return end_date;
	}

	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}

	
	
	
}