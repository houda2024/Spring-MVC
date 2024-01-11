package com.example.NewBook.demo.dao;

import com.example.NewBook.demo.Entity.Themes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemesRepository extends JpaRepository<Themes ,Long> {
}
