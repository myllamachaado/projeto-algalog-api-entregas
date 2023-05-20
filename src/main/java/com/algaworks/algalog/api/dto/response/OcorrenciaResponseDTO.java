package com.algaworks.algalog.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class OcorrenciaResponseDTO {

    private Long id;
    private String descricao;
    private OffsetDateTime dataRegistro;

}
