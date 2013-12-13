package com.cnnic.whois.dao.oauth;

import java.util.List;

import com.cnnic.whois.bean.oauth.User;

public interface UserDao {

	public void save(User user);

	public void update(int id, User user);

	public void delete(int id);

	public User getUserById(int id);

	public List<User> getUsers();
	
	public User findByUserIdAndPassword(String userId, String password);
	

}
