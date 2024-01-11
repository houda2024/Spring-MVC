package com.example.NewBook.demo.service;

import com.example.NewBook.demo.Entity.Themes;
import com.example.NewBook.demo.dao.ThemesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceThemes implements IserviceThemes {
    private ThemesRepository themesRepository;
    @Override
    public void addThemes(Themes c){themesRepository.save(c);}

    @Override
    public List<Themes> getAllThemes() {
        return themesRepository.findAll();
    }

}
