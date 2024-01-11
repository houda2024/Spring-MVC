package com.example.NewBook.demo.security.repository;

import com.example.NewBook.demo.security.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<AppUser,String> {
    public AppUser findAppByUsername(String userName);

}
