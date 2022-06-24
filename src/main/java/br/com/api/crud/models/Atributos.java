package br.com.api.crud.models;

import br.com.api.crud.dtos.AtributoDto;
import br.com.api.crud.dtos.PageDto;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.modelmapper.ModelMapper;

import javax.persistence.*;

@Entity
@Data
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Atributos {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(strategy = "uuid2", name = "uuid")
    private String id;
    private String name;
    private String type;
    private String description;

    @ManyToOne
    private Page page;

    public static Atributos create(AtributoDto atributoDto){
        return new ModelMapper().map(atributoDto, Atributos.class);
    }

}
