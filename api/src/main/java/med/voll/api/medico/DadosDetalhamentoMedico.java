package med.voll.api.medico;

import med.voll.api.endereco.Endereco;

public record DadosDetalhamentoMedico(
		Long identificador,
		String nome,
		String email,
		String telefone,
		String crm,
		Especialidade especialidade,
		Endereco endereco) {

	public DadosDetalhamentoMedico(Medico medico) {
		this(
				medico.getIdentificador(),
				medico.getNome(),
				medico.getEmail(),
				medico.getTelefone(),
				medico.getCrm(),
				medico.getEspecialidade(),
				medico.getEndereco());
	}

}
