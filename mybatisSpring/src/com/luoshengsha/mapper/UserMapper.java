package com.luoshengsha.mapper;

import com.luoshengsha.bean.User;

public interface UserMapper {
	/**����**/
	public void save(User user);
	/**�޸�**/
	public void update(User user);
	/**����**/
	public User find(String id);
	/**ɾ��**/
	public void delete(String id);
}
