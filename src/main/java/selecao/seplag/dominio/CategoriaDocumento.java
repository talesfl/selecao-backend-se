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
public class CategoriaDocumento {

	@Id
	@GeneratedValue
	@Column(name = "CATEGORIA_DOCUMENTO_ID")
	private final Long id;
	
	@Column(nullable = false, unique = false)
	private final String nome;
	
	@Column(nullable = false)
	private final String descricao;

	public CategoriaDocumento() {
		this(null, null, null);
	}

	public CategoriaDocumento(final Long id) {
		this(id, null, null);
	}
	
	public CategoriaDocumento(final String nome, final String descricao) {
		this(null, nome, descricao);
	}
}
