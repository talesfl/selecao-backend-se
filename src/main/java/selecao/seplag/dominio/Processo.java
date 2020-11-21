package selecao.seplag.dominio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public class Processo {

	@Id
	@GeneratedValue
	@Column(name = "PROCESSO_ID")
	private final Long id;

	@Column(nullable = false)
	private final String nome;
	
	@Column(nullable = false)
	private final String descricao;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private final LocalDateTime dataCriacao;
	
	@OneToOne
	@JoinColumn(name = "SERVIDOR_ID", nullable = false, updatable = false)
	private final Servidor servidor;

	@OneToOne
	@JoinColumn(name = "BENEFICIO_ID", nullable = false, updatable = false)
	private final Beneficio beneficio;
	
	public Processo() {
		this(null, null, null, null, null, null);
	}
	
	public Processo(final Long id) {
		this(id, null, null, null, null, null);
	}
	
	public Processo(
		final String nome,
		final String descricao,
		final LocalDateTime dataCriacao,
		final Servidor servidor,
		final Beneficio beneficio
	) {
		this(null, nome, descricao, dataCriacao, servidor, beneficio);
	}
}
