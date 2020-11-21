package selecao.seplag.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import selecao.seplag.dominio.Orgao;

public interface OrgaoRepository extends JpaRepository<Orgao, Long> {

	Page<Orgao> findByNomeStartingWith(final String nome, final Pageable pageable);
}
