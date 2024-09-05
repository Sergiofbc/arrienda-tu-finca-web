package com.example.ArriendaTuFinca.services;

import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;
import com.example.ArriendaTuFinca.models.Estado;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.PropiedadRepository;
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

class PropiedadServiceTest {

    @Mock
    private PropiedadRepository propiedadRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PropiedadService propiedadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetPropiedades() {
        List<Propiedad> propiedades = List.of(new Propiedad());
        List<PropiedadDTO> propiedadesDTO = List.of(new PropiedadDTO());

        when(propiedadRepository.findAll()).thenReturn(propiedades);
        when(modelMapper.map(any(Propiedad.class), eq(PropiedadDTO.class))).thenReturn(propiedadesDTO.get(0));

        List<PropiedadDTO> resultado = propiedadService.get();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(propiedadRepository, times(1)).findAll();
    }

    @Test
    void testObtenerPropiedadPorId() {
        Long id = 1L;
        Propiedad propiedad = new Propiedad();
        PropiedadDTO propiedadDTO = new PropiedadDTO();

        when(propiedadRepository.findById(id)).thenReturn(Optional.of(propiedad));
        when(modelMapper.map(propiedad, PropiedadDTO.class)).thenReturn(propiedadDTO);

        PropiedadDTO resultado = propiedadService.obtenerPropiedadPorId(id);

        assertNotNull(resultado);
        verify(propiedadRepository, times(1)).findById(id);
    }

    @Test
    void testCrearPropiedadExito() {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        propiedadDTO.setPropiedad_id(1L);

        UsuarioDTO arrendadorDTO = new UsuarioDTO();
        arrendadorDTO.setUsuario_id(1L);
        propiedadDTO.setArrendador_id(arrendadorDTO);

        Usuario arrendador = new Usuario();
        Propiedad propiedad = new Propiedad();

        when(usuarioRepository.findById(arrendadorDTO.getUsuario_id())).thenReturn(Optional.of(arrendador));
        when(modelMapper.map(propiedadDTO, Propiedad.class)).thenReturn(propiedad);
        when(propiedadRepository.save(any(Propiedad.class))).thenReturn(propiedad);

        PropiedadDTO resultado = propiedadService.crearPropiedad(propiedadDTO);

        assertNotNull(resultado);
        verify(propiedadRepository, times(1)).save(propiedad);
    }

    @Test
    void testCrearPropiedadErrorArrendadorNoExiste() {
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        UsuarioDTO arrendadorDTO = new UsuarioDTO();
        arrendadorDTO.setUsuario_id(1L);
        propiedadDTO.setArrendador_id(arrendadorDTO);

        when(usuarioRepository.findById(arrendadorDTO.getUsuario_id())).thenReturn(Optional.empty());

        assertThrows(IllegalArgumentException.class, () -> {
            propiedadService.crearPropiedad(propiedadDTO);
        });

        verify(propiedadRepository, never()).save(any(Propiedad.class));
    }

    @Test
    void testActualizarPropiedad() {
        Long id = 1L;
        PropiedadDTO propiedadDTO = new PropiedadDTO();
        Propiedad propiedad = new Propiedad();

        when(modelMapper.map(propiedadDTO, Propiedad.class)).thenReturn(propiedad);
        when(propiedadRepository.save(any(Propiedad.class))).thenReturn(propiedad);
        when(modelMapper.map(propiedad, PropiedadDTO.class)).thenReturn(propiedadDTO);

        PropiedadDTO resultado = propiedadService.actualizarPropiedad(id, propiedadDTO);

        assertNotNull(resultado);
        verify(propiedadRepository, times(1)).save(propiedad);
    }

    @Test
    void testEliminarPropiedad() {
        Long id = 1L;

        propiedadService.eliminarPropiedad(id);

        verify(propiedadRepository, times(1)).deleteById(id);
    }
}