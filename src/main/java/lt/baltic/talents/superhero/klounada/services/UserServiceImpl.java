package lt.baltic.talents.superhero.klounada.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lt.baltic.talents.superhero.klounada.daos.UserDao;
import lt.baltic.talents.superhero.klounada.models.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Transactional(readOnly = true)
	@Override
	public boolean login(User user) {
		return userDao.login(user);
	}

}
