package selecao.seplag.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import selecao.seplag.dominio.Servidor;
import selecao.seplag.repository.ServidorRepository;

@RestController
@RequestMapping(path = "servidores", produces = "application/json;charset=UTF-8")
public class ServidorController {

	private final ServidorRepository servidorRepository;

	public ServidorController(
			final ServidorRepository ServidorRepository
	) {
		this.servidorRepository = ServidorRepository;
	}

	@GetMapping("{id}")
	public ResponseEntity<Servidor> findById(@PathVariable("id") final Long id) {
		Servidor servidor = servidorRepository.findById(id).orElseThrow();
		return ResponseEntity.ok(servidor);
	}
	
	@GetMapping
	public ResponseEntity<Page<Servidor>> findByNomeStartingWith(
			@RequestParam final String nome,
			@RequestParam final int pageNumber, 
			@RequestParam final int pageSize
	) {
		Page<Servidor> page = servidorRepository.findByNomeStartingWith(nome, PageRequest.of(pageNumber, pageSize));
		return ResponseEntity.ok(page);
	}

}
