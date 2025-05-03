package br.com.dio.dto;

import br.com.dio.persistence.entity.BoardColumnsKindEnum;

public record BoardColumnsDTO(Long id, String name, BoardColumnsKindEnum kind, int cardsAmount) {

}
