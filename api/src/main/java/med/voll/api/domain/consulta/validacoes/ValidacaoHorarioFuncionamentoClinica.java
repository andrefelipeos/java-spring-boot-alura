package med.voll.api.domain.consulta.validacoes;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoHorarioFuncionamentoClinica implements ValidacaoAgendamentoConsulta {

	public void validar(DadosAgendamentoConsulta dados) {
		LocalDateTime horarioConsulta = dados.horarioComData();

		boolean horarioNoDomingo = horarioConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		boolean horarioAntesAbertura = horarioConsulta.getHour() < 7;
		boolean horarioAposEncerramento = horarioConsulta.getHour() > 18;

		if (horarioNoDomingo || horarioAntesAbertura || horarioAposEncerramento) {
			throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
		}
	}

}
