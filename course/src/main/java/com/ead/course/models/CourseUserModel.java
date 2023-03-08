package com.ead.course.models;

import java.io.Serializable;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) /* Faz com que ao dar um get nesta classe não mostre os campo que estiverem em branco no JSON*/
@Entity
@Table(name = "TB_COURSES_USERS")
public class CourseUserModel implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private CourseModel course;
	
	@Column(nullable = false)
	private UUID userId;
	
}
