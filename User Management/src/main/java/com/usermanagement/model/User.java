package com.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USERS", catalog = "XE")
@Data
public class User {

	@Id
	@Column(name = "id",length=32, unique = true, nullable = false, insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_NAME")
	@SequenceGenerator(name = "SEQUENCE_NAME", sequenceName = "SEQUENCE_NAME", allocationSize = 1, initialValue = 1)
	private Integer id;
	@Column(name = "first_name",nullable = false, length = 50)
	private String firstName;
	@Column(name = "last_name",nullable = false, length = 50)
	private String lastName;
	@Column(name = "email",nullable = false, length = 50)
	private String email;
	@Column(name = "type",nullable = false, length =4 )
	private Integer type;
	
	public User(){}

}
