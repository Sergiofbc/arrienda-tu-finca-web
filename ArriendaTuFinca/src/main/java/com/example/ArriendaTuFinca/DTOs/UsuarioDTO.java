package com.example.ArriendaTuFinca.DTOs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long idUsuario;
    private String nombre;
    private String correo;
    private String telefono;
    private String contrasenia;
    private String estado;
    private String rol;

    private List<Long> propiedadesIds;
}
