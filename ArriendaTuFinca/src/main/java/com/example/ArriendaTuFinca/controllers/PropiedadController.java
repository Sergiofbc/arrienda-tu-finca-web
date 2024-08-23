package com.example.ArriendaTuFinca.controllers;

import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.services.PropiedadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/propiedades")
public class PropiedadController {

    @Autowired
    private PropiedadService propiedadService;

    @GetMapping
    public List<Propiedad> listarPropiedades() {
        return propiedadService.listarPropiedades();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Propiedad> obtenerPropiedadPorId(@PathVariable Long id) {
        Optional<Propiedad> propiedad = propiedadService.obtenerPropiedadPorId(id);
        return propiedad.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Propiedad> crearPropiedad(@RequestBody Propiedad propiedad, @RequestParam Long arrendadorId) {
        Propiedad nuevaPropiedad = propiedadService.crearPropiedad(propiedad, arrendadorId);
        return new ResponseEntity<>(nuevaPropiedad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Propiedad> actualizarPropiedad(@PathVariable Long id, @RequestBody Propiedad propiedad) {
        Optional<Propiedad> propiedadExistente = propiedadService.obtenerPropiedadPorId(id);
        if (propiedadExistente.isPresent()) {
            propiedad.setPropiedadId(id);
            return ResponseEntity.ok(propiedadService.guardarPropiedad(propiedad));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPropiedad(@PathVariable Long id) {
        if (propiedadService.obtenerPropiedadPorId(id).isPresent()) {
            propiedadService.eliminarPropiedad(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}