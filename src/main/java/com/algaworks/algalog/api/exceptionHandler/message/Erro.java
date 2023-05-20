package com.algaworks.algalog.api.exceptionHandler.message;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
public class Erro {

    private Integer status;
    private OffsetDateTime data;
    private String message;
    private List<Campo> campos;

}
