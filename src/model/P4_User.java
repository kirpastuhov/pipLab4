package model;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "P4_User.getByLogin", query = "select user.Login from P4_User user where user.Login = :login"),
        @NamedQuery(name = "P4_User.getByPassword", query = "select user from P4_User user where user.Password = :password and user.Login = :login")
})
public class P4_User {
    @Id
    @GeneratedValue
    private int Id;
    private String Password;
    private String Login;
    private String Salt;

    public String getSalt() {
        return Salt;
    }

    public void setSalt(String salt) {
        Salt = salt;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getLogin() {
        return Login;
    }

    public void setLogin(String login) {
        Login = login;
    }
}


