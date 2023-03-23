package med.voll.api.domain.consulta.validacoes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;

@Component
public class ValidacaoMedicoAtivo implements ValidacaoAgendamentoConsulta {

	@Autowired
	private MedicoRepository repository;

	public void validar(DadosAgendamentoConsulta dados) {
		if (dados.idDoMedico() == null) return;

		boolean medicoAtivo = repository.findAtivoById(dados.idDoMedico());
		if (!medicoAtivo) {
			throw new ValidacaoException("Não é possível agendar uma consulta com um médico inativo");
		}
	}

}
