package com.example.ArriendaTuFinca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.services.UsuarioService;


@Controller
@RequestMapping( value = "/api/usuarios")
public class UsuarioController {

    // Inyección de dependencias
    @Autowired
    private UsuarioService usuarioService;

    // CRUD Endpoints
    @CrossOrigin
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsuarioDTO> get() {
        return usuarioService.get();
    }

    // Endpoint para manejar el login
    @CrossOrigin
    @PostMapping("/login")
    public String login(@RequestParam String correo, @RequestParam String contrasenia, Model model) {
        UsuarioDTO usuarioAutenticado = usuarioService.autenticarUsuario(correo, contrasenia);
        if (usuarioAutenticado != null) {
            // Usuario autenticado correctamente
            model.addAttribute("usuario", usuarioAutenticado);
            return "redirect:/navegar"; // Redirige a la página navegar.html
        } else {
            // Autenticación fallida
            model.addAttribute("error", "Correo o contraseña incorrectos");
            return "login"; // Vuelve a la página de login con un mensaje de error
        }
    }


    // Validar
    @CrossOrigin
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // Create
    @CrossOrigin
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.crearUsuario(usuarioDTO);
    }

    // UpdateCLARO NO FUNCIONA PORQUE NO HACE NADA CON EL ID QUE LLEGA
    @CrossOrigin
    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO actualizarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.actualizarUsuario(usuarioDTO);
    }

    // Delete
    @CrossOrigin
    @DeleteMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioService.eliminarUsuario(usuarioDTO);
    }

    // Delete by id
    @CrossOrigin
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarUsuario(@PathVariable Long id) {
        usuarioService.eliminarUsuarioPorId(id);
    }
}
