package lt.baltic.talents.superhero.klounada.daos;

import lt.baltic.talents.superhero.klounada.models.User;

public interface UserDao {
	
	boolean login(User user);
	
	boolean create(User user);

}
