package br.com.api.crud.dtos;

import br.com.api.crud.models.Page;
import lombok.*;
import org.modelmapper.ModelMapper;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class PageDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String namePage;
    private List<AtributoDto> campos;

    public static PageDto create(Page page){
        return new ModelMapper().map(page, PageDto.class);
    }
}
