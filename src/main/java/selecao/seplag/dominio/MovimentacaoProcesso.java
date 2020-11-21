package selecao.seplag.dominio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
public class MovimentacaoProcesso {
	
	@Id
	@GeneratedValue
	@Column(name = "MOVIMENTACAO_PROCESSO_ID")
	private final Long id;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	@Column(name = "DATA_TRAMITACAO", nullable = false, updatable = false)
	private final LocalDateTime dataTramitacao;
	
	@ManyToOne
	@JoinColumn(
			name = "ORGAO_ORIGEM_ID", 
			referencedColumnName = "ORGAO_ID",
			nullable = false, 
			updatable = false
		)
	private final Orgao orgaoOrigem;
	
	@ManyToOne
	@JoinColumn(
			name = "ORGAO_DESTINO_ID", 
			referencedColumnName = "ORGAO_ID",
			nullable = false, 
			updatable = false
		)
	private final Orgao orgaoDestino;
	
	private final Long usuario;
	
	@ManyToOne
	@JoinColumn(name = "PROCESSO_ID", nullable = false, updatable = false)
	private final Processo processo;
	
	public MovimentacaoProcesso() {
		this(null, null, null, null, null, null);
	}
	
	public MovimentacaoProcesso(
			final LocalDateTime dataTramitacao,
			final Orgao orgaoOrigem,
			final Orgao orgaoDestino,
			final Long usuario,
			final Processo processo
	) {
		this(null, dataTramitacao, orgaoOrigem, orgaoDestino, usuario, processo);
	}
}
