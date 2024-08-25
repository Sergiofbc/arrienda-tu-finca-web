package com.example.ArriendaTuFinca.services;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.internal.util.collections.ConcurrentReferenceHashMap.Option;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ArriendaTuFinca.repository.PropiedadRepository;
import com.example.ArriendaTuFinca.repository.UsuarioRepository;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;


@Service
public class PropiedadService {

    @Autowired
    private PropiedadRepository propiedadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModelMapper modelMapper;

    //se van a retornar DTOs

    //get
    public List<PropiedadDTO> get() {
        List<Propiedad> propiedades = propiedadRepository.findAll();
        return Arrays.stream(modelMapper.map(propiedades, PropiedadDTO[].class))
               .collect(Collectors.toList());
    }

    //get por id
    public PropiedadDTO obtenerPropiedadPorId(Long id) {
        Propiedad propiedad = propiedadRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }

    //post
    //revisar bien esto  <---------------------
    public PropiedadDTO crearPropiedad(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        Optional<Usuario> arrendador = usuarioRepository.findById(propiedadDTO.getArrendadorId());
        if (arrendador.isPresent()) {
            propiedad.setArrendador(arrendador.get());
            propiedad = propiedadRepository.save(propiedad);
            propiedadDTO.setPropiedadId(propiedad.getPropiedadId());
            return propiedadDTO;
        }
        return null;   
    }

    //put
    public PropiedadDTO actualizarPropiedad(Long id, PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setPropiedadId(id);
        Propiedad propiedadActualizada = propiedadRepository.save(propiedad);
        return modelMapper.map(propiedadActualizada, PropiedadDTO.class);
    }

    //delete
    public PropiedadDTO eliminarPropiedad(Long id) {
        Propiedad propiedad = propiedadRepository.findById(id)
                          .orElseThrow(() -> new RuntimeException("Propiedad no encontrada"));
        propiedadRepository.deleteById(id);
        return modelMapper.map(propiedad, PropiedadDTO.class);
    }
}

