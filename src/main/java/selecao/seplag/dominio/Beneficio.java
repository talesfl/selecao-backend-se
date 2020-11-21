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
public final class Beneficio {
	
	@Id
	@GeneratedValue
	@Column(name = "BENEFICIO_ID")
	private final Long id;
	
	@Column(nullable = false, unique = true)
	private final String nome;
	
	@Column(nullable = false)
	private final String descricao;
	
	// Contrutor vazio para JSON / JPA
	public Beneficio() {
		this(null, null, null);
	}
	
	public Beneficio(final String nome, final String descricao) {
		this(null, nome, descricao);
	}
}
