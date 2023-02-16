package com.ead.authuser.specification;


import org.springframework.data.jpa.domain.Specification;

import com.ead.authuser.models.UserModel;

import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;

public class SpecificationTemplate {
	
	@And({ 
		@Spec(path="userType", spec= Equal.class), 
		@Spec(path="userStatus", spec= Equal.class),
		@Spec(path="email", params = "email", spec= LikeIgnoreCase.class)
		})

	public interface UserSpec extends Specification<UserModel> {
	}
}
