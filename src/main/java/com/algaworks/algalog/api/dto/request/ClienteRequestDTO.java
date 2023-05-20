package com.algaworks.algalog.api.dto.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ClienteRequestDTO {

    @NotNull
    private Long id;

}
