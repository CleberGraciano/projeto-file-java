package br.com.api.crud.controllers;

import br.com.api.crud.dtos.PageDto;
import br.com.api.crud.models.Atributos;
import br.com.api.crud.models.Page;
import br.com.api.crud.services.PageService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("pages")
@AllArgsConstructor
@CrossOrigin
public class PageController {

    private final PageService pageService;

    @PostMapping
    public ResponseEntity<PageDto> createPage(@RequestBody PageDto pageDto){
        var id = UUID.randomUUID().toString();
        var pageEntity = Page.builder().build();

        pageEntity.setId(id);
        pageEntity.setNamePage(pageDto.getNamePage());
        pageEntity.setCampos(pageDto.getCampos().stream().map(atributo ->
                Atributos.builder().page(pageEntity)
                        .name(atributo.getName())
                        .description(atributo.getDescription())
                        .type(atributo.getType()).build()).collect(Collectors.toList()));

        log.info("Pagina Salva com seucesso!! "+pageDto);
        pageService.createPage(pageEntity);

        return ResponseEntity.status(HttpStatus.CREATED).body(pageDto);

    }

    @GetMapping("{pageName}")
    public ResponseEntity<PageDto> listPageByPageName(@PathVariable String pageName){
        Page page =  pageService.listPageByNamePage(pageName);
        return ResponseEntity.status(HttpStatus.CREATED).body(PageDto.create(page));
    }

    @PutMapping("{namePage}")
    public ResponseEntity<PageDto> updatePageByName (@PathVariable String namePage, @RequestBody PageDto page){
        Page pageFind = pageService.updatePageByNamePage(namePage, Page.create(page));
        return ResponseEntity.status(HttpStatus.OK).body(PageDto.create(pageFind));
    }


}
