package br.com.api.crud.schedule;

import br.com.api.crud.repositories.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task {

    @Autowired
    AgendaRepository agendaRepository;



    @Scheduled(cron = "40 38 22 * * *")
    public void runJobConfirmaPagamento() {
        System.out.println("Entrou: "+LocalDateTime.now());
    }

}
