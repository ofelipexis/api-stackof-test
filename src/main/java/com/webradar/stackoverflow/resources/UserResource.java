package com.webradar.stackoverflow.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webradar.stackoverflow.entities.Question;
import com.webradar.stackoverflow.entities.User;
import com.webradar.stackoverflow.services.QuestionService;
import com.webradar.stackoverflow.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> findAll() {
		List<User> list = userService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}/questions")
	public ResponseEntity<List<Question>> findQuestionsByUserId(@PathVariable String id) {
		User user = new User(Long.parseLong(id));
		List<Question> list = questionService.findByUser(user);		
		return ResponseEntity.ok().body(list);
	}	
	
	@PostMapping
	public User insert(@RequestBody User user) {
		return userService.save(user);
	}
}