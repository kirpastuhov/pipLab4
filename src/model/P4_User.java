package model;

import javax.persistence.*;

/*
    Цэ у нас модель сущности БД, чтобы указать что это сущность мы пишем @Entity,
    очевидно, без этого робить не будет

    NamedQueries({ ... }) пишем JPQL заросы к БД, каждый запрос имеет имя, чтоб можно было юзать запрос по имени

    Вообще можно было делать запросы и подругому, ORM все дела, но это сложно и нафиг надо

    "select user.Login from P4_User user where user.Login = :login" - запрос, где login это изменяемый параметр,
    который мы указываем, когда используем запрос (об этом написано в HitDataRecord)

 */

@Entity

@NamedQueries({
        @NamedQuery(name = "P4_User.getByLogin", query = "select user.Login from P4_User user where user.Login = :login"),
        @NamedQuery(name = "P4_User.getPassword", query = "select user from P4_User user where user.Password = :password and user.Login = :login")
})
public class P4_User {
    @Id
    @GeneratedValue
    private int Id;
    private String Password;
    private String Login;
    private String Salt;

    //геттеры и сеттеры, ибо так безопаснее, чем напрямую пихать значение в поле класса
    public int getId() {
        return Id;
    }

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


