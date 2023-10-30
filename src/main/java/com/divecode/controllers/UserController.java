package com.divecode.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.divecode.dao.User;
import com.divecode.services.UserServices;

@RestController
@RequestMapping("/api/v1")
public class UserController {
	
	// Implement service
	@Autowired
	UserServices userServices;
	
	@GetMapping("/users")
	public ResponseEntity<List<User>> listUsers() {
		System.out.println("paso");
		return  ResponseEntity.ok(userServices.getAllUser());
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> userById(@PathVariable(name = "id") Long id) {

		Optional<User> user = userServices.getUserById(id);

		if (user.isPresent()) {
			return ResponseEntity.ok(user.get()); // Status 200 y el User
		} else {
			return ResponseEntity.notFound().build(); // Status 404
		}

	}

	@PostMapping("/user")
	public ResponseEntity<Long> createUser(@RequestBody User user) {	
		userServices.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(user.getId()); // Status 2, solo el ID por seguridad
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUser(@PathVariable(name = "id") Long id, @RequestBody User updatedUser) {
		Optional<User> user = userServices.getUserById(id);
		
		if (user.isPresent()) {
			
			User existingUser = user.get();
			existingUser.setName(updatedUser.getName());
			userServices.save(existingUser);
			
			return ResponseEntity.ok(existingUser); // Status 200 y el User
			
		} else {
			return ResponseEntity.notFound().build(); //Status 404
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable(name = "id") Long id) {
		
		Optional<User> user = userServices.getUserById(id);
		
		if (user.isPresent()) {
			
			userServices.delete(id);
			
			return ResponseEntity.noContent().build(); // Status 204
			
		} else {
			
			return ResponseEntity.notFound().build(); // Status 404
		}
	}

}
