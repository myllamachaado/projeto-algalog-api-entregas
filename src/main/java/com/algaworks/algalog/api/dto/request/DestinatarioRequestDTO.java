package com.algaworks.algalog.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class DestinatarioRequestDTO {

    @NotBlank
    private String nome;

    @NotBlank
    private String longradouro;

    @NotBlank
    private String numero;

    @NotBlank
    private String complemento;

    @NotBlank
    private String bairro;
}
