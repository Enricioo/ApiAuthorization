package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.UnauthorizedException;
import com.example.demo.model.Token;
import com.example.demo.model.User;
import com.example.demo.service.TokenService;
import com.example.demo.service.UserService;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    // Endpoint per ottenere i dati dell'utente loggato
    @GetMapping("/me")
    public User getUserData(@RequestHeader("Authorization") String token) {
    	System.out.println("token:" + token);
    	 // Trova il token nel database
        Token authToken = tokenService.findByToken(token);
        
        // Se il token è valido, restituisce i dati dell'utente
        if (authToken != null) {
            return userService.findById(authToken.getUserId()).orElseThrow(() -> new UnauthorizedException());
        } else {
        	// Se il token non è valido, lancia un'eccezione di non autorizzato
            throw new UnauthorizedException();
        }
    }

    // Endpoint riservato all'admin per ottenere la lista di tutti gli utenti
    @GetMapping("/users")
    public List<User> getAllUsers(@RequestHeader("Authorization") String token) {
    	   // Trova il token nel database
    	Token authToken = tokenService.findByToken(token);
    	  // Se il token è valido, verifica il ruolo dell'utente
        if (authToken != null) {
            User user = userService.findById(authToken.getUserId()).orElseThrow(() -> new UnauthorizedException());
            // Se l'utente ha il ruolo di admin, restituisce la lista di tutti gli utenti
            if ("ADMIN".equals(user.getRole())) {
                return userService.findAll();
            } else {
            	   // Se l'utente non è admin, lancia un'eccezione di non autorizzato
                throw new UnauthorizedException();
            }
        } else {
        	 // Se il token non è valido, lancia un'eccezione di non autorizzato
            throw new UnauthorizedException();
        }
    }
}
