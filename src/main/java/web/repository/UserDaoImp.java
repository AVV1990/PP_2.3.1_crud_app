package web.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> getUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.merge(user);
    }

    @Override
    public User getUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    @Transactional
    public void updateUser(Long id, User user) {
        User userAfterUpdate = getUserById(id);
        userAfterUpdate.setFirstName(user.getFirstName());
        userAfterUpdate.setLastName(user.getLastName());
        userAfterUpdate.setAge(user.getAge());
        entityManager.merge(userAfterUpdate);
    }


    @Override
    @Transactional
    public void deleteUserById(Long id) {
        entityManager.remove(getUserById(id));
    }
}
