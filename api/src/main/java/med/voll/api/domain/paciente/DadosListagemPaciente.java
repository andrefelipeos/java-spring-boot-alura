package med.voll.api.domain.paciente;

public record DadosListagemPaciente(Long identificador, String nome, String email, String cpf) {

	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getIdentificador(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}

}
