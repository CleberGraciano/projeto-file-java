package br.com.api.crud.repositories;

import br.com.api.crud.models.Agenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendaRepository extends JpaRepository<Agenda, Long> {

    String findByDataRemocao(String valor);

    Agenda findByNomeMicroservico(String nomeMs);
}
