package com.sunbeam.daos;

import java.util.List;

import com.sunbeam.pojos.User;

public interface UserDao extends AutoCloseable {
	User findByEmail(String email) throws Exception;
	User findById(int userId) throws Exception;
	List<User> findAll() throws Exception;
	int save(User u) throws Exception;
	int update(User u) throws Exception;
	int deleteById(int userId) throws Exception;
}
