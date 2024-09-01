package com.example.ArriendaTuFinca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewsController {

    @GetMapping("/login")
    public String login() {
        return "login"; // El nombre del archivo HTML sin la extensión
    }

    @GetMapping("/crear-usuario")
    public String crearUsuario() {
        return "crear-usuario"; // El nombre del archivo HTML sin la extensión
    }

    @GetMapping("/")
    public String index() {
        return "index"; // El nombre del archivo HTML sin la extensión
    }
}
