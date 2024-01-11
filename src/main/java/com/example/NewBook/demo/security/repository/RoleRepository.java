package com.example.NewBook.demo.security.repository;

import com.example.NewBook.demo.security.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<AppRole,Long> {



}
