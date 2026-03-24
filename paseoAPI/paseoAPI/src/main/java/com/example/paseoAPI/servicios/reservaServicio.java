package com.example.paseoAPI.servicios;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paseoAPI.models.Reserva;
import com.example.paseoAPI.repositorios.IRepositorioReserva;

@Service
public class reservaServicio {

    @Autowired
    private IRepositorioReserva repositorioReserva;

    public boolean guardarReservaEnBD(Reserva datos){
        return false;
        //validar que datos me envian y si estos cumplen las reglas de negocio 
        //guardar los datos en BD con ayuda del repositorio 
    }

    public boolean modificarReservaEnBD (Reserva datos, UUID id){
        return false; 
        //validar que los datos me envian y si estos cumplen las reglas de negocio 
        //modificar los datos BD con ayuda de un repositorio
    }

    public boolean eliminarReservaEnBD(UUID id){
        return false; 
        // buscar y validar si el ID que me envian existe 
        //eliminar el registro en BD 
    }

    public boolean buscarReservaEnBD(){
        return false; 
        //Dependiendo del parametro de busqueda debo implementar validaciones 
        //devuelvo los usuarios o usuario que encuentre en BD 
    }

}
