package com.example.NewBook.demo.security.service;

import com.example.NewBook.demo.security.entities.AppUser;

public interface IAccountService {
    public void addRole(String nom);


   public void addUser(String username, String password, String mail);

    public void addRoleToUser(String username, Long nameRole);

    public void deleteRoleToUser(String username, Long role);
    public AppUser loadUserByUserName(String username);

}
