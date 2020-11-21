package selecao.seplag.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import selecao.seplag.dominio.Documento;

public interface DocumentoService {
	
	Documento salvar(
			final MultipartFile multipartFile, 
			final Long categoriaId, 
			final Long processoId
		);
	
	Documento findById(final String id);
	
	List<Documento> findByProcessoIdAndCategoriaId(final Long processoId, final Long categoriaId);
}
