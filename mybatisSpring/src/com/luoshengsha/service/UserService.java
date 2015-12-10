package com.luoshengsha.service;

import com.luoshengsha.bean.User;

public interface UserService {
	/**±£´æ**/
	public void save(User user);
	/**ÐÞ¸Ä**/
	public void update(User user);
	/**²éÕÒ**/
	public User find(String id);
	/**É¾³ý**/
	public void delete(String id);
}
