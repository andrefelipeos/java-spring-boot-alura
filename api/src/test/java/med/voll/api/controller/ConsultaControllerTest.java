package med.voll.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import med.voll.api.domain.consulta.AgendaDeConsultas;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.consulta.DadosDetalhamentoConsulta;
import med.voll.api.domain.medico.Especialidade;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private JacksonTester<DadosAgendamentoConsulta> dadosAgendamentoConsultaJson;

	@Autowired
	private JacksonTester<DadosDetalhamentoConsulta> dadosDetalhamentoConsultaJson;

	@MockBean
	private AgendaDeConsultas agendaDeConsultas;

	@Test
	@DisplayName("Deveria devolver código 400 quando informações estão inválidas")
	@WithMockUser
	void agendar_canario01() throws Exception {
		var response = mvc.perform(MockMvcRequestBuilders.post("/consultas")).andReturn().getResponse();
		assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
	}

	@Test
	@DisplayName("Deveria devolver código 200 quando informações estão válidas")
	@WithMockUser
	void agendar_canario02() throws Exception {
		var idMedico = 1l;
		var idPaciente = 1l;
		var data = LocalDate.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).atTime(10, 0);
		var especialidade = Especialidade.CARDIOLOGIA;

		DadosDetalhamentoConsulta dadosConsulta = new DadosDetalhamentoConsulta(null, idMedico, idPaciente, data);
		when(agendaDeConsultas.agendar(any())).thenReturn(dadosConsulta);
		String jsonEsperado = dadosDetalhamentoConsultaJson.write(dadosConsulta).getJson();

		var response = mvc.perform(
					post("/consultas")
					.contentType(MediaType.APPLICATION_JSON)
					.content(dadosAgendamentoConsultaJson
							.write(new DadosAgendamentoConsulta(idMedico, idPaciente, data, especialidade))
							.getJson()))
				.andReturn()
				.getResponse();

		assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
	}

}
