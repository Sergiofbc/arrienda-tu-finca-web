package com.example.ArriendaTuFinca.services;
import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;

@Service
public class PropiedadService {

    @Autowired
    private PropiedadRepository propiedadRepository;

    public List<Propiedad> listarPropiedades() {
        return propiedadRepository.findAll();
    }

    public Optional<Propiedad> obtenerPropiedadPorId(Long id) {
        return propiedadRepository.findById(id);
    }

    public Propiedad guardarPropiedad(Propiedad propiedad) {
        return propiedadRepository.save(propiedad);
    }

    public void eliminarPropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }

    
    
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Propiedad crearPropiedad(Propiedad propiedad, Long arrendadorId) {
        // Obtén el arrendador (usuario) por su ID
        Usuario arrendador = usuarioRepository.findById(arrendadorId)
                              .orElseThrow(() -> new RuntimeException("Arrendador no encontrado"));

        // Asigna el arrendador a la propiedad
        propiedad.setArrendador(arrendador);

        // Guarda la propiedad en la base de datos
        return propiedadRepository.save(propiedad);
    }

    /*
    private UsuarioRepository usuarioRepository;

    public Propiedad savePropiedad(Propiedad propiedad, Long arrendadorId) {
        Usuario arrendador = usuarioRepository.findById(arrendadorId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        propiedad.setArrendador(arrendador);
        return propiedadRepository.save(propiedad);
    }*/
}

