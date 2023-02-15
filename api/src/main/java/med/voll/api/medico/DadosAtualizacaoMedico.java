package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.endereco.DadosAtualizacaoEndereco;

public record DadosAtualizacaoMedico(
		@NotNull
		Long identificador,
		String nome,
		String telefone,
		@Valid
		DadosAtualizacaoEndereco endereco) {

}
