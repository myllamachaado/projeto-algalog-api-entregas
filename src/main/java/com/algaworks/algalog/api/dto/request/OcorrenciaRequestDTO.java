package com.algaworks.algalog.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class OcorrenciaRequestDTO {

    @NotBlank
    private String descricao;

}
