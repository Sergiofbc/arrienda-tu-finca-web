package com.example.ArriendaTuFinca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.MediaType;

import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.services.UsuarioService;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;


@RestController
@RequestMapping( value = "/api/usuarios")
public class UsuarioController {

    // Inyección de dependencias
    @Autowired
    private UsuarioService usuarioService;

    // CRUD Endpoints

    // Read
    @GetMapping( value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO obtenerUsuarioPorId(@PathVariable Long id) {
        return usuarioService.obtenerUsuarioPorId(id);
    }

    // read all
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UsuarioDTO> get() {
        return usuarioService.get();
    }

    // Create
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO crearUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.crearUsuario(usuarioDTO);
    }

    // Update
    @PutMapping(value = "/{id}")
    public UsuarioDTO actualizarUsuario(@PathVariable Long id, @RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.actualizarUsuario(id, usuarioDTO);
    }

    // Delete
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UsuarioDTO eliminarUsuario(@PathVariable Long id) {
        return usuarioService.eliminarUsuario(id);
    }
}
