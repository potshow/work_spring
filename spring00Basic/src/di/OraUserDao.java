package di;

import java.util.ArrayList;
import java.util.List;

public class OraUserDao implements UserDao{

	List<User> list = new ArrayList<User>();

	@Override
	public void insert(User user) {
		list.add(user);

	}

	@Override
	public User select(int id) {
		for (User user : list) {
			if (user.getId() == id) {
				return user;
			}
		}
		
		return null;
	}

}
