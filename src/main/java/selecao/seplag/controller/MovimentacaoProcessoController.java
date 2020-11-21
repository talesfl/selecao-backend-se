package selecao.seplag.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import selecao.seplag.dominio.MovimentacaoProcesso;
import selecao.seplag.dominio.Orgao;
import selecao.seplag.dominio.Processo;
import selecao.seplag.repository.MovimentacaoProcessoRepository;

@RestController
@RequestMapping(path = "movimentacoes", produces = "application/json;charset=UTF-8")
public class MovimentacaoProcessoController {
	
	private MovimentacaoProcessoRepository repository;

	public MovimentacaoProcessoController(MovimentacaoProcessoRepository repository) {
		this.repository = repository;
	}
	
	
	@PostMapping
	public ResponseEntity<MovimentacaoProcesso> salvar(
			@RequestBody final MovimentacaoProcesso movimentacaoProcesso) {
	
		MovimentacaoProcesso save = repository.save(
					new MovimentacaoProcesso(
						LocalDateTime.now(),
						movimentacaoProcesso.getOrgaoOrigem(),
						movimentacaoProcesso.getOrgaoDestino(),
						1L,
						movimentacaoProcesso.getProcesso())
				);
		
		return ResponseEntity.ok(save);
	}
	
	@GetMapping("processo/{processoId}")
	public ResponseEntity<List<MovimentacaoProcesso>> findByProcessoId(
			@PathVariable("processoId") final Long processoId) {
		List<MovimentacaoProcesso> list = repository.findByProcessoId(processoId);
		return ResponseEntity.ok(list);
	}
}
