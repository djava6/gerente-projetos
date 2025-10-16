package br.com.useinet.gerente.controller;

import br.com.useinet.gerente.model.Projeto;
import br.com.useinet.gerente.service.ProjetoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projetos")
@RequiredArgsConstructor
public class ProjetoController {

    private final ProjetoService projetoService;

    @GetMapping
    public List<Projeto> listar() {
        return projetoService.listar();
    }

    @GetMapping("/{id}")
    public Projeto buscar(@PathVariable Long id) {
        return projetoService.buscarPorId(id);
    }

    @PostMapping
    public Projeto criar(@RequestBody Projeto projeto) {
        return projetoService.salvar(projeto);
    }

    @PutMapping("/{id}")
    public Projeto atualizar(@PathVariable Long id, @RequestBody Projeto projeto) {
        return projetoService.atualizar(id, projeto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        projetoService.excluir(id);
    }
}
