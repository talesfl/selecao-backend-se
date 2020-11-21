package selecao.seplag.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import selecao.seplag.dominio.Servidor;

public interface ServidorRepository extends JpaRepository<Servidor, Long> {

	Page<Servidor> findByNomeStartingWith(@Param("nome") final String nome, final Pageable pageable);
}
