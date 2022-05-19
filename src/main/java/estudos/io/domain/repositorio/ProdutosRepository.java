package estudos.io.domain.repositorio;

import estudos.io.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutosRepository extends JpaRepository<Produto,Integer> {
}
