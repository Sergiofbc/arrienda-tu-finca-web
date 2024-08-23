package com.example.ArriendaTuFinca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.ArriendaTuFinca.models.Propiedad;


@Repository
public interface PropiedadRepository extends JpaRepository<Propiedad, Long> {
    // Métodos personalizados si es necesario
}