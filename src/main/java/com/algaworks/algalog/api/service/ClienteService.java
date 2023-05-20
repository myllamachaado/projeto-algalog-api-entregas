package com.algaworks.algalog.api.service;

import com.algaworks.algalog.api.exceptionHandler.exceptions.NegocioException;
import com.algaworks.algalog.api.model.Cliente;
import com.algaworks.algalog.api.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;

    @Transactional
    public Cliente insereCliente(Cliente cliente){
        boolean existeEmailCadastrado = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
        if(existeEmailCadastrado){
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listaClientes(){
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscaCliente(Long id){
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente;
    }

    public boolean clienteExiste(Long id){
        return clienteRepository.existsById(id);
    }

    @Transactional
    public Cliente atualizaCliente(Cliente cliente, Long id){
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }
    @Transactional
    public void deletaCliente(Long id){
        clienteRepository.deleteById(id);
    }

}
