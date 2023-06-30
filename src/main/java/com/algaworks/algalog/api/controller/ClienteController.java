package com.algaworks.algalog.api.controller;

import com.algaworks.algalog.api.dto.response.ClienteResponseDTO;
import com.algaworks.algalog.api.mapper.ClienteAssembler;
import com.algaworks.algalog.api.model.Cliente;
import com.algaworks.algalog.api.service.ClienteService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@AllArgsConstructor
public class ClienteController {

    private ClienteService clienteService;
    private ClienteAssembler clienteAssembler;

    @GetMapping
    public List<ClienteResponseDTO> retornaClientes(){
        return clienteAssembler.toCollection(clienteService.listaClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> retornaClientePorId(@PathVariable Long id){
        return clienteService.buscaCliente(id)
                .map(cliente -> ResponseEntity.ok(clienteAssembler.toModel(cliente)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ClienteResponseDTO> insereCliente(@Valid @RequestBody Cliente cliente){
        Cliente clienteInserido = clienteService.insereCliente(cliente);
        return ResponseEntity.ok(clienteAssembler.toModel(cliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> atualizaCliente(@Valid @RequestBody Cliente cliente, @PathVariable Long id){
        if(!clienteService.clienteExiste(id)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(clienteAssembler.toModel(clienteService.atualizaCliente(cliente, id)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletaCliente(@PathVariable Long id){
        if(!clienteService.clienteExiste(id)){
            return ResponseEntity.notFound().build();
        }
        clienteService.deletaCliente(id);
        return ResponseEntity.noContent().build();
    }

}
