package br.com.dio.dto;

import br.com.dio.persistence.entity.BoardColumnsKindEnum;

public record BoardColumnsInfoDTO(Long id, int order, BoardColumnsKindEnum kind) {
}