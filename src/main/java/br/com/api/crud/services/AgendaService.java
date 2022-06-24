package br.com.api.crud.services;

import br.com.api.crud.models.Agenda;
import br.com.api.crud.repositories.AgendaRepository;
import br.com.api.crud.utils.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AgendaService {

    @Autowired
    private AgendaRepository agendaRepository;

    public Agenda cadastrarAgenda(Agenda agenda) throws IOException {
        String pathGit = "C:/Users/Cleber/Desktop/arquivos/teste.txt";
        String pathLocalBkp = "C:/Users/Cleber/Desktop/arquivos/testeBKP.txt";
        FileUtils.adicionarLinhas(pathGit, agenda.getNomeMicroservico());
        FileUtils.adicionarLinhas(pathLocalBkp, agenda.getNomeMicroservico());
        return agendaRepository.save(agenda);
    }
    public String removeAgenda(String nomeMicroservico) throws IOException {
        Agenda agenda = agendaRepository.findByNomeMicroservico(nomeMicroservico);
        String message = "";
        if (agenda!=null) {
            String pathGit = "C:/Users/Cleber/Desktop/arquivos/teste.txt";
            String pathLocalBkp = "C:/Users/Cleber/Desktop/arquivos/testeBKP.txt";
            FileUtils.removeLinhas(pathGit, nomeMicroservico);
            FileUtils.removeLinhas(pathLocalBkp, nomeMicroservico);
            agendaRepository.delete(agenda);
            message = "Removido com sucesso!! "+nomeMicroservico;
        }
        return "Erro ao deletar o arquivo: "+nomeMicroservico;
    }

    public List<Agenda> getAllAgenda(){
        return agendaRepository.findAll();
    }

    @Scheduled(fixedRate = 1000)
    public void executarScheduler(){
        LocalDateTime localDateTime = LocalDateTime.now();

        int segundo = localDateTime.getSecond();
        int minuto = localDateTime.getMinute();
        int hora = localDateTime.getHour();
        int dia = localDateTime.getDayOfMonth();
        int mes = localDateTime.getMonthValue();
        int diaSemana = localDateTime.getDayOfWeek().getValue();
        String dataAtual = segundo + " "+minuto+" "+hora+" "+dia+" "+mes+" "+diaSemana;
//        String dataAtual = "* * * "+dia+" "+mes+" "+diaSemana;

        List<Agenda> list = agendaRepository.findAll();
        list.forEach(c -> {
            if (dataAtual.equals(c.getDataRemocao())) {

                String path = "C:/Users/Cleber/Desktop/arquivos/teste.txt";
                String pathBkp = "C:/Users/Cleber/Desktop/arquivos/testeBKP.txt";

                try {

                        FileUtils.removeLinhas(path, c.getNomeMicroservico());
                        FileUtils.removeLinhas(pathBkp, c.getNomeMicroservico());
                        //agendaRepository.delete(c);
                        System.out.println("Lista local atualizada com sucesso!!");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }
}
