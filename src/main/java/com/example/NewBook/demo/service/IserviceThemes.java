package com.example.NewBook.demo.service;

import com.example.NewBook.demo.Entity.Themes;

import java.util.List;

public interface IserviceThemes {
    public void  addThemes(Themes c);
    public List<Themes> getAllThemes();
}
