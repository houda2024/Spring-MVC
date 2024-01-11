package com.example.NewBook.demo.security.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Add this line to generate the ID automatically
    private Long id; // Change the type to Long for the ID

    private String role;



}
