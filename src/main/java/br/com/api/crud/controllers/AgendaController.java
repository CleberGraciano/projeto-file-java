package br.com.api.crud.controllers;

import br.com.api.crud.models.Agenda;
import br.com.api.crud.services.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("agenda")
public class AgendaController {


    @Autowired
    private AgendaService agendaService;

    @PostMapping
    public Agenda salvarAgenda(@RequestBody Agenda agenda) throws IOException {
        return agendaService.cadastrarAgenda(agenda);
    }

    @DeleteMapping("{nomeMicroservico}")
    public String deletarAgenda(@PathVariable String nomeMicroservico) throws IOException {
        return agendaService.removeAgenda(nomeMicroservico);
    }

    @GetMapping
    public List<Agenda> listarTodas(){
        return agendaService.getAllAgenda();
    }

}
