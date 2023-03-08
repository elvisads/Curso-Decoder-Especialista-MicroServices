package com.ead.authuser.specification;


import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.ead.authuser.models.UserCourseModel;
import com.ead.authuser.models.UserModel;

import jakarta.persistence.criteria.Join;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
	
	@And({ 
		@Spec(path="userType", spec= Equal.class), 
		@Spec(path="userStatus", spec= Equal.class),
		@Spec(path="email", spec= LikeIgnoreCase.class),
		@Spec(path="fullName", spec= LikeIgnoreCase.class)
		})

	public interface UserSpec extends Specification<UserModel> {
	}

	public static Specification<UserModel> userCourseId(final UUID courseId) {
		return (root, query, cb) -> {
			query.distinct(true);
			Join<UserModel, UserCourseModel> userProd = root.join("usersCourses");
			return cb.equal(userProd.get("courseId"), courseId);
		};
	}
}
