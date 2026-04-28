package com.example.paseoAPI.controladores;

@RestController
@RequestMapping ("/paseoapo/v1/Espacios")

public class EspacioControlador {
     //por cada servicio ofrecido 
    //configuro 1 funcion controladora 

    @Autowired
    EspacioServicio servicio; 

    //funcion para controlar el guardado 

    @postMapping
    public ResponseEntity<Espacio>controladorModificado (@RequestBody Espacio datos, @PathVariable UUID id){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.modificarEspacioEnBD(datos, id));
    }

    //funcion para controlar la eliminacion 

    @DeleteMapping ("/{id}")
    public ResponseEntity<Boolean> controladorEliminar (@PathVariable UUID id){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.eliminarEspacioEnBD(id));
    }

    //funcion para controlar la busqueda

    @GetMapping
    public ResponseEntity<List<Espacio>> controladorBuscar (){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.buscarEspaciosEnBD());
    }

    //funcion para controlar el listar 

    @GetMapping 
    public ResponseEntity<?> controladorListar (){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.listarEspaciosEnBD());
    }

}



