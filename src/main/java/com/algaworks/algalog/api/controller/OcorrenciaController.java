package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.request.OcorrenciaRequestDTO;
import com.algaworks.algalog.api.dto.response.OcorrenciaResponseDTO;
import com.algaworks.algalog.api.exceptionHandler.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algalog.api.mapper.OcorrenciaAssembler;
import com.algaworks.algalog.api.model.Entrega;
import com.algaworks.algalog.api.model.Ocorrencia;
import com.algaworks.algalog.api.service.EntregaService;
import com.algaworks.algalog.api.service.OcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/entregas/{entregaId}/ocorrencias")
@AllArgsConstructor
public class OcorrenciaController {

    private OcorrenciaService ocorrenciaService;
    private EntregaService entregaService;
    private OcorrenciaAssembler ocorrenciaAssembler;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponseDTO registrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaRequestDTO ocorrencia){
        Ocorrencia registro = ocorrenciaService.registrar(entregaId, ocorrencia.getDescricao());
        return ocorrenciaAssembler.toModel(registro);
    }

    @GetMapping
    public List<OcorrenciaResponseDTO> listar(@PathVariable Long entregaId){
        Entrega entrega = entregaService.buscaEntrega(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
        return ocorrenciaAssembler.toCollection(entrega.getOcorrencias());
    }

}
