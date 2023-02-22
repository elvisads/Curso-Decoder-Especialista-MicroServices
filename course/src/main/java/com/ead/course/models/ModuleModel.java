package com.ead.course.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL) /* Faz com que ao dar um get nesta classe não mostre os campo que estiverem em branco no JSON*/
@Entity
@Table(name = "TB_MODULES")
public class ModuleModel  implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID moduleId;
	
	@Column(nullable = false, unique = true, length = 150)
	private String title;
	
	@Column(nullable = false, unique = true, length = 250)
	private String description;
	
	@Column(nullable = false)
	@JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "dd-MM-yyy HH:mm:ss")
	private LocalDateTime creationDate;
}
