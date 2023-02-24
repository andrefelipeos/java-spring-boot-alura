package med.voll.api.domain.paciente;

import med.voll.api.domain.endereco.Endereco;

public record DadosDetalhamentoPaciente(
		Long identificador,
		String nome,
		String email,
		String telefone,
		String cpf,
		Endereco endereco) {

	public DadosDetalhamentoPaciente(Paciente paciente) {
		this(
				paciente.getIdentificador(),
				paciente.getNome(),
				paciente.getEmail(),
				paciente.getTelefone(),
				paciente.getCpf(),
				paciente.getEndereco());
	}

}
