package com.ead.course.services.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ead.course.models.CourseModel;
import com.ead.course.models.CourseUserModel;
import com.ead.course.repositories.CourseUserRepository;
import com.ead.course.services.CourseUserService;

@Service
public class CourseUserServiceImpl implements CourseUserService{

	@Autowired
	CourseUserRepository courseUserRepository;

	@Override
	public boolean existsByCourseAndUserId(CourseModel courseModel, UUID userId) {
		return courseUserRepository.existsByCourseAndUserId(courseModel, userId);
	}

	@Override
	public CourseUserModel save(CourseUserModel courseUserModel) {
		return courseUserRepository.save(courseUserModel);
	}

	@Override
	public CourseUserModel saveAndSendSubscriptionUserInCourse(CourseUserModel courseUserModel) {
		return null;
	}
}
