package com.divecode.dto;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.divecode.dao.User;

@Repository
public interface IUser extends JpaRepository<User, Long> {
}
