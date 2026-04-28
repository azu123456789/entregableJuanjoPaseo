package com.example.paseoAPI.servicios;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.paseoAPI.models.Usuario;
import com.example.paseoAPI.repositorios.IRepositorioUsuario;

@Service
public class usuarioServicio {

    //inyectando una depencia al repositorio usuario

    @Autowired
    private IRepositorioUsuario repositorioUsuario;

    public Usuario guardarUsuarioEnBD(Usuario datos){
                //1 validar el correo a registrar no se haya ingresado previamente 

        if (repositorioUsuario.findByCorreo(datos.getCorreo()).isPresent()){
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Ya existe una cuenta con ese correo ingresado"
            );
        }
        
        //condiciones logicas para validar datos a guardar 

        //validar que el nombre no este vacio o en blanco

        if (datos.getNombre().isEmpty()||datos.getNombre().isBlank()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El nombre no puede estar vacio"
            );
        }
        //validar que la contraseña tenga 6 caracteres o mas

        if (datos.getContraseña().length()<6){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La contraseña debe tener al menos 6 caracteres"
            );
        }
        //Si paso la zona de validaciones procedo la receta (ejecuto la query que se necesite)



        return this.repositorioUsuario.save(datos);
        
    }

    public Usuario modificarUsuarioEnBD (Usuario datos, UUID id){
        //1 Buscar si el usuario existe en BD

        Optional<Usuario> usuarioBuscado = this.repositorioUsuario.findById(id);
        if (usuarioBuscado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "El usuario no existe, intente de nuevo" 
            );
        }
        Usuario usuarioEncontrado = usuarioBuscado.get();

        //2. Validar la informacion nueva que me mando el cliente
        if (datos.getNombre().isEmpty()||datos.getNombre().isBlank()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "revise el nombre ingresado"
            );
        }
        //3 ejecutar el nuevo guardado y retornar 
        usuarioEncontrado.setNombre(datos.getNombre());
        return this.repositorioUsuario.save(usuarioEncontrado);
        //validar que los datos me envian y si estos cumplen las reglas de negocio 
        //modificar los datos BD con ayuda de un repositorio
        

    }


    public boolean eliminarUsuarioEnBD(UUID id){
        
        // buscar y validar si el ID que me envian existe 
        //eliminar el registro en BD 
        Optional<Usuario> usuarioBuscado = this.repositorioUsuario.findById(id);
        if (usuarioBuscado.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "El usuario no existe" 
            );
        }
        Usuario usuarioEncontrado = usuarioBuscado.get();
        this.repositorioUsuario.deleteById(id);
        return true;
    }

    public List <Usuario>buscarUsuariosEnBD(){ 
        //Dependiendo del parametro de busqueda debo implementar validaciones 
        //devuelvo los usuarios o usuario que encuentre en BD 
        List<Usuario> usuariosEncontrados = this.repositorioUsuario.findAll();
        return usuariosEncontrados;
    }

}
