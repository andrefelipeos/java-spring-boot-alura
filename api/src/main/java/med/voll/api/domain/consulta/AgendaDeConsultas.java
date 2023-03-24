package med.voll.api.domain.consulta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.ValidacaoAgendamentoConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

@Service
public class AgendaDeConsultas {

	@Autowired
	private ConsultaRepository consultaRepository;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private List<ValidacaoAgendamentoConsulta> validacoes;

	public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
		if (!pacienteRepository.existsById(dados.idDoPaciente())) {
			throw new ValidacaoException("Identificador de paciente informado não existe");
		}

		if (dados.idDoMedico() != null && !medicoRepository.existsById(dados.idDoMedico())) {
			throw new ValidacaoException("Identificador de médico informado não existe");
		}

		validacoes.forEach(validacao -> validacao.validar(dados));

		var medico = escolherMedico(dados);

		if (medico == null) {
			throw new ValidacaoException("Não existe médico especialista disponível na data escolhida");
		}

		var paciente = pacienteRepository.findById(dados.idDoPaciente()).get();
		var consulta = new Consulta(null, medico, paciente, dados.horarioComData());
		consultaRepository.save(consulta);

		return new DadosDetalhamentoConsulta(consulta);
	}

	private Medico escolherMedico(DadosAgendamentoConsulta dados) {
		if (dados.idDoMedico() != null) {
			return medicoRepository.getReferenceById(dados.idDoMedico());
		}

		if (dados.especialidade() == null) {
			throw new ValidacaoException("Campo Especialidade é obrigatório quando o médico não é selecionado");
		}

		return medicoRepository.escolherMedicoAleatorioLivreNoHorario(dados.especialidade(), dados.horarioComData());
	}

}
