package com.example.ArriendaTuFinca.models;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Propiedad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long propiedadId;

    @ManyToOne
    @JoinColumn(name = "arrendadorId", referencedColumnName = "idUsuario", unique = false, nullable = false)
    private Usuario arrendador;

    private String imagen;
    private String nombre;
    private String departamento;
    private String municipio;
    private String tipoDeIngreso;
    private String descripcion;
    private int cantBanos;
    private int cantHabitaciones;
    private boolean mascotas;
    private boolean piscina;
    private boolean asador;
    private int valorNoche;
    private boolean visible;
    private int calificacion;
}
