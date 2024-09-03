package com.example.ArriendaTuFinca.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.models.Estado;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    // Método para autenticar un usuario por correo y contraseña
    public UsuarioDTO autenticarUsuario(String correo, String contrasenia) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia);
        if (usuarioOptional.isPresent()) {
            return modelMapper.map(usuarioOptional.get(), UsuarioDTO.class);
        } else {
            return null; // Retornar null si no existe un usuario con esas credenciales
        }
    }

    //get
    public List<UsuarioDTO> get() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        List<UsuarioDTO> usuariosDTO = usuarios.stream() //convertir lista de usuarios a lista de usuariosDTO
                                       .map(usuario -> modelMapper.map(usuario, UsuarioDTO.class))
                                       .collect(Collectors.toList());
        return usuariosDTO;
    }

    //get por id
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id); //Optional es un contenedor que puede o no contener un valor no nulo
        UsuarioDTO usuarioDTO = null;
        if (usuario.isPresent()) {
            usuarioDTO = modelMapper.map(usuario.get(), UsuarioDTO.class);
        }
        return usuarioDTO;
    }

    //post
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setEstado(Estado.ACTIVE); // Establecer estado activo

        try {
            usuario = usuarioRepository.save(usuario); // Guardar en la base de datos
            return modelMapper.map(usuario, UsuarioDTO.class); // Mapear de vuelta a DTO
        } catch (Exception e) {
            // Log del error para seguimiento
            throw new RuntimeException("Error al guardar el usuario en la base de datos", e);
        }
    }



    //put
    public UsuarioDTO actualizarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setEstado(Estado.ACTIVE);
        usuario = usuarioRepository.save(usuario);
        usuarioDTO = modelMapper.map(usuario, UsuarioDTO.class);
        return usuarioDTO;
    }

    //delete
    public void eliminarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuarioRepository.delete(usuario);
    }

    //delete por id
    public void eliminarUsuarioPorId(Long id) {
        usuarioRepository.deleteById(id);
    }

}
