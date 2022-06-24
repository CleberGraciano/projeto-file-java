package br.com.api.crud.models;

import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dataRemocao;
    private String nomeMicroservico;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(String dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public String getNomeMicroservico() {
        return nomeMicroservico;
    }

    public void setNomeMicroservico(String nomeMicroservico) {
        this.nomeMicroservico = nomeMicroservico;
    }
}
