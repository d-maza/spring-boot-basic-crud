package com.divecode.services;

import java.util.List;
import java.util.Optional;
import com.divecode.dao.User;

public interface IUserService {
	
	public List<User> getAllUser();
	public Optional<User> getUserById(Long id);
	public Long save(User u);
	public void delete (Long id);
	
}
