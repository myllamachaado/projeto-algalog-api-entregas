package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.dto.response.ClienteResponseDTO;
import com.algaworks.algalog.api.model.Cliente;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class ClienteAssembler {

    private ModelMapper mapper;

    public ClienteResponseDTO toModel(Cliente cliente) {
        return mapper.map(cliente, ClienteResponseDTO.class);
    }

    public List<ClienteResponseDTO> toCollection(List<Cliente> clientes){
        return clientes.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }
}
