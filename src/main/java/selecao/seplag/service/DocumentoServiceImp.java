package selecao.seplag.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import selecao.seplag.dominio.CategoriaDocumento;
import selecao.seplag.dominio.Documento;
import selecao.seplag.dominio.Processo;
import selecao.seplag.repository.DocumentoRepository;

@Service
public class DocumentoServiceImp implements DocumentoService {

	private DocumentoRepository repository;
	
	public DocumentoServiceImp(DocumentoRepository repository) {
		this.repository = repository;
	}

	@Override
	public Documento salvar(
			final MultipartFile multipartFile, 
			final Long categoriaId, 
			final Long processoId
		) {
		
		try {
			Documento save = new Documento(
				multipartFile.getOriginalFilename(), 
				multipartFile.getBytes(), 
				multipartFile.getContentType(),
				LocalDateTime.now(),
				new CategoriaDocumento(categoriaId),
				new Processo(processoId)
			);
			
			return repository.save(save);
		} catch (IOException e) {
			throw new RuntimeException("Não foi possível salvar o documento.");
		}
		
	}

	@Override
	public Documento findById(final String id) {
		return repository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Documento não encontrado."));
	}

	@Override
	public List<Documento> findByProcessoIdAndCategoriaId(final Long processoId, final Long categoriaId) {
		return repository.findByProcessoIdAndCategoriaId(processoId, categoriaId);
	}

}
