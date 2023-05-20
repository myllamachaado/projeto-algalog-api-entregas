package com.algaworks.algalog.api.dto.response;

import com.algaworks.algalog.api.model.StatusEntrega;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaResponseDTO {

    private Long id;
    private ClienteResponseDTO cliente;
    private DestinatarioResponseDTO destinatario;
    private BigDecimal taxa;
    private StatusEntrega status;
    private OffsetDateTime dataPedido;
    private OffsetDateTime data_finalizacao;

}
