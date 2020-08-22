package airport.entity;

import java.io.Serializable;

public class User extends AbstractEntity implements Serializable {
    private String userName;
    private String userSurname;
    private String login;
    private String password;

    public User(String userName, String userSurname){
        setUserName(userName);
        setUserSurname(userSurname);
    }

    public User(String userName, String userSurname, String login, String password){
        this(userName, userSurname);
        setLogin(login);
        setPassword(password);
    }

    public User(Long id, String userName, String userSurname, String login, String password){
        this(userName, userSurname, login, password);
        setId(id);
    }

    public String getUserName(){
        return this.userName;
    }
    private void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserSurname(){
        return this.userSurname;
    }
    private void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }
    public String getLogin(){
        return this.login;
    }
    private void setLogin(String login) {
        this.login = login;
    }
    public String getPassword(){
        return this.password;
    }
    private void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID='" + this.getId() + '\'' +
                ", userName='" + userName + '\'' +
                ", userSurname='" + userSurname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
