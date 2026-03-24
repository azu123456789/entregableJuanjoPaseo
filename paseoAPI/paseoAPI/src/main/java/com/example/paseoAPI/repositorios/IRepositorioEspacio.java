package com.example.paseoAPI.repositorios;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.paseoAPI.models.Espacio;

@Repository
public interface IRepositorioEspacio extends JpaRepository<Espacio, UUID>{

}
