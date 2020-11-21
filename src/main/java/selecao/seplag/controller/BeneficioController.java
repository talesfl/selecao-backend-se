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

import selecao.seplag.dominio.Beneficio;
import selecao.seplag.repository.BeneficioRepository;

@RestController
@RequestMapping(path = "beneficios", produces = "application/json;charset=UTF-8")
public class BeneficioController {

	private final BeneficioRepository beneficioRepository;

	public BeneficioController(final BeneficioRepository beneficioRepository) {
		this.beneficioRepository = beneficioRepository;
	}

	@PostMapping
	public ResponseEntity<Beneficio> salvar(@RequestBody final Beneficio beneficio) {
		Beneficio save = beneficioRepository.save(beneficio);
		return ResponseEntity.ok(save);
	}

	@PutMapping
	public ResponseEntity<Beneficio> atualizar(@RequestBody final Beneficio beneficio) {
		
		// Busca a entidade para atualizar. 
		// Caso não a encontre lança uma exceção.
		beneficioRepository.findById(beneficio.getId()).orElseThrow();
		
		Beneficio save = beneficioRepository.save(beneficio);
		return ResponseEntity.ok(save);
	}

	@GetMapping("{id}")
	public ResponseEntity<Beneficio> findById(@PathVariable("id") final Long id) {
		Beneficio beneficio = beneficioRepository.findById(id).orElseThrow();
		return ResponseEntity.ok(beneficio);
	}

	@GetMapping
	public ResponseEntity<Page<Beneficio>> findByNomeStartingWith(@RequestParam final String nome,
			@RequestParam final int pageNumber, @RequestParam final int pageSize) {

		Page<Beneficio> page = beneficioRepository.findByNomeStartingWith(nome, PageRequest.of(pageNumber, pageSize));
		return ResponseEntity.ok(page);
	}

	@DeleteMapping("{id}")
	public ResponseEntity<Void> delete(@PathVariable("id") final Long id) {

		beneficioRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
