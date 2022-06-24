package br.com.api.crud.services;
import br.com.api.crud.models.Atributos;
import br.com.api.crud.models.Page;
import br.com.api.crud.repositories.AtributoRepository;
import br.com.api.crud.repositories.PageRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PageService {

    private final PageRepository pageRepository;
    private final AtributoRepository atributoRepository;

    public Page createPage(Page page){

        return pageRepository.save(page);
    }

    public List<Page> listPages(){
        return pageRepository.findAll();
    }

    public Page listPageByNamePage(String namePage){
        return pageRepository.findByNamePage(namePage);
    }

    public Page updatePageByNamePage(String namePage, Page page){

        Page pageBusca = pageRepository.findByNamePage(namePage);
        Atributos campo = new Atributos();


        if (pageBusca.getNamePage().equals(namePage)) {

            pageBusca.setId(page.getId());
            pageBusca.setNamePage(page.getNamePage());
            pageBusca.setCampos(page.getCampos().stream().map(
                    c -> {
                        c.setPage(page);
                        return c;
                    }
            ).collect(Collectors.toList()));
                pageRepository.save(pageBusca);
                log.info("Pagina Alterada com sucesso!!");
        }
        log.info("Pagina não encontrada!!");
        return pageBusca;
    }

    public void deletePage(String namePage) {
        Page pageBusca = pageRepository.findByNamePage(namePage);

        if (pageBusca.getNamePage().equals(namePage)) {
            try {
                pageRepository.delete(pageBusca);
                log.info("Pagina deletada com sucesso!!");
            } catch (RuntimeException e) {
                log.info("Erro ao deletar Pagina!!" + e.getMessage());
            }
        }
        log.info("Pagina não encontrada!!");
    }

}
