package med.voll.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.paciente.DadosAtualizacaoPaciente;
import med.voll.api.domain.paciente.DadosCadastroPaciente;
import med.voll.api.domain.paciente.DadosDetalhamentoPaciente;
import med.voll.api.domain.paciente.DadosListagemPaciente;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;

@RestController
@RequestMapping("pacientes")
@SecurityRequirement(name = "bearer-key")
public class PacienteController {

	@Autowired
	private PacienteRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoPaciente> cadastrar(@RequestBody @Valid DadosCadastroPaciente dados, UriComponentsBuilder uriBuilder) {
		var paciente = new Paciente(dados);
		repository.save(paciente);
		var uri = uriBuilder.path("/pacientes/{identificador}").buildAndExpand(paciente.getIdentificador()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoPaciente(paciente));
	}

	@GetMapping("/{identificador}")
	public ResponseEntity<DadosDetalhamentoPaciente> detalhar(@PathVariable Long identificador) {
		var paciente = repository.getReferenceById(identificador);
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemPaciente>> listar(@PageableDefault(size = 10, sort = {"nome", "cpf"}) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoPaciente> atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
		var paciente = repository.getReferenceById(dados.identificador());
		paciente.atualizar(dados);
		return ResponseEntity.ok(new DadosDetalhamentoPaciente(paciente));
	}

	@DeleteMapping("/{identificador}")
	@Transactional
	public ResponseEntity<Void> remover(@PathVariable Long identificador) {
		var paciente = repository.getReferenceById(identificador);
		paciente.inativar();
		return ResponseEntity.noContent().build();
	}

}
