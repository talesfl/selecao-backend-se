package selecao.seplag.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import selecao.seplag.dominio.CategoriaDocumento;
import selecao.seplag.repository.CategoriaDocumentoRepository;

@RestController
@RequestMapping(path = "categorias", produces = "application/json;charset=UTF-8")
public class CategoriaDocumentoController {

	private final CategoriaDocumentoRepository repository;

	public CategoriaDocumentoController(final CategoriaDocumentoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	public ResponseEntity<CategoriaDocumento> salvar(
			@RequestBody final CategoriaDocumento categoriaDocumento) {
		
		CategoriaDocumento save = repository.save(categoriaDocumento);
		return ResponseEntity.ok(save);
	}
	
	@PutMapping
	public ResponseEntity<CategoriaDocumento> atualizar(
			@RequestBody final CategoriaDocumento categoriaDocumento) {
		
		// Busca a entidade para atualizar. 
		// Caso não a encontre lança uma exceção.
		repository.findById(categoriaDocumento.getId()).orElseThrow();
		
		CategoriaDocumento save = repository.save(categoriaDocumento);
		return ResponseEntity.ok(save);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<CategoriaDocumento> findById(@PathVariable final Long id) {
		CategoriaDocumento categoriaDocumento = repository.findById(id).orElseThrow();
		return ResponseEntity.ok(categoriaDocumento);
	}
	
	@GetMapping
	public ResponseEntity<Page<CategoriaDocumento>> findByNomeStartingWith(@RequestParam final String nome,
			@RequestParam final int pageNumber, @RequestParam final int pageSize) {

		Page<CategoriaDocumento> page = repository.findByNomeStartingWith(nome, PageRequest.of(pageNumber, pageSize));
		return ResponseEntity.ok(page);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable final Long id) {
		
		repository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
