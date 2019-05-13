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
@Table(name = "COURSES", catalog = "XE")
@Data
public class Courses {

	@Id
	@Column(name = "id",length=32, unique = true, nullable = false, insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_NAME_COURSE")
	@SequenceGenerator(name = "SEQUENCE_NAME_COURSE", sequenceName = "SEQUENCE_NAME_COURSE", allocationSize = 1, initialValue = 1)
	private Integer id;
	@Column(name = "name",nullable = false, length = 50, unique=true)
	private String name;
	@Column(name = "description",nullable = true, length = 500)
	private String description;
	@Column(name = "content",nullable = false, length = 4000)
	private String content;
	
	public Courses(){}
	
}
