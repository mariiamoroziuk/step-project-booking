package airport.controller;

import airport.entity.User;
import airport.logger.AirportLogger;
import airport.service.UserService;

import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        AirportLogger.info("создание обьекта UserController");
        this.userService = userService;
    }

    public List<User> getAllUsers(){
        AirportLogger.info("получение списка всех пользователей");
        return this.userService.getAllUsers();
    }
    public User saveUser(User user){
        AirportLogger.info("сохранение пользователя");
        return this.userService.saveUser(user);
    }
    public User getUserByLoginAndPassword(String login, String password){
        AirportLogger.info("получение пользователя по логину и паролю");
        return this.userService.getUserByLoginAndPassword(login, password);
    }
    public User getUserByName(String name, String surname){
        AirportLogger.info("получение пользователя по имени и фимилии");
        return this.userService.getUserByName(name, surname);
    }
}
