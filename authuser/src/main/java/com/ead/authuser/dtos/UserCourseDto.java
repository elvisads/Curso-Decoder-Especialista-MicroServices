package com.ead.authuser.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserCourseDto {

	private UUID userId;
	
	@NotNull
	private UUID courseId;
	
}
