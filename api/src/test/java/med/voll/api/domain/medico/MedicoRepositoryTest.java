package med.voll.api.domain.medico;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import med.voll.api.domain.consulta.Consulta;
import med.voll.api.domain.endereco.DadosCadastroEndereco;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.Paciente;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private TestEntityManager testEntityManager;

	@Test
	@DisplayName("Deveria retornar null quando houver um único médico cadastrado não está disponível no horário")
	void escolherMedicoAleatorioLivreNoHorarioCenario01() {
		LocalDateTime proximaSegundaAsDez = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
				.atTime(10, 0);

		Medico medico = cadastrarMedico("medico", "medico@mail.com", "123456", Especialidade.CARDIOLOGIA);
		Paciente paciente = cadastrarPaciente("paciente", "paciente@mail.com", "12345678900");
		cadastrarConsulta(medico, paciente, proximaSegundaAsDez);

		Medico medicoEscolhido = medicoRepository
				.escolherMedicoAleatorioLivreNoHorario(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);

		assertThat(medicoEscolhido).isNull();
	}

	@Test
	@DisplayName("Deveria retornar médico cadastrado que está disponível no horário")
	void escolherMedicoAleatorioLivreNoHorarioCenario02() {
		LocalDateTime proximaSegundaAsDez = LocalDate.now()
				.with(TemporalAdjusters.next(DayOfWeek.MONDAY))
				.atTime(10, 0);

		Medico medico = cadastrarMedico("medico", "medico@mail.com", "123456", Especialidade.CARDIOLOGIA);

		Medico medicoEscolhido = medicoRepository
				.escolherMedicoAleatorioLivreNoHorario(Especialidade.CARDIOLOGIA, proximaSegundaAsDez);

		assertThat(medicoEscolhido).isEqualTo(medico);
	}

	private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
		testEntityManager.persist(new Consulta (null, medico, paciente, data, false, null));
	}

	private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
		Medico medico = new Medico(dadosMedico(nome, email, crm, especialidade));
		testEntityManager.persist(medico);
		return medico;
	}

	private Paciente cadastrarPaciente(String nome, String email, String cpf) {
	    var paciente = new Paciente(dadosPaciente(nome, email, cpf));
	    testEntityManager.persist(paciente);
	    return paciente;
	}

	private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
	    return new DadosCadastroMedico(
	            nome,
	            email,
	            "88 98100 9876",
	            crm,
	            especialidade,
	            dadosEndereco()
	    );
	}

	private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
	    return new DadosCadastroPaciente(
	            nome,
	            email,
	            "88 98100 1234",
	            cpf,
	            dadosEndereco()
	    );
	}

	private DadosCadastroEndereco dadosEndereco() {
	    return new DadosCadastroEndereco(
	            "Rua Principal",
	            "Centro",
	            "62580000",
	            "Acaraú",
	            "CE",
	            null,
	            null
	    );
	}

}
