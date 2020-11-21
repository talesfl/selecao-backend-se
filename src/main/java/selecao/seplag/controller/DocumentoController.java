package selecao.seplag.controller;

import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import selecao.seplag.dominio.Documento;
import selecao.seplag.service.DocumentoService;

@RestController
@RequestMapping(path = "documentos", produces = "application/json;charset=UTF-8")
public class DocumentoController {
	
	private DocumentoService service;
	
	public DocumentoController(DocumentoService service) {
		this.service = service;
	}

	@PostMapping(consumes = "multipart/form-data")
	public ResponseEntity<Documento> salvar(
			@RequestParam("multipartFile") final MultipartFile multipartFile,
			@RequestParam("categoriaId") final Long categoriaId,
			@RequestParam("processoId") final Long processoId
	) {
		Documento save = service.salvar(multipartFile, categoriaId, processoId);
		return ResponseEntity.ok(save);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Resource> findById(@PathVariable String id) {
		Documento documento = service.findById(id);
		return ResponseEntity.ok()
				.contentType(MediaType.parseMediaType(documento.getTipo()))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + documento.getNome() + "\"")
				.body(new ByteArrayResource(documento.getConteudo()));
	}
	
	@GetMapping("processo/{processoId}/categoria/{categoriaId}")
	public ResponseEntity<List<Documento>> findByProcessoIdAndCategoriaId(
			@PathVariable("processoId") Long processoId, 
			@PathVariable("categoriaId") Long categoriaId
	) {
		List<Documento> documentos = service.findByProcessoIdAndCategoriaId(processoId, categoriaId);
		return ResponseEntity.ok(documentos);
	}
	
}
