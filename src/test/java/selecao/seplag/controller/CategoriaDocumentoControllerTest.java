package selecao.seplag.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import selecao.seplag.dominio.CategoriaDocumento;
import selecao.seplag.repository.CategoriaDocumentoRepository;

@WebMvcTest(CategoriaDocumentoController.class)
public class CategoriaDocumentoControllerTest {
	
	@MockBean
	private CategoriaDocumentoRepository repository;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<CategoriaDocumento> json;
	
	@BeforeEach
	private void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void testSalvar() throws Exception {
		
		// given
		CategoriaDocumento categoria = new CategoriaDocumento("Categoria", "Categoria do documento");
		given(repository.save(categoria)).willReturn(categoria);
		
		// when
		MockHttpServletResponse response = mvc.perform(
				post("/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.write(categoria).getJson())
				).andReturn().getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString())
			.isEqualTo(json.write(new CategoriaDocumento(
					categoria.getNome(), 
					categoria.getDescricao())).getJson());
	}
	
	@Test
	public void testAtualizar() throws Exception {
		
		// given
		CategoriaDocumento categoria = new CategoriaDocumento(1L, "Categoria", "Categoria do documento");
		given(repository.findById(categoria.getId())).willReturn(Optional.of(categoria));
		
		CategoriaDocumento categoriaAtualizado = new CategoriaDocumento(1L, "Categoria", "Categoria do documento Atualizado");
		given(repository.save(categoriaAtualizado)).willReturn(categoriaAtualizado);
		
		// when
		MockHttpServletResponse response = mvc.perform(
				put("/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json.write(categoriaAtualizado).getJson())
				).andReturn()
				.getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(categoriaAtualizado).getJson());
	}
	
	@Test
	public void testAtualizarNaoEncontraCategoriaDocumento() {
		// given
		CategoriaDocumento categoria = new CategoriaDocumento(1L, "Categoria", "Categoria do documento");
		given(repository.findById(categoria.getId())).willReturn(Optional.empty());
		
		CategoriaDocumento categoriaAtualizado = new CategoriaDocumento(1L, "Categoria", "Categoria do documento Atualizado");
		given(repository.save(categoriaAtualizado)).willReturn(categoriaAtualizado);
		
		// When
		String message = "";
		try {
			mvc.perform(
					put("/categorias")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json.write(categoriaAtualizado).getJson())
				).andReturn().getResponse();
		} catch (Exception e) {
			Throwable cause = e.getCause();
			message = cause.getMessage();
		}
		
		// then
		assertThat(message).isEqualTo("No value present");
	}
}
