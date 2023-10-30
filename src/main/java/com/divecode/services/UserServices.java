package com.divecode.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.divecode.dao.User;
import com.divecode.dto.IUser;

@Service        
public class UserServices implements IUserService {

	@Autowired
	private IUser data;

	@Override
	public List<User> getAllUser() {
		return data.findAll();
	}

	@Override
	public Optional<User> getUserById(Long id) {
		return data.findById(id);
	}

	@Override
	public Long save(User u) {
		data.save(u);
	return	u.getId();
	}

	@Override
	public void delete(Long id) {
		data.deleteById(id);
	}


}
