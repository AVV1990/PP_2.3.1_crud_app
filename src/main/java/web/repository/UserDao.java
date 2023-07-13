package web.repository;

import web.model.User;
import java.util.List;

public interface UserDao {
     List<User> getUsers();

     void addUser(User user);

     User getUserById(Long id);

     void updateUser(Long id, User user);

     void deleteUserById(Long id);


}
