package org.gvasquez.desafio.controller;

import org.gvasquez.desafio.dto.LibroDTO;
import org.gvasquez.desafio.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("libros")
public class LibrosController {

    @Autowired
    private ServiceService service;

    //@CrossOrigin(origins = "http://localhost:4200")
    @GetMapping()
    private List<LibroDTO> obtenerTodasLosLibros() {
        return service.obtenerTodosLosLibros();
    }

}
