package com.fatec.projeto.projeto2025.Controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.projeto.projeto2025.entities.Cliente;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.fatec.projeto.projeto2025.domain.cliente.ClienteService;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

        private static final Logger logger = LoggerFactory.getLogger(ClienteController.class.getName());

        private final List<Cliente> clientes = new ArrayList<>();
        private Long idCount = 1L;

    //http://localhost:8080/api/cliente/criarCliente => POST
    @PostMapping("/criarCliente")
    public ResponseEntity<Cliente> CriarCliente(@RequestBody Cliente cliente) {
        var novoCliente = clienteService.criarCliente(cliente);
        return new ResponseEntity<>(novoCliente, HttpStatus.OK);
    }

    @GetMapping("/listarClientes")
    public List<Cliente> ListarClientes() {
        return clienteService.listarClientes();

    }

    @DeleteMapping("/deletarCliente/{id}")
    public String DeletarClientes(@PathVariable Long id) {
        for(Cliente cliente : clientes) {
            if (cliente.getId().equals(id)) {
                clientes.remove(cliente);
                return "Usuário de id " + id + " foi deletado";
            }
        }

        return "Usuário não encontrado";
    }

    @PutMapping("/atualizarCliente")
    public String atualizarCliente(@RequestParam Long id, @RequestBody Cliente cliente) {
        for(Cliente cl : clientes) {
            if(cl.getId() == id) {
                cl.setNome(cliente.getNome());
                cl.setIdade(cliente.getIdade());
                cl.setEndereco(cliente.getEndereco());
                return "Cliente " + cl.getNome() + " foi atualizado.";
            }
        }

        return "usuário não encontrado.";
    }

    @GetMapping("/cliente")
    public Cliente ListarCliente(@RequestParam Long id) {
        Cliente retornarCliente = null;
        for(Cliente cli : clientes) {
            if(cli.getId() == id) {
                retornarCliente = cli;
            }
        }

        return retornarCliente;
    }
}