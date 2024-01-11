package com.example.NewBook.demo.restcontroleur;

import com.example.NewBook.demo.Entity.Books;
import com.example.NewBook.demo.service.IserviceBooks;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*")
@RequestMapping("/restapiBook")
public class BookRestController {
    private final IserviceBooks serviceBooks;

    @GetMapping("/all")
    public List<Books> getAllBooks(@RequestParam(name = "mc", defaultValue = "") String mc,
                                   @RequestParam(name = "page", defaultValue = "0") int page,
                                   @RequestParam(name = "size", defaultValue = "5") int size) {
        Page<Books> bookPage = serviceBooks.getBooksByMC(mc, PageRequest.of(page, size));
        return bookPage.getContent();
    }

    @DeleteMapping("/remove/{id}")
    public void deleteBook(@PathVariable("id") Long id) {
        serviceBooks.deleteBooks(id);
    }

    @PostMapping("/save")
    public void addBook(@RequestParam("book") String book, @RequestParam("file") MultipartFile mf) throws IOException {
        Books p = new ObjectMapper().readValue(book, Books.class);
        serviceBooks.saveBooks(p, mf);
    }

    @GetMapping(path = "/image/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@PathVariable Long id) throws IOException {
        return serviceBooks.getImage(id);
    }










}
