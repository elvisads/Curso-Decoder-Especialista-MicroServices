package com.ead.course.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ead.course.dtos.CourseDto;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {

	@Autowired
	CourseService courseService;

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody @Valid CourseDto courseDto){
        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseDto, courseModel);
        courseModel.setCreationDate(LocalDateTime.now(ZoneId.of("UTC")));
        courseModel.setLastUpdateDate(LocalDateTime.now(ZoneId.of("UTC")));
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseModel));
    }

	@DeleteMapping("/{courseId}")
	public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId) {
		Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
		if (!courseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("course Not Found.");
		}
		courseService.delete(courseModelOptional.get());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("course Deleted successfully");
	}

	@PutMapping("/{courseId}")
	public ResponseEntity<Object> updateCourse(@PathVariable(value = "courseId") UUID courseId,
			@RequestBody @Valid CourseDto courseDto) {
		Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
		if (!courseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("course Not Found.");
		}
		var courseModel = courseModelOptional.get();
		courseModel.setName(courseDto.getName());
		courseModel.setDescription(courseDto.getDescription());
		courseModel.setImageUrl(courseDto.getImageUrl());
		courseModel.setCourseStatus(courseDto.getCourseStatus());
		courseModel.setCourseLevel(courseDto.getCourseLevel());
		return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseModel));
	}

	@GetMapping
	public ResponseEntity<List<CourseModel>> getAllCourses() {
		return ResponseEntity.status(HttpStatus.OK).body(courseService.findAll());
	}

	@GetMapping("/{courseId}")
	public ResponseEntity<Object> getOneCourses(@PathVariable(value="courseId") UUID courseId) {
		Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
		if (!courseModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("course Not Found.");
		}
		return ResponseEntity.status(HttpStatus.OK).body(courseModelOptional.get());
	}

}