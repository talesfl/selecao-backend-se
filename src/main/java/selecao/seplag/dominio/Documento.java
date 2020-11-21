package selecao.seplag.dominio;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

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
public class Documento {
	
	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	@Column(name = "DOCUMENTO_ID")
	private final String id;
	
	@Column(nullable = false, updatable = false)
	private final String nome;
	
	@Lob
	@Column(nullable = false, updatable = false)
	private final byte[] conteudo;
	
	@Column(nullable = false, updatable = false)
	private final String tipo;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm:ss")
	private final LocalDateTime dataInsercao;
	
	@ManyToOne
	@JoinColumn(
			name = "CATEGORIA_DOCUMENTO_ID", 
			nullable = false, 
			updatable = false
		)
	private final CategoriaDocumento categoria;
	
	@ManyToOne
	@JoinColumn(
			name = "PROCESSO_ID",
			nullable = false, 
			updatable = false
		)
	private final Processo processo;
	
	public Documento() {
		this(null, null, null, null, null, null, null);
	}
	
	public Documento(
			final String nome,
			final byte[] documento,
			final String tipo,
			final LocalDateTime dataInsercao,
			final CategoriaDocumento categoria,
			final Processo processo
	) {
		this(null, nome, documento, tipo, dataInsercao, categoria, processo);
	}
}
