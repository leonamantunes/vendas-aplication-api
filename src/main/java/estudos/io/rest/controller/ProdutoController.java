package estudos.io.rest.controller;

import estudos.io.domain.entity.Cliente;
import estudos.io.domain.entity.Produto;
import estudos.io.domain.repositorio.ProdutosRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

        private ProdutosRepository produtos;

    public ProdutoController(ProdutosRepository produtos) {
        this.produtos = produtos;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody Produto produto){
        return produtos.save(produto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update(@PathVariable Integer id, @RequestBody Produto produto){
        produtos
                .findById(id)
                .map( p -> {
                    produto.setId(p.getId());
                    produtos.save(produto);
                    return produto;
                }).orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não Encontrado."));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        produtos
                .findById(id)
                .map( p -> {
                    produtos.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não Encontrado."));
    }

    @GetMapping("/{id}")
    public Produto getById(@PathVariable Integer id){
        return produtos
                .findById(id)
                .orElseThrow( () ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Produto não Encontrado."));
    }

    @GetMapping
    public List<Produto> find(Produto filtro ){
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(
                        ExampleMatcher.StringMatcher.CONTAINING );

        Example example = Example.of(filtro, matcher);
        return produtos.findAll(example);
    }

}
