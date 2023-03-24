package med.voll.api.domain.consulta.validacoes;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoPacienteSemOutraConsultaMesmoDia implements ValidacaoAgendamentoConsulta {

	@Autowired
	private ConsultaRepository repository;

	public void validar(DadosAgendamentoConsulta dados) {
		LocalDateTime primeiroHorario = dados.horarioComData().withHour(7);
		LocalDateTime ultimoHorario = dados.horarioComData().withHour(18);

		boolean pacientePossuiOutraConsulta = repository.existsByPacienteIdentificadorAndHorarioBetween(dados.idDoPaciente(),
				primeiroHorario, ultimoHorario);
		if (pacientePossuiOutraConsulta) {
			throw new ValidacaoException("Paciente já possui uma consulta agendada nesse dia");
		}
	}

}
