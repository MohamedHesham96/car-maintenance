package com.blue.soft.store.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blue.soft.store.DAO.UserRepository;
import com.blue.soft.store.entity.User;

@Service
public class UserService {

	@Autowired
	UserRepository UserRepository;

	@Autowired
	HttpSession httpSession;

	public boolean checkUser(String name, String password) {

		User theUser = UserRepository.findByNameAndPassword(name, password);
		if (theUser != null) {

			httpSession.setAttribute("user", theUser);
			httpSession.setMaxInactiveInterval(10 * 60);
			return true;

		} else {

			return false;
		}
	}

	public User getUserById(String userId) {

		return UserRepository.findById(userId).get();
	}

	public void saveUser(User theUser) {

		UserRepository.save(theUser);

	}
}
