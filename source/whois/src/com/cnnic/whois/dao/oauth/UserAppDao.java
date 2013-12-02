package com.cnnic.whois.dao.oauth;

import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.UserApp;

public interface UserAppDao {

	public void save(UserApp userApp);

	public void update(int id, UserApp userApp);

	public void delete(int id);

	public UserApp getUserAppById(int user_id);

	public List<UserApp> getUserApps(int user_id);

	public Map<String, String> getUserApps();
	
}
