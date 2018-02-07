package di;

import java.util.HashMap;
import java.util.Map;

public class MyUserDao implements UserDao{

	Map<Integer, User> table = new HashMap<Integer, User>();
	
	@Override
	public void insert(User user) {
		table.put(user.getId(), user);
	}

	@Override
	public User select(int id) {
		return table.get(id);
	}

	
	
}
