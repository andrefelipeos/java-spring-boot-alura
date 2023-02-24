package med.voll.api.domain.medico;

public record DadosListagemMedico(Long identificador, String nome, String email, String crm, Especialidade especialidade) {

	public DadosListagemMedico(Medico medico) {
		this(medico.getIdentificador(), medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
	}

}
