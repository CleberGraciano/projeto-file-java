package br.com.api.crud.models;


import br.com.api.crud.dtos.PageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Page {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    private String id;
    private String namePage;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL)
    private List<Atributos> campos;

    public static Page create(PageDto pageDto){
        return new ModelMapper().map(pageDto, Page.class);
    }

}
