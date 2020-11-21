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

import selecao.seplag.dominio.Orgao;
import selecao.seplag.repository.OrgaoRepository;

@RestController
@RequestMapping(path = "orgaos", produces = "application/json;charset=UTF-8")
public class OrgaoController {

	private OrgaoRepository repository;

	public OrgaoController(OrgaoRepository repository) {
		this.repository = repository;
	}

	@PostMapping
	public ResponseEntity<Orgao> salvar(@RequestBody Orgao orgao) {
		Orgao save = repository.save(orgao);
		return ResponseEntity.ok(save);
	}

	@PutMapping
	public ResponseEntity<Orgao> atualizar(@RequestBody Orgao orgao) {
		repository.findById(orgao.getId()).orElseThrow();

		Orgao save = repository.save(orgao);
		return ResponseEntity.ok(save);
	}

	@GetMapping
	public ResponseEntity<Page<Orgao>> findByNomeStartingWith(@RequestParam final String nome,
			@RequestParam final int pageNumber, @RequestParam final int pageSize) {

		Page<Orgao> page = repository.findByNomeStartingWith(nome, PageRequest.of(pageNumber, pageSize));
		return ResponseEntity.ok(page);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {
		repository.deleteById(id);
		return ResponseEntity.noContent().build();	
	}
}
