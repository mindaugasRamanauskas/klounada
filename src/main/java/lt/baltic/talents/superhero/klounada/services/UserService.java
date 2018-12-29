package lt.baltic.talents.superhero.klounada.services;

import lt.baltic.talents.superhero.klounada.daos.Errors;
import lt.baltic.talents.superhero.klounada.models.User;

public interface UserService {

	boolean login(User user);
	
	boolean create(User user) throws Errors;

}
