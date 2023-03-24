package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;

@Component
public class ValidacaoMedicoIndisponivelHorario implements ValidacaoAgendamentoConsulta {

	@Autowired
	private ConsultaRepository repository;

	public void validar(DadosAgendamentoConsulta dados) {
		boolean medicoIndisponivel = repository.existsByMedicoIdentificadorAndHorario(dados.idDoMedico(), dados.horarioComData());
		if (medicoIndisponivel) {
			throw new ValidacaoException("Médico já possui consulta agendada no horário escolhido");
		}
	}

}
