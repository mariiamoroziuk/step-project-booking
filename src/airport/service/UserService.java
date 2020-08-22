package airport.service;

import airport.dao.IAirportDao;
import airport.entity.User;

import java.util.List;

public class UserService {
    private final IAirportDao<User> userDao;

    public UserService(IAirportDao<User> userDao) {
        this.userDao = userDao;
    }

    public List<User> getAllUsers(){
        return this.userDao.findAll();
    }
    public User saveUser(User user){
        return this.userDao.save(user);
    }
    public User getUserByLoginAndPassword(String login, String password){
        return this.userDao.findAll()
                .stream()
                .filter(u-> u.getLogin() != null && u.getLogin().equals(login) && u.getPassword() != null && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    public User getUserByName(String name, String surname){
        return this.userDao.findAll()
                .stream()
                .filter(u-> u.getUserName().equals(name) && u.getUserSurname().equals(surname))
                .findFirst()
                .orElse(null);
    }
}
