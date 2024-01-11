package com.example.NewBook.demo.service;

import com.example.NewBook.demo.Entity.Books;
import com.example.NewBook.demo.dao.BooksRepository;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@Service
@AllArgsConstructor
public class ServiceBooks implements IserviceBooks {
    private BooksRepository pr;


    @Override
    public void saveBooks(Books p,MultipartFile mf)throws IOException {
        if (!mf.isEmpty())
            p.setNomImage(saveImage(mf));
        pr.save(p);
    }

    @Override
    public List<Books> getAllBooks() {
        return pr.findAll();
    }

    @Override
    public Books getBooks(Long id) {
        return pr.findById(Long.valueOf(id)).orElse(null);
    }

    @Override
    public void deleteBooks(Long id) {
        pr.deleteById(id);

    }



    @Override
    public byte[] getImage(Long id)throws IOException{
        File f=new ClassPathResource("static/photos").getFile();
        String chemin=f.getAbsolutePath();
        Path p=Paths.get(chemin,getBooks(id).getNomImage());
        return Files.readAllBytes(p);
    }


    private String saveImage(MultipartFile mf)throws IOException{
        String nomfile=mf.getOriginalFilename();
        String tab[]=nomfile.split("\\.");
        String newName=tab[0]+System.currentTimeMillis()+"."+tab[1];
        File file=new ClassPathResource("static/photos").getFile();
        String chemin=file.getAbsolutePath();
        Path p= Paths.get(chemin,newName);
        Files.write(p, mf.getBytes());
        return newName;
    }


    @Override
    public Page<Books> getBooksByMC(String mc, Pageable p) {
        return pr.findByNomContains(mc,p);
    }


    @Override
    public List<Books> getBooksBCat(Long idCat) {
        return pr.getBooksByCat(idCat);
    }





}
