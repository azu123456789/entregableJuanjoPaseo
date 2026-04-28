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

    public Reserva guardarReservaEnBD(Reserva datos){

        //validar que la fecha no este vacida o en blanco

        if (datos.getFecha()==null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La fecha no puede estar vacia"
            );
        }
        //validar que el tiempo sea mayor a 0
        if (datos.getTiempo()<=0){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El tiempo debe ser mayor a 0"
            );
        }


        return this.repositorioReserva.save(datos);

        //validar que datos me envian y si estos cumplen las reglas de negocio 
        //guardar los datos en BD con ayuda del repositorio 
    }

    public Reserva modificarReservaEnBD (Reserva datos, UUID id){
         Optional<Reserva> reservaBuscada = this.repositorioReserva.findById(id);
        if (reservaBuscada.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "La reserva no existe, intente de nuevo" 
            );
        }
        Reserva reservaEncontrada = reservaBuscada.get();

            
        //2. Validar la informacion nueva que me mando el cliente

        if (datos.getFecha()==null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "revise la fecha ingresada"
            );
        }
        
        //3 ejecutar el nuevo guardado y retornar 
        reservaEncontrada.setFecha(datos.getFecha());
        return this.repositorioReserva.save(reservaEncontrada);
        //validar que los datos me envian y si estos cumplen las reglas de negocio 
        //modificar los datos BD con ayuda de un repositorio
        

    }
    }

    public boolean eliminarReservaEnBD(UUID id){
        return false; 
        // buscar y validar si el ID que me envian existe 
        //eliminar el registro en BD 
            Optional<Reserva> reservaBuscada = this.repositorioReserva.findById(id);
        if (reservaBuscada.isEmpty()){
            throw new ResponseStatusException(  
                HttpStatus.NOT_FOUND,
                "La reserva no existe, intente de nuevo" 
            ); 
            Reserva reservaEncontrada = reservaBuscada.get();
            this.repositorioReserva.deleteById(id);
            return true;

    }

    public List<Reserva> buscarReservaEnBD(){

        List<Reserva> reservasEncontradas = this.repositorioReserva.findAll();
        return reservasEncontradas;
        //Dependiendo del parametro de busqueda debo implementar validaciones 
        //devuelvo los usuarios o usuario que encuentre en BD 
    }

}
