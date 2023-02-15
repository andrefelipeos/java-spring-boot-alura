package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosAtualizacaoEndereco;

public record DadosAtualizacaoPaciente(
		@NotNull
		Long identificador,
		String nome,
		String telefone,
		@Valid
		DadosAtualizacaoEndereco endereco) {

}
