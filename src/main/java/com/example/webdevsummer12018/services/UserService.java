package com.example.webdevsummer12018.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.webdevsummer12018.models.User;
import com.example.webdevsummer12018.models.UsernameExists;
import com.example.webdevsummer12018.repositories.UserRepository;

@RestController
public class UserService {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/api/user")
	public List<User> findAllUsers() {
		return (List<User>) userRepository.findAll();
	}
	
	@PostMapping("/api/user")
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}
	
	@GetMapping("/api/user/{userId}")
	public User findUserById(@PathVariable("userId") int userId) {
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			return data.get();
		}
		else {
		return null;
		}
	}
	
	@PostMapping("/api/profile")
	public User profile(@RequestBody User user) {
		Optional<User> user1 = userRepository.findById(user.getId());
		if (user1.isPresent()) {
			return (User) user1.get();
		}
		else {
			return null;
		}
	}
	
	
	@PutMapping("/api/user/{userId}")
	public User updateUser(@PathVariable("userId") int userId, @RequestBody User newUser) {
		Optional<User> data = userRepository.findById(userId);
		if(data.isPresent()) {
			
			User user = data.get();
			user.setFirstName(newUser.getFirstName());
			user.setLastName(newUser.getLastName());
			user.setDateOfBirth(newUser.getDateOfBirth());
			user.setEmail(newUser.getEmail());
			user.setUsername(newUser.getUsername());
			user.setPassword(newUser.getPassword());
			user.setRole(newUser.getRole());
			user.setPhone(newUser.getPhone());
			userRepository.save(user);
			return user;
		}
		return null;
	}
	
	@DeleteMapping("/api/user/{userId}")
	public void deleteUser(@PathVariable("userId") int userId) {
		userRepository.deleteById(userId);
	}
	
	public User findUserByUsername(String username) {
		User user = null;
		List<User> user_list = (List<User>) userRepository.findUserByUsername(username);
		for (User user2 : user_list) {
			user = user2;
			break;
		}
		return user;
	}
	
	@PostMapping("/api/register")
	public User register(@RequestBody User user, HttpServletResponse response) {
		List<User> user_list = (List<User>) userRepository.findUserByUsername(user.getUsername());
		if (user_list.isEmpty()) {
			return userRepository.save(user);
		}
		else
		{
			response.setStatus(HttpServletResponse.SC_CONFLICT);
		}
		return user;
	}
	
	@PostMapping("/api/login")
	public User login(@RequestBody User user, HttpServletResponse response) {
		Iterable<User> user1 =  userRepository.findUserByCredentials(user.getUsername(), user.getPassword());
		for (User user2 : user1) {
			return user2;
		}
		response.setStatus(HttpServletResponse.SC_CONFLICT);
		return null;
	}
	
	@GetMapping("/api/session/set/{attr}/{value}")
	public String setSessionAttribute(
			@PathVariable("attr") String attr,
			@PathVariable("value") String value,
			HttpSession session) {
		session.setAttribute(attr, value);
		return attr + " = " + value;
	}
	
	@GetMapping("/api/session/get/{attr}")
	public String getSessionAttribute(
			@PathVariable ("attr") String attr,
			HttpSession session) {
		return (String) session.getAttribute(attr);
	}

	@GetMapping("/api/session/invalidate")
	public String invalidateSession(
	HttpSession session) {
		session.invalidate();
	return "session invalidated";
	}

}
