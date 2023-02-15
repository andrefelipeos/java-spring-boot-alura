package med.voll.api.paciente;

public record DadosListagemPaciente(Long identificador, String nome, String email, String cpf) {

	public DadosListagemPaciente(Paciente paciente) {
		this(paciente.getIdentificador(), paciente.getNome(), paciente.getEmail(), paciente.getCpf());
	}

}
