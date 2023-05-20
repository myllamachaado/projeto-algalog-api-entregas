package com.algaworks.algalog.api.service;

import com.algaworks.algalog.api.exceptionHandler.exceptions.EntidadeNaoEncontradaException;
import com.algaworks.algalog.api.exceptionHandler.exceptions.NegocioException;
import com.algaworks.algalog.api.model.Cliente;
import com.algaworks.algalog.api.model.Entrega;
import com.algaworks.algalog.api.model.StatusEntrega;
import com.algaworks.algalog.api.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EntregaService {

    private EntregaRepository entregaRepository;
    private ClienteService clienteService;

    public Optional<Entrega> buscaEntrega(Long id){
        return entregaRepository.findById(id);
    }

    public List<Entrega> listaEntregas(){
        return entregaRepository.findAll();
    }

    @Transactional
    public Entrega insereEntrega(Entrega entrega){
        Cliente cliente = clienteService.buscaCliente(entrega.getCliente().getId()).get();
        if(!clienteService.clienteExiste(cliente.getId())){
            throw new NegocioException("O cliente vinculado à entrega não existe.");
        }
        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }

    @Transactional
    public void finalizaEntrega(Long entregaId){
        Entrega entrega = this.buscaEntrega(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
        entrega.finalizar();
        entregaRepository.save(entrega);
    }

    public boolean existeEntrega(Long id){
        return entregaRepository.existsById(id);
    }

    @Transactional
    public void deletaEntrega(Long id){
        entregaRepository.deleteById(id);
    }

}
