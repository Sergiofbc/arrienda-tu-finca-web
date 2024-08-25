package com.example.ArriendaTuFinca.services;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    //se van a retornar DTOs

    //get
    public List<UsuarioDTO> get() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return Arrays.stream(modelMapper.map(usuarios, UsuarioDTO[].class))
               .collect(Collectors.toList());
    }

    //get por id
    public UsuarioDTO obtenerUsuarioPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

    //post
    public UsuarioDTO crearUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        Usuario nuevoUsuario = usuarioRepository.save(usuario);
        return modelMapper.map(nuevoUsuario, UsuarioDTO.class);
    }

    //put
    public UsuarioDTO actualizarUsuario(Long id, UsuarioDTO usuarioDTO) {
        Usuario usuario = modelMapper.map(usuarioDTO, Usuario.class);
        usuario.setIdUsuario(id);
        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return modelMapper.map(usuarioActualizado, UsuarioDTO.class);
    }

    //delete
    public UsuarioDTO eliminarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
        return modelMapper.map(usuario, UsuarioDTO.class);
    }

}
