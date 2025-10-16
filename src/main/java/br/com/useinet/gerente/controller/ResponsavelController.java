package br.com.useinet.gerente.controller;

import br.com.useinet.gerente.model.Responsavel;
import br.com.useinet.gerente.service.ResponsavelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsaveis")
@RequiredArgsConstructor
public class ResponsavelController {

    private final ResponsavelService responsavelService;

    @GetMapping
    public List<Responsavel> listar() {
        return responsavelService.listar();
    }

    @GetMapping("/{id}")
    public Responsavel buscar(@PathVariable Long id) {
        return responsavelService.buscarPorId(id);
    }

    @PostMapping
    public Responsavel criar(@RequestBody Responsavel responsavel) {
        return responsavelService.salvar(responsavel);
    }

    @PutMapping("/{id}")
    public Responsavel atualizar(@PathVariable Long id, @RequestBody Responsavel novo) {
        return responsavelService.atualizar(id, novo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        responsavelService.excluir(id);
    }
}
