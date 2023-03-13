package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsulta(
		Long identificador,
		Long idDoMedico,
		Long idDoPaciente,
		LocalDateTime horarioComData) {

}
