package com.example.paseoAPI.servicios;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.paseoAPI.models.Espacio;
import com.example.paseoAPI.repositorios.IRepositorioEspacio;


@Service
public class espacioServicio {

@Autowired
private IRepositorioEspacio repositorioEspacio;

    public Espacio guardarEspacioEnBD(Espacio datos){
         //validar que el nombre del espacio no este vacio o en blanco
        if (datos.getNombre().isEmpty()||datos.getNombre().isBlank()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El nombre del espacio no puede estar vacio"
            );
        }
        //validar que la descripcion no este vacia o en blanco
        if (datos.getDescripcion().isEmpty()||datos.getDescripcion().isBlank()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La descripcion del espacio no puede estar vacia"
            );
        }
                //validar que no haya un espacio con el mismo nombre registrado en BD
        if (repositorioEspacio.findByNombre(datos.getNombre()).isPresent()){
            throw new ResponseStatusException(  
                HttpStatus.CONFLICT,
                "Ya existe un espacio registrado con ese nombre, intente con otro nombre"
            );
                


        return this.repositorioEspacio.save(datos);
        //validar que datos me envian y si estos cumplen las reglas de negocio 
    }

    public Espacio modificarEspacioEnBD (Espacio datos, UUID id){
        //1 Buscar si el espacio existe en BD
         Optional<Espacio> espacioBuscado = this.repositorioEspacio.findById(id);
         if (espacioBuscado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "El espacio no existe, intente de nuevo" 
            );
        }
        Espacio espacioEncontrado = espacioBuscado.get();

            
        //2. Validar la informacion nueva que me mando el cliente

        if (datos.getNombre().isEmpty()||datos.getNombre().isBlank()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El nombre del espacio no puede estar vacio"
            );
        }
        
        //3 ejecutar el nuevo guardado y retornar 
        espacioEncontrado.setNombre(datos.getNombre());
        return this.repositorioEspacio.save(espacioEncontrado);
        //validar que los datos me envian y si estos cumplen las reglas de negocio 
        //modificar los datos BD con ayuda de un repositorio
    }

    public boolean eliminarEspacioEnBD(UUID id){
         Optional<Espacio> espacioBuscado = this.repositorioEspacio.findById(id);
        if (espacioBuscado.isEmpty()){
            throw new ResponseStatusException(  
                HttpStatus.NOT_FOUND,
                "El espacio no existe, intente de nuevo" 
            ); 
        }
        Espacio espacioEncontrado = espacioBuscado.get();
        this.repositorioEspacio.deleteById(id);
            return true;
        // buscar y validar si el ID que me envian existe 
        //eliminar el registro en BD 
    }

    public List<Espacio> buscarEspacioEnBD(){
        List<Espacio> espaciosEncontrados = this.repositorioEspacio.findAll();
        return espaciosEncontrados;
        //Dependiendo del parametro de busqueda debo implementar validaciones 
        //devuelvo los usuarios o usuario que encuentre en BD 
    }

}
