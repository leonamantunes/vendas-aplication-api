package estudos.io.rest.controller;

import estudos.io.domain.entity.Produto;
import estudos.io.domain.repositorio.ProdutosRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

        private ProdutosRepository produtos;

    public ProdutoController(ProdutosRepository produtos) {
        this.produtos = produtos;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto save(@RequestBody Produto produto){
        return produtos.save(produto);
    }


}
