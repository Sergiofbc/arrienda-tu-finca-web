package com.example.ArriendaTuFinca.DTOs;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {
    private Long usuario_id;
    private String nombre;
    private String correo;
    private String telefono;
    private String contrasenia;
    private String estado;

    // Puede ser un solo rol o una lista dependiendo de los requisitos
    private String rol; // O puede ser List<String> si permites seleccionar múltiples roles

    private String confirmarContrasenia; // Campo de confirmación de contraseña
}

