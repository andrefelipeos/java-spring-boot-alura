package med.voll.api.endereco;

import jakarta.validation.constraints.Pattern;

public record DadosAtualizacaoEndereco(
		String logradouro,
		String bairro,
		@Pattern(regexp = "\\d{8}")
		String cep,
		String cidade,
		String uf,
		String complemento,
		String numero) {

}
