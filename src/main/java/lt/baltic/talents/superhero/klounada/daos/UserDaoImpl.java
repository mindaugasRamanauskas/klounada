package lt.baltic.talents.superhero.klounada.daos;

import lt.baltic.talents.superhero.klounada.models.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public boolean create(User user) throws Errors {
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where login = ?1");

		query.setParameter(1, user.getLogin());

		List<User> users = query.getResultList();

		if (users != null && users.size() > 0) {
			throw Errors.REGISTER_EXISTS;
		}

		Long id = (Long) sessionFactory.getCurrentSession().save(user);
		
		if (id != null) {
			return true;
		}
		
		return false;
	}

	@Override
	public boolean login(User user) {
		@SuppressWarnings("unchecked")
		TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User where login = ?1 and pwd = ?2");
		
		query.setParameter(1, user.getLogin());
		query.setParameter(2, user.getPwd());
		
		List<User> users = query.getResultList();
		
		if (users != null && users.size() == 1) {
			return true;
		}
		
		return false;
	}

}
