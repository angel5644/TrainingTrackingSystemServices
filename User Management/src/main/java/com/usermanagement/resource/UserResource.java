package com.usermanagement.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanagement.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserResource {

	@Autowired
    UserRepository userRepository;
}
