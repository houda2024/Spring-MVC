package com.example.NewBook.demo;

import com.example.NewBook.demo.Entity.Books;
import com.example.NewBook.demo.Entity.Themes;
import com.example.NewBook.demo.dao.BooksRepository;
import com.example.NewBook.demo.dao.ThemesRepository;
import com.example.NewBook.demo.security.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
@AllArgsConstructor

public class DemoApplication implements CommandLineRunner {
	 @Autowired
	 private BooksRepository booksRepository;
	 @Autowired
	private ThemesRepository themesRepository;


	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		themesRepository.save(new Themes(9L,"Thrille",null));
		themesRepository.save(Themes.builder().nom("Horror").build());
		themesRepository.save(Themes.builder().nom("Romance").build());
		themesRepository.save(Themes.builder().nom("Science Fiction").build());
		booksRepository.save(new Books(1, "if He", 50, 100, "BOOK1_image_url", themesRepository.findById(1L).get()));
		booksRepository.save(new Books(2, "A life", 60, 50, "BOOK2_image_url", themesRepository.findById(1L).get()));
		booksRepository.save(new Books(3, "Verity", 70, 35, "BOOK3_image_url", themesRepository.findById(1L).get()));
		booksRepository.save(new Books(4, "The Patient", 80, 200, "BOOK4_image_url", themesRepository.findById(1L).get()));

	}
	@Bean
	PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner commandLineRunner(IAccountService accountService){
		return args -> {
			//accountService.addRole("USER");
			//accountService.addRole("ADMIN");

			//accountService.addUser("user", "123", "user@gmail.com");
			//accountService.addUser("admin", "123", "admin@gmail.com");
			accountService.addRoleToUser("user", 1L);
			accountService.addRoleToUser("admin", 1L);
			accountService.addRoleToUser("admin", 2L);

		};
	}
}
