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
import com.example.ArriendaTuFinca.models.Estado;
import com.example.ArriendaTuFinca.models.Propiedad;
import com.example.ArriendaTuFinca.models.Usuario;
import com.example.ArriendaTuFinca.DTOs.PropiedadDTO;
import com.example.ArriendaTuFinca.DTOs.UsuarioDTO;


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
        List<PropiedadDTO> propiedadesDTO = propiedades.stream() 
                                       .map(propiedad -> modelMapper.map(propiedad, PropiedadDTO.class))
                                       .collect(Collectors.toList());   
        return propiedadesDTO;
    }

    //get por id
    public PropiedadDTO obtenerPropiedadPorId(Long id) {
        Optional<Propiedad> propiedad = propiedadRepository.findById(id); //Optional es un contenedor que puede o no contener un valor no nulo
        PropiedadDTO propiedadDTO = null;
        if (propiedad.isPresent()) {
            propiedadDTO = modelMapper.map(propiedad.get(), PropiedadDTO.class);
        }
        return propiedadDTO;
    }

    //post
    //Aqui esta el error????  <---------------------
    public PropiedadDTO crearPropiedad(PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setEstado(Estado.ACTIVE);
        Optional<Usuario> usuariOptional = usuarioRepository.findById(propiedadDTO.getArrendadorId());
        if (usuariOptional.isPresent()) {
            propiedad.setArrendador(usuariOptional.get());
            propiedad = propiedadRepository.save(propiedad);
            propiedadDTO.setPropiedadId(propiedad.getPropiedadId());
            return propiedadDTO;
        }
        return null;   
    }    

    //put
    public PropiedadDTO actualizarPropiedad(Long id, PropiedadDTO propiedadDTO) {
        Propiedad propiedad = modelMapper.map(propiedadDTO, Propiedad.class);
        propiedad.setEstado(Estado.ACTIVE);
        propiedad.setPropiedadId(id);
        propiedadDTO = modelMapper.map(propiedadRepository.save(propiedad), PropiedadDTO.class);
        return propiedadDTO;
    }

    //delete por id
    public void eliminarPropiedad(Long id) {
        propiedadRepository.deleteById(id);
    }
}

