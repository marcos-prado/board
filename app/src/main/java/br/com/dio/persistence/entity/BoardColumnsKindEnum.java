package br.com.dio.persistence.entity;

import java.util.stream.Stream;

public enum BoardColumnsKindEnum {
    INITIAL, FINAL, CANCEL, PENDING;

    public static BoardColumnsKindEnum findByName(final String name){
        return Stream.of(BoardColumnsKindEnum.values())
        .filter(b -> b.name().equals(name))
        .findFirst().orElseThrow();
    }
}
