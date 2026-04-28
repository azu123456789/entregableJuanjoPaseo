package com.example.paseoAPI.controladores;

@RestController
@RequestMapping ("/paseoapo/v1/reservas")

public class ReservaControlador {

     //por cada servicio ofrecido 
    //configuro 1 funcion controladora 

    @Autowired
    ReservaServicio servicio; 

    //funcion para controlar el guardado 

    @postMapping
    public ResponseEntity<Reserva>controladorModificado (@RequestBody Reserva datos, @PathVariable UUID id){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.modificarReservaEnBD(datos, id));
    }

    //funcion para controlar la eliminacion 

    @DeleteMapping ("/{id}")
    public ResponseEntity<Boolean> controladorEliminar (@PathVariable UUID id){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.eliminarReservaEnBD(id));
    }

    //funcion para controlar la busqueda

    @GetMapping
    public ResponseEntity<List<Reserva>> controladorBuscar (){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.buscarReservasEnBD());
    }

    //funcion para controlar el listar 

    @GetMapping 
    public ResponseEntity<?> controladorListar (){
        return ResponseEntity
        .status(HttpStatus.OK)
        .body(this.servicio.listarReservasEnBD());
    }

}



