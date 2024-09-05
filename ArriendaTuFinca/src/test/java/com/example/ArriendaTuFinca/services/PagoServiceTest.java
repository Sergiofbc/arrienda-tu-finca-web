package com.example.ArriendaTuFinca.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.example.ArriendaTuFinca.repository.PagoRepository;
import com.example.ArriendaTuFinca.repository.SolicitudRepository;
import com.example.ArriendaTuFinca.models.Pago;
import com.example.ArriendaTuFinca.models.Solicitud;
import com.example.ArriendaTuFinca.DTOs.PagoDTO;
import com.example.ArriendaTuFinca.DTOs.SolicitudDTO;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private SolicitudRepository solicitudRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PagoService pagoService;

    private Pago pago;
    private PagoDTO pagoDTO;
    private Solicitud solicitud;
    private SolicitudDTO solicitudDTO;

    @BeforeEach
    void setUp() {
        pago = new Pago();
        pago.setPago_id(1L);

        solicitud = new Solicitud();
        solicitud.setSolicitud_id(1L);

        pagoDTO = new PagoDTO();
        pagoDTO.setPago_id(1L);

        solicitudDTO = new SolicitudDTO();
        solicitudDTO.setSolicitud_id(1L);

        pagoDTO.setSolicitud_id(solicitudDTO);
    }

    @Test
    void testGet() {
        // Mocking the repository and model mapper
        when(pagoRepository.findAll()).thenReturn(Arrays.asList(pago));
        when(modelMapper.map(pago, PagoDTO.class)).thenReturn(pagoDTO);

        // Call the service method
        List<PagoDTO> result = pagoService.get();

        // Assert the result
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(pagoDTO, result.get(0));

        // Verify repository interaction
        verify(pagoRepository).findAll();
    }

    @Test
    void testObtenerPagoPorId_Exists() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.of(pago));
        when(modelMapper.map(pago, PagoDTO.class)).thenReturn(pagoDTO);

        PagoDTO result = pagoService.obtenerPagoPorId(1L);

        assertNotNull(result);
        assertEquals(pagoDTO, result);

        verify(pagoRepository).findById(1L);
    }

    @Test
    void testObtenerPagoPorId_NotExists() {
        when(pagoRepository.findById(1L)).thenReturn(Optional.empty());

        PagoDTO result = pagoService.obtenerPagoPorId(1L);

        assertNull(result);

        verify(pagoRepository).findById(1L);
    }

    void testCrearPago_SolicitudExists() {
        lenient().when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        lenient().when(modelMapper.map(pagoDTO, Pago.class)).thenReturn(pago);
        lenient().when(pagoRepository.save(pago)).thenReturn(pago);
        lenient().when(modelMapper.map(pago, PagoDTO.class)).thenReturn(pagoDTO);

        PagoDTO result = pagoService.crearPago(pagoDTO);

        assertNotNull(result);
        assertEquals(1L, result.getPago_id());

        verify(solicitudRepository).findById(1L);
        verify(pagoRepository).save(pago);
    }

    @Test
    void testCrearPago_SolicitudNotExists() {
        when(solicitudRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pagoService.crearPago(pagoDTO);
        });

        assertEquals("No se encontro la solicitud con id: 1", exception.getMessage());

        verify(solicitudRepository).findById(1L);
        verify(pagoRepository, never()).save(any(Pago.class));
    }

    @Test
    void testActualizarPago_SolicitudExists() {
        lenient().when(solicitudRepository.findById(1L)).thenReturn(Optional.of(solicitud));
        lenient().when(modelMapper.map(pagoDTO, Pago.class)).thenReturn(pago);
        lenient().when(pagoRepository.save(pago)).thenReturn(pago);
        lenient().when(modelMapper.map(pago, PagoDTO.class)).thenReturn(pagoDTO);

        PagoDTO result = pagoService.actualizarPago(pagoDTO);

        assertNotNull(result);
        assertEquals(1L, result.getPago_id());

        verify(solicitudRepository).findById(1L);
        verify(pagoRepository).save(pago);
    }

    @Test
    void testActualizarPago_SolicitudNotExists() {
        when(solicitudRepository.findById(1L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            pagoService.actualizarPago(pagoDTO);
        });

        assertEquals("No se encontro la solicitud con id: 1", exception.getMessage());

        verify(solicitudRepository).findById(1L);
        verify(pagoRepository, never()).save(any(Pago.class));
    }

    @Test
    void testEliminarPago() {
        when(modelMapper.map(pagoDTO, Pago.class)).thenReturn(pago);

        pagoService.eliminarPago(pagoDTO);

        verify(pagoRepository).delete(pago);
    }

    @Test
    void testEliminarPagoPorId() {
        pagoService.eliminarPagoPorId(1L);

        verify(pagoRepository).deleteById(1L);
    }
}
