package selecao.seplag.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public class Servidor {

	@Id
	@Column(name = "MATRICULA_ID", nullable = false)
	private final Long matricula;

	@ManyToOne
	@JoinColumn(name = "ORGAO_ID", nullable = false)
	private final Orgao orgao;

	@Column(nullable = false)
	private final Long cpf;

	@Column(nullable = false)
	private final String nome;
	

	public Servidor() {
		this(null, null, null, null);
	}
	
}
