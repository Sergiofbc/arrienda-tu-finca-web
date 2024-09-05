package com.example.ArriendaTuFinca.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ArriendaTuFinca.models.Pago;
import com.example.ArriendaTuFinca.models.Solicitud;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class PagoRepositoryTest {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private SolicitudRepository solicitudRepository;

    private Solicitud solicitud;

    @BeforeEach
    void setUp() {
        // Crear y guardar una entidad Solicitud
        solicitud = new Solicitud();
        solicitud.setFecha_inicio(LocalDate.of(2024, 1, 1));
        solicitud.setFecha_fin(LocalDate.of(2024, 1, 10));
        solicitud.setPrecio_por_noche(100);
        solicitud = solicitudRepository.save(solicitud);
    }

    @Test
    void testSavePago() {
        // Crear una instancia de Pago
        Pago pago = new Pago();
        pago.setSolicitud_id(solicitud);
        pago.setMonto(1000);
        pago.setFecha_pago(LocalDate.of(2024, 2, 1));

        // Guardar el pago en la base de datos
        Pago savedPago = pagoRepository.save(pago);

        // Asegurarse de que se ha guardado correctamente
        assertNotNull(savedPago);
        assertNotNull(savedPago.getPago_id());
        assertEquals(solicitud.getSolicitud_id(), savedPago.getSolicitud_id().getSolicitud_id());
        assertEquals(1000, savedPago.getMonto());
        assertEquals(LocalDate.of(2024, 2, 1), savedPago.getFecha_pago());
    }

    @Test
    void testFindById() {
        // Crear y guardar un nuevo pago
        Pago pago = new Pago();
        pago.setSolicitud_id(solicitud);
        pago.setMonto(1200);
        pago.setFecha_pago(LocalDate.of(2024, 3, 1));

        Pago savedPago = pagoRepository.save(pago);

        // Buscar el pago por ID
        Optional<Pago> foundPago = pagoRepository.findById(savedPago.getPago_id());

        // Asegurarse de que se ha encontrado correctamente
        assertTrue(foundPago.isPresent());
        assertEquals(savedPago.getPago_id(), foundPago.get().getPago_id());
    }

    @Test
    void testDeletePago() {
        // Crear y guardar un nuevo pago
        Pago pago = new Pago();
        pago.setSolicitud_id(solicitud);
        pago.setMonto(1500);
        pago.setFecha_pago(LocalDate.of(2024, 4, 1));

        Pago savedPago = pagoRepository.save(pago);

        // Eliminar el pago por ID
        pagoRepository.deleteById(savedPago.getPago_id());

        // Verificar que el pago ha sido eliminado
        Optional<Pago> deletedPago = pagoRepository.findById(savedPago.getPago_id());
        assertFalse(deletedPago.isPresent());
    }

    @Test
    void testFindAll() {
        // Crear y guardar varios pagos
        Pago pago1 = new Pago();
        pago1.setSolicitud_id(solicitud);
        pago1.setMonto(1800);
        pago1.setFecha_pago(LocalDate.of(2024, 5, 1));

        Pago pago2 = new Pago();
        pago2.setSolicitud_id(solicitud);
        pago2.setMonto(2000);
        pago2.setFecha_pago(LocalDate.of(2024, 6, 1));

        pagoRepository.save(pago1);
        pagoRepository.save(pago2);

        // Obtener todos los pagos
        List<Pago> pagos = pagoRepository.findAll();

        // Verificar que se han encontrado todos los pagos
        assertNotNull(pagos);
        assertTrue(pagos.size() >= 2);
    }
}
