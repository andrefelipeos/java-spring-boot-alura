package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DadosAgendamentoConsulta(
		Long idDoMedico,

		@NotNull
		Long idDoPaciente,
		
		@NotNull
		@Future
		LocalDateTime horarioComData) {

}
