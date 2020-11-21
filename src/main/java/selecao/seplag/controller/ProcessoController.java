package selecao.seplag.controller;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import selecao.seplag.dominio.Processo;
import selecao.seplag.repository.ProcessoRepository;

@RestController
@RequestMapping(path = "processos", produces = "application/json;charset=UTF-8")
public class ProcessoController {
	
	private ProcessoRepository repository;

	public ProcessoController(ProcessoRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	public ResponseEntity<Processo> salvar(@RequestBody final Processo processo) {
		
		Processo save = repository.save(new Processo(
					processo.getNome(),
					processo.getDescricao(),
					LocalDateTime.now(),
					processo.getServidor(),
					processo.getBeneficio()
				));
		
		return ResponseEntity.ok(save);
	}
	
	@PutMapping
	public ResponseEntity<Processo> atualizar(@RequestBody final Processo processo) {
		repository.findById(processo.getId()).orElseThrow();
		
		Processo save = repository.save(processo);
		return ResponseEntity.ok(save);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Processo> findById(@PathVariable("id") final Long id) {
		Processo Processo = repository.findById(id).orElseThrow();
		return ResponseEntity.ok(Processo);
	}
	
	@GetMapping
	public ResponseEntity<Page<Processo>> findByNomeStartingWith(
			@RequestParam final String nome,
			@RequestParam final int pageNumber, 
			@RequestParam final int pageSize
	) {
		Page<Processo> page = repository.findByNomeStartingWith(nome, PageRequest.of(pageNumber, pageSize));
		return ResponseEntity.ok(page);
	}
}
