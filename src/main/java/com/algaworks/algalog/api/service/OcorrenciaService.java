package com.algaworks.algalog.api.service;

import com.algaworks.algalog.api.exceptionHandler.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algalog.api.model.Entrega;
import com.algaworks.algalog.api.model.Ocorrencia;
import com.algaworks.algalog.api.repository.OcorrenciaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class OcorrenciaService {

    private OcorrenciaRepository ocorrenciaRepository;
    private EntregaService entregaService;

    @Transactional
    public Ocorrencia registrar(Long idEntrega, String descricao){
        Entrega entrega = entregaService.buscaEntrega(idEntrega)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));

        return entrega.adicionaOcorrencia(descricao);
    }



}
