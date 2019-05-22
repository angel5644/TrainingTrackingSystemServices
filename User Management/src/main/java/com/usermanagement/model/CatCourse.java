package com.usermanagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CATCOURSES", catalog = "XE")
@Data
@NoArgsConstructor
public class CatCourse {

	@Id
	@Column(name = "id",length=32, unique = true, nullable = false, insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQUENCE_NAME_CATCOURSE")
	@SequenceGenerator(name = "SEQUENCE_NAME_CATCOURSE", sequenceName = "SEQUENCE_NAME_CATCOURSE", allocationSize = 1, initialValue = 1)
	private int id;
	
	@Column(name = "idcategory",nullable = false, length = 32, unique=true)
	private int idCategory;
	
	@Column(name = "idcourse",nullable = false, length = 32, unique=true)
	private int idCourse;
}
