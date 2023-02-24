package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.endereco.DadosAtualizacaoEndereco;

public record DadosAtualizacaoPaciente(
		@NotNull
		Long identificador,
		String nome,
		String telefone,
		@Valid
		DadosAtualizacaoEndereco endereco) {

}
