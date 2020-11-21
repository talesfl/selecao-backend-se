package selecao.seplag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import selecao.seplag.dominio.MovimentacaoProcesso;

public interface MovimentacaoProcessoRepository extends JpaRepository<MovimentacaoProcesso, Long> {

	List<MovimentacaoProcesso> findByProcessoId(final Long processoId);
}
