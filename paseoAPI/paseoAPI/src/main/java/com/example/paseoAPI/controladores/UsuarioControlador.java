package com.example.paseoAPI.controladores;

@RestController
@RequestMapping ("/paseoapo/v1/usuarios")

public class UsuarioControlador {

    //por cada servicio ofrecido 
    //configuro 1 funcion controladora 

    @Autowired
    UsuarioServicio servicio; 

    //funcion para controlar el guardado 

    @postMapping
    public ResponseEntity<Usuario>controladorModificado (@RequestBody Usuario datos, @PathVariable UUID id){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.modificarUsuarioEnBD(datos, id));
    }

    //funcion para controlar la eliminacion 

    @DeleteMapping ("/{id}")
    public ResponseEntity<Boolean> controladorEliminar (@PathVariable UUID id){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.eliminarUsuarioEnBD(id));
    }

    //funcion para controlar la busqueda

    @GetMapping
    public ResponseEntity<List<Usuario>> controladorBuscar (){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.buscarUsuariosEnBD());
    }

    //funcion para controlar el listar 

    @GetMapping 
    public ResponseEntity<?> controladorListar (){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.listarUsuariosEnBD());
    }

}
