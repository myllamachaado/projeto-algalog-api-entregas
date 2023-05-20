package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.dto.request.EntregaRequestDTO;
import com.algaworks.algalog.api.dto.response.EntregaResponseDTO;
import com.algaworks.algalog.api.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class EntregaAssembler {

    private ModelMapper mapper;

    public EntregaResponseDTO toModel(Entrega entrega){
        return mapper.map(entrega, EntregaResponseDTO.class);
    }

    public List<EntregaResponseDTO> toCollection(List<Entrega> entregas){
        return entregas.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaRequestDTO entrega){
        return mapper.map(entrega, Entrega.class);
    }

}
