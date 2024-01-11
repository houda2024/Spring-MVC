package com.example.NewBook.demo.Controller;

import com.example.NewBook.demo.Entity.Books;
import com.example.NewBook.demo.dao.ThemesRepository;
import com.example.NewBook.demo.service.IserviceBooks;
import com.example.NewBook.demo.service.IserviceThemes;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class BooksControleur {
    private IserviceBooks serviceBooks;
    private IserviceThemes serviceThemes;
    private ThemesRepository themesRepository;
    @GetMapping(path = {"/user/index"})
    public String getAllBooks(Model m, @RequestParam(name = "page" , defaultValue = "1") int page,
                           @RequestParam(name = "size", defaultValue = "5") int size,
                           @RequestParam(name = "mc" , defaultValue = "")String mc)
    {
        Page<Books> ListePage=serviceBooks.getBooksByMC(mc, PageRequest.of(page-1,size));
        m.addAttribute("" +
                "", ListePage.getContent());
        m.addAttribute("pages", new int[ListePage.getTotalPages()]);
        m.addAttribute("current", new int[ListePage.getTotalPages()]);
        m.addAttribute("mc",mc);
        return "home";
    }
    @GetMapping("/admin/delete/{id}")
    public String deleteBooks(@PathVariable ("id") Long idBook){
        serviceBooks.deleteBooks(idBook);
        return "redirect:/user/index";
    }
    @GetMapping("/admin/update/{id}")
    public String editBooks(@PathVariable Long id , Model m){
        Books p=serviceBooks.getBooks(id);
        m.addAttribute("books",p);
        m.addAttribute("themes", themesRepository.findAll());
        return "add";
    }
    @PostMapping("/admin/save")
    public String saveBooks(@ModelAttribute Books p, @RequestParam("file")MultipartFile mf) throws IOException {
        serviceBooks.saveBooks(p,mf);
        return "redirect:/user/index";

    }
    @GetMapping("/admin/formBook")
    public String formAjout(Model m){
        m.addAttribute("themes",themesRepository.findAll());
        m.addAttribute("books",new Books());
        return "add";
    }
    @GetMapping("/")
    public String home(){return "redirect:/user/index";}
}
