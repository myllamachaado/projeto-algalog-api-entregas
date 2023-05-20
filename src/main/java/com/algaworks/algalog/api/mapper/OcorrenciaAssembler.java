package com.algaworks.algalog.api.mapper;

import com.algaworks.algalog.api.dto.request.OcorrenciaRequestDTO;
import com.algaworks.algalog.api.dto.response.OcorrenciaResponseDTO;
import com.algaworks.algalog.api.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

    private ModelMapper mapper;

    public OcorrenciaResponseDTO toModel(Ocorrencia ocorrencia){
        return mapper.map(ocorrencia, OcorrenciaResponseDTO.class);
    }

    public List<OcorrenciaResponseDTO> toCollection(List<Ocorrencia> ocorrencias){
        return ocorrencias.stream()
                .map(this::toModel)
                .collect(Collectors.toList());
    }

    public Ocorrencia toEntity(OcorrenciaRequestDTO ocorrencia){
        return mapper.map(ocorrencia, Ocorrencia.class);
    }

}
