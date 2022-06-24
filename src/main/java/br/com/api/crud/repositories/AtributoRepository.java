package br.com.api.crud.repositories;

import br.com.api.crud.models.Atributos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtributoRepository extends JpaRepository<Atributos, String> {

}
