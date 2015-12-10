package com.luoshengsha.mapper;

import com.luoshengsha.bean.User;

public interface UserMapper {
	/**±£´æ**/
	public void save(User user);
	/**ĞŞ¸Ä**/
	public void update(User user);
	/**²éÕÒ**/
	public User find(String id);
	/**É¾³ı**/
	public void delete(String id);
}
