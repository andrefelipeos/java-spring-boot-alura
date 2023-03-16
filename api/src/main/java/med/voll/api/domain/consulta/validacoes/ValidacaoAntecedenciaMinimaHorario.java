package med.voll.api.domain.consulta.validacoes;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoAntecedenciaMinimaHorario implements ValidacaoAgendamentoConsulta {

	public void validar(DadosAgendamentoConsulta dados) {
		LocalDateTime horarioConsulta = dados.horarioComData();

		LocalDateTime horarioAtual = LocalDateTime.now();
		Long diferencaEmMinutos = Duration.between(horarioAtual, horarioConsulta).toMinutes();

		if (diferencaEmMinutos < 30) {
			throw new ValidacaoException("Consulta deve ser marcada com pelo menos 30 minutos de antecedência");
		}
	}

}
