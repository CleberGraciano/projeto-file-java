package br.com.api.crud.repositories;

import br.com.api.crud.models.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PageRepository extends JpaRepository<Page, String> {

    Page findByNamePage(String name);

}
