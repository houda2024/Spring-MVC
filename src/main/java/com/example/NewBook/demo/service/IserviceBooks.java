package com.example.NewBook.demo.service;

import com.example.NewBook.demo.Entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IserviceBooks {
    public  void saveBooks(Books p, MultipartFile mf) throws IOException;
    public List<Books> getAllBooks();
    public Books getBooks(Long id);
    public void deleteBooks(Long id);
    public  List<Books> getBooksBCat(Long idCat);

     public  Page<Books> getBooksByMC(String mc, Pageable p);

    public byte[] getImage(Long id) throws IOException;

}
