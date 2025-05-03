package br.com.dio.persistence.entity;

import java.util.ArrayList;
import java.util.List;


import org.apache.commons.lang3.builder.ToStringExclude;

import com.google.common.base.Predicate;
import static br.com.dio.persistence.entity.BoardColumnsKindEnum.CANCEL;
import static br.com.dio.persistence.entity.BoardColumnsKindEnum.INITIAL;

import lombok.Data;

@Data
public class BoardEntity {
    private Long id;
    private String name;
    @ToStringExclude
    private List<BoardColumnsEntity>boardColumns = new ArrayList<>();

    public BoardColumnsEntity getInitialColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(INITIAL));
    }

    public BoardColumnsEntity getCancelColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(CANCEL));
    }

    private BoardColumnsEntity getFilteredColumn(Predicate<BoardColumnsEntity> filter){
        return boardColumns.stream()
                .filter(filter)
                .findFirst().orElseThrow();
    }
    
}
