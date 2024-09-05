package com.example.ArriendaTuFinca.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.mail.javamail.JavaMailSender;

import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.models.Estado;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private JavaMailSender mailSender;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAutenticarUsuario() {
        String correo = "test@example.com";
        String contrasenia = "password";
        Usuario usuario = new Usuario();
        usuario.setAutenticado(true);

        when(usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia)).thenReturn(Optional.of(usuario));
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(new UsuarioDTO());

        UsuarioDTO result = usuarioService.autenticarUsuario(correo, contrasenia);

        assertNotNull(result);
        verify(modelMapper).map(usuario, UsuarioDTO.class);
    }

    @Test
    void testAutenticarUsuarioNoAutenticado() {
        String correo = "test@example.com";
        String contrasenia = "password";
        Usuario usuario = new Usuario();
        usuario.setAutenticado(false);

        when(usuarioRepository.findByCorreoAndContrasenia(correo, contrasenia)).thenReturn(Optional.of(usuario));

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.autenticarUsuario(correo, contrasenia);
        });
    }

    @Test
    void testGetUsuarios() {
        List<Usuario> usuarios = List.of(new Usuario());
        when(usuarioRepository.findAll()).thenReturn(usuarios);
        when(modelMapper.map(any(Usuario.class), eq(UsuarioDTO.class))).thenReturn(new UsuarioDTO());

        List<UsuarioDTO> result = usuarioService.get();

        assertNotNull(result);
        assertEquals(1, result.size());
    }

    @Test
    void testObtenerUsuarioPorId() {
        Long id = 1L;
        Usuario usuario = new Usuario();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuario));
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(new UsuarioDTO());

        UsuarioDTO result = usuarioService.obtenerUsuarioPorId(id);

        assertNotNull(result);
    }

    @Test
    void testCrearUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCorreo("test@example.com");
        Usuario usuario = new Usuario();

        when(usuarioRepository.findByCorreo(usuarioDTO.getCorreo())).thenReturn(Optional.empty());
        when(modelMapper.map(usuarioDTO, Usuario.class)).thenReturn(usuario);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);
        when(modelMapper.map(usuario, UsuarioDTO.class)).thenReturn(usuarioDTO);

        UsuarioDTO result = usuarioService.crearUsuario(usuarioDTO);

        assertNotNull(result);
    }

    @Test
    void testCrearUsuarioCorreoExiste() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setCorreo("test@example.com");

        when(usuarioRepository.findByCorreo(usuarioDTO.getCorreo())).thenReturn(Optional.of(new Usuario()));

        assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.crearUsuario(usuarioDTO);
        });
    }


    @Test
    void testEliminarUsuario() {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        Usuario usuario = new Usuario();

        when(modelMapper.map(usuarioDTO, Usuario.class)).thenReturn(usuario);

        usuarioService.eliminarUsuario(usuarioDTO);

        verify(usuarioRepository).delete(usuario);
    }

    @Test
    void testEliminarUsuarioPorId() {
        Long id = 1L;

        usuarioService.eliminarUsuarioPorId(id);

        verify(usuarioRepository).deleteById(id);
    }
}
