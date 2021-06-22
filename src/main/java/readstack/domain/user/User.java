package readstack.domain.user;

import readstack.domain.api.DiscoveryBasicInfo;

import java.time.LocalDateTime;
import java.util.List;

public class User {
    private Integer id;
    private String username;
    private String email;
    private String password;
    private LocalDateTime registrationDate;
    private List<DiscoveryBasicInfo> favList;

    public List<DiscoveryBasicInfo> getFavList() {
        return favList;
    }

    //by dodac do ulub
    private List<DiscoveryBasicInfo> favDisc;

    public List<DiscoveryBasicInfo> getFavDisc() {
        return favDisc;
    }
//



    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User(Integer id, String username, String email, String password, LocalDateTime registrationDate) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }

    public User(String username, String email, String password, LocalDateTime registrationDate) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.registrationDate = registrationDate;
    }
}//W konstruktorze pominąłem pole id, ponieważ będzie ono generowane automatycznie po stronie bazy danych.
