package com.jsp.projecta1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Staff {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int staffId;
	private String staffName;
	private String staffEmail;
	private String staffPassword;
	private long phoneNumber;
	
	
	@OneToOne
	private MedicalStore medicalStore;
	
	
	@ManyToOne
	private Admin admin;
	
}
