package selecao.seplag.dominio;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@RequiredArgsConstructor
@Entity
public class Orgao {
	
	@Id
	@GeneratedValue
	@Column(name = "ORGAO_ID")
	private final Long id;
	
	@Column(nullable = false, unique = true)
	private final String nome;
	
	@Column(nullable = false)
	private final String descricao;	
	
	public Orgao() {
		this(null, null, null);
	}
	
	public Orgao(final String nome, final String descricao) {
		this(null, nome, descricao);
	}
}
