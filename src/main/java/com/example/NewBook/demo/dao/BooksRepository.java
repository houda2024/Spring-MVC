package com.example.NewBook.demo.dao;

import com.example.NewBook.demo.Entity.Books;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BooksRepository extends JpaRepository<Books ,Long> {
    public Page<Books> findByNomContains(String mc , Pageable p );
    @Query("select  p from  Books p where p.themes.id=:x")
    public List<Books> getBooksByCat(@Param("x") Long idC);
}
