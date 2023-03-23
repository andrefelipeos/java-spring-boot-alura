package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
		Long identificador,
		Long idDoMedico,
		Long idDoPaciente,
		LocalDateTime horarioComData) {

	public DadosDetalhamentoConsulta(Consulta consulta) {
		this(consulta.getIdentificador(), consulta.getMedico().getIdentificador(),
				consulta.getPaciente().getIdentificador(), consulta.getHorario());
	}

}
