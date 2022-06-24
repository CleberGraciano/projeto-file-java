package br.com.api.crud.dtos;

import br.com.api.crud.models.Atributos;
import lombok.*;
import org.modelmapper.ModelMapper;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class AtributoDto {

    private String name;
    private String type;
    private String description;


    public static AtributoDto create(Atributos atributos){
        return new ModelMapper().map(atributos, AtributoDto.class);
    }
}
