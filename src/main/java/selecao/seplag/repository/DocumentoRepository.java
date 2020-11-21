package selecao.seplag.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import selecao.seplag.dominio.Documento;

public interface DocumentoRepository extends JpaRepository<Documento, String> {

	List<Documento> findByProcessoIdAndCategoriaId(final Long processoId, final Long categoriaId);

}
