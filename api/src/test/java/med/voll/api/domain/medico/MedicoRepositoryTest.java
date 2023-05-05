package med.voll.api.domain.medico;

import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDateTime;

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
	private TestEntityManager testEntityManager;

	@Test
	void testEscolherMedicoAleatorioLivreNoHorario() {
		fail("Not yet implemented");
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
