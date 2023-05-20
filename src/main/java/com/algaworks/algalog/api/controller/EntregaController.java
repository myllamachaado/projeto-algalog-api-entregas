package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.request.EntregaRequestDTO;
import com.algaworks.algalog.api.dto.response.EntregaResponseDTO;
import com.algaworks.algalog.api.mapper.EntregaAssembler;
import com.algaworks.algalog.api.model.Entrega;
import com.algaworks.algalog.api.service.EntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/entregas")
@AllArgsConstructor
public class EntregaController {

    private EntregaService entregaService;
    private EntregaAssembler entregaAssembler;

    @GetMapping
    public List<EntregaResponseDTO> retornaEntregas(){
        return entregaAssembler.toCollection(entregaService.listaEntregas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponseDTO> retornaEntregaPorId(@PathVariable Long id){
        return entregaService.buscaEntrega(id)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EntregaResponseDTO> insereEntrega(@Valid @RequestBody EntregaRequestDTO entregaRequest){
        Entrega entrega = entregaAssembler.toEntity(entregaRequest);
        return ResponseEntity.ok(entregaAssembler.toModel(entregaService.insereEntrega(entrega)));
    }

    @PutMapping("/finalizacao")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void finalizaEntrega(@PathVariable Long entregaId){
        entregaService.finalizaEntrega(entregaId);
    }

}
