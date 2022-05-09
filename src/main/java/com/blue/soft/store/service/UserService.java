package com.blue.soft.store.service;

import java.util.List;

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

			httpSession.setAttribute("id", theUser.getId());
			httpSession.setAttribute("name", theUser.getName());

			httpSession.setAttribute("isHasBank", theUser.isHasBank());
			httpSession.setAttribute("isHasBuys", theUser.isHasBuys());
			httpSession.setAttribute("isHasClients", theUser.isHasClients());
			httpSession.setAttribute("isHasCompanies", theUser.isHasCompanies());
			httpSession.setAttribute("isHasItemMove", theUser.isHasItemMove());
			httpSession.setAttribute("isHasMain", theUser.isHasMain());
			httpSession.setAttribute("isHasReports", theUser.isHasReports());
			httpSession.setAttribute("isHasSales", theUser.isHasSales());
			httpSession.setAttribute("isHasStore", theUser.isHasStore());

			httpSession.setMaxInactiveInterval(10000000);
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

	public List<User> getAllUsers() {

		return (List<User>) UserRepository.findAll();

	}
}
