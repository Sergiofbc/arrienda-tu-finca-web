package com.example.ArriendaTuFinca.DTOs;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Getter
@Setter
public class UsuarioDTO {
    private Long usuario_id;
    private String nombre;
    private String correo;
    private String telefono;
    private String contrasenia;
    private String estado;
    private String rol;

    //tengo que mejorar el model mapper pa poder usar esta chimbada??
    /* 
    @JsonIgnore  // Ignora este campo durante la serialización para evitar ciclos
    private List<Propiedad> propiedades;

    @JsonIgnore  
    private List<SolicitudDTO> solicitudes;
    */
}
