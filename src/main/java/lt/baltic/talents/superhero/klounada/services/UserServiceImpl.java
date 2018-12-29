package lt.baltic.talents.superhero.klounada.services;


import lt.baltic.talents.superhero.klounada.daos.Errors;
import lt.baltic.talents.superhero.klounada.daos.UserDao;
import lt.baltic.talents.superhero.klounada.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public boolean login(User user) {
		return userDao.login(user);
	}
	
	@Transactional
	@Override
	public boolean create(User user) throws Errors {
		return userDao.create(user);
	}

}
