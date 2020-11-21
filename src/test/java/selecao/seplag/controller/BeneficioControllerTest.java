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

import selecao.seplag.dominio.Beneficio;
import selecao.seplag.repository.BeneficioRepository;

@WebMvcTest(BeneficioController.class)
public class BeneficioControllerTest {
	
	@MockBean
	private BeneficioRepository repository;
	
	@Autowired
	private MockMvc mvc;
	
	private JacksonTester<Beneficio> json;
	
	@BeforeEach
	public void setup() {
		JacksonTester.initFields(this, new ObjectMapper());
	}
	
	@Test
	public void testSalvar() throws Exception {
		// given
		Beneficio beneficio = new Beneficio("Beneficio", "Descrição");
		given(repository.save(beneficio)).willReturn(beneficio);
		
		// when
		MockHttpServletResponse response = mvc.perform(
				post("/beneficios")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json.write(beneficio).getJson())
				).andReturn()
				.getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString())
				.isEqualTo(json.write(new Beneficio(beneficio.getNome(), beneficio.getDescricao())).getJson());
		
	} 
	
	@Test
	public void testAtualizar() throws Exception {
		// given
		Beneficio beneficio = new Beneficio(1L, "Beneficio", "Descrição");
		given(repository.findById(beneficio.getId())).willReturn(Optional.of(beneficio));

		Beneficio beneficioAtualizado = new Beneficio(1L, "Beneficio", "Descrição Atualizada");
		given(repository.save(beneficioAtualizado)).willReturn(beneficioAtualizado);
		
		// when
		MockHttpServletResponse response = mvc.perform(
				put("/beneficios")
					.contentType(MediaType.APPLICATION_JSON)
					.content(json.write(beneficioAtualizado).getJson())
				).andReturn()
				.getResponse();
		
		// then
		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(json.write(beneficioAtualizado).getJson());
		
	} 
	
	@Test
	public void testAtualizarNaoEncontraBeneficio() {
		// given
		Beneficio beneficio = new Beneficio(1L, "Beneficio", "Descrição");
		given(repository.findById(beneficio.getId())).willReturn(Optional.empty());

		Beneficio beneficioAtualizado = new Beneficio(1L, "Beneficio", "Descrição Atualizada");
		given(repository.save(beneficioAtualizado)).willReturn(beneficioAtualizado);
		
		// when
		String message = "";
		try {
			mvc.perform(put("/beneficios").contentType(MediaType.APPLICATION_JSON)
					.content(json.write(beneficioAtualizado).getJson())).andReturn().getResponse();
		} catch (Exception e) {
			Throwable cause = e.getCause();
			message = cause.getMessage();
		}
		
		// then
		assertThat(message).isEqualTo("No value present");
	} 
}
