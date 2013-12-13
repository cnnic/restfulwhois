package com.cnnic.whois.dao.oauth;

import java.util.List;
import java.util.Map;

import com.cnnic.whois.bean.oauth.UserApp;

public interface UserAppDao {

	public void save(UserApp userApp);

	public void update(int id, UserApp userApp);

	public void delete(int id);
	
	public UserApp getUserAppById(int id);

	public UserApp getUserAppByUserId(int user_id);

	public List<UserApp> getUserApps(int user_id);

	public Map<String, String> getUserApps();
	
}
