package com.algaworks.algalog.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaRequestDTO {

    private Long id;

    @Valid
    @NotNull
    private ClienteRequestDTO cliente;

    @Valid
    @NotNull
    private DestinatarioRequestDTO destinatario;

    @NotNull
    private BigDecimal taxa;
}
