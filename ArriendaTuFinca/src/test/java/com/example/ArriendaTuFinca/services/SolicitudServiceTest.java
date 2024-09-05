package com.example.ArriendaTuFinca.services;

import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.DTOs.SolicitudDTO;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.repository.SolicitudRepository;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class SolicitudServiceTest {

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PropiedadRepository propiedadRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private SolicitudService solicitudService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetSolicitudes() {
        List<Solicitud> solicitudes = List.of(new Solicitud());
        List<SolicitudDTO> solicitudesDTO = List.of(new SolicitudDTO());

        when(solicitudRepository.findAll()).thenReturn(solicitudes);
        when(modelMapper.map(any(Solicitud.class), eq(SolicitudDTO.class))).thenReturn(solicitudesDTO.get(0));

        List<SolicitudDTO> resultado = solicitudService.get();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(solicitudRepository, times(1)).findAll();
    }

    @Test
    void testObtenerSolicitudPorId() {
        Long id = 1L;
        Solicitud solicitud = new Solicitud();
        SolicitudDTO solicitudDTO = new SolicitudDTO();

        when(solicitudRepository.findById(id)).thenReturn(Optional.of(solicitud));
        when(modelMapper.map(solicitud, SolicitudDTO.class)).thenReturn(solicitudDTO);

        SolicitudDTO resultado = solicitudService.obtenerSolicitudPorId(id);

        assertNotNull(resultado);
        verify(solicitudRepository, times(1)).findById(id);
    }

    @Test
    void testCrearSolicitudExito() {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        solicitudDTO.setSolicitud_id(1L);

        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuario_id(1L);
        solicitudDTO.setArrendatario_id(usuarioDTO);

        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setPropiedad_id(1L);
        solicitudDTO.setPropiedad_id(propiedadDTO);

        Usuario usuario = new Usuario();
        Propiedad propiedad = new Propiedad();
        Solicitud solicitud = new Solicitud();

        when(usuarioRepository.findById(usuarioDTO.getUsuario_id())).thenReturn(Optional.of(usuario));
        when(propiedadRepository.findById(propiedadDTO.getPropiedad_id())).thenReturn(Optional.of(propiedad));
        when(modelMapper.map(solicitudDTO, Solicitud.class)).thenReturn(solicitud);
        when(solicitudRepository.save(any(Solicitud.class))).thenReturn(solicitud);

        SolicitudDTO resultado = solicitudService.crearSolicitud(solicitudDTO);

        assertNotNull(resultado);
        verify(solicitudRepository, times(1)).save(solicitud);
    }

    @Test
    void testCrearSolicitudError() {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setUsuario_id(1L);
        solicitudDTO.setArrendatario_id(usuarioDTO);

        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setPropiedad_id(1L);
        solicitudDTO.setPropiedad_id(propiedadDTO);

        when(usuarioRepository.findById(usuarioDTO.getUsuario_id())).thenReturn(Optional.empty());
        when(propiedadRepository.findById(propiedadDTO.getPropiedad_id())).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            solicitudService.crearSolicitud(solicitudDTO);
        });

        verify(solicitudRepository, never()).save(any(Solicitud.class));
    }

    @Test
    void testActualizarSolicitud() {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        Solicitud solicitud = new Solicitud();

        when(modelMapper.map(solicitudDTO, Solicitud.class)).thenReturn(solicitud);
        when(solicitudRepository.save(solicitud)).thenReturn(solicitud);
        when(modelMapper.map(solicitud, SolicitudDTO.class)).thenReturn(solicitudDTO);

        SolicitudDTO resultado = solicitudService.actualizarSolicitud(solicitudDTO);

        assertNotNull(resultado);
        verify(solicitudRepository, times(1)).save(solicitud);
    }

    @Test
    void testEliminarSolicitud() {
        SolicitudDTO solicitudDTO = new SolicitudDTO();
        Solicitud solicitud = new Solicitud();

        when(modelMapper.map(solicitudDTO, Solicitud.class)).thenReturn(solicitud);

        solicitudService.eliminarSolicitud(solicitudDTO);

        verify(solicitudRepository, times(1)).delete(solicitud);
    }

    @Test
    void testEliminarSolicitudPorId() {
        Long id = 1L;

        solicitudService.eliminarSolicitudPorId(id);

        verify(solicitudRepository, times(1)).deleteById(id);
    }
}
