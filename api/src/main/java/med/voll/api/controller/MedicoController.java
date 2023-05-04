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
import med.voll.api.domain.medico.DadosAtualizacaoMedico;
import med.voll.api.domain.medico.DadosCadastroMedico;
import med.voll.api.domain.medico.DadosDetalhamentoMedico;
import med.voll.api.domain.medico.DadosListagemMedico;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;

@RestController
@RequestMapping("medicos")
@SecurityRequirement(name = "bearer-key")
public class MedicoController {

	@Autowired
	private MedicoRepository repository;

	@PostMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
		var medico = new Medico(dados);
		repository.save(medico);
		var uri = uriBuilder.path("/medicos/{identificador}").buildAndExpand(medico.getIdentificador()).toUri();
		return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
	}

	@GetMapping("/{identificador}")
	public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long identificador) {
		var medico = repository.getReferenceById(identificador);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@GetMapping
	public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome", "especialidade"}) Pageable paginacao) {
		var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
		return ResponseEntity.ok(page);
	}

	@PutMapping
	@Transactional
	public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
		var medico = repository.getReferenceById(dados.identificador());
		medico.atualizar(dados);
		return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
	}

	@DeleteMapping("/{identificador}")
	@Transactional
	public ResponseEntity<Void> remover(@PathVariable Long identificador) {
		var medico = repository.getReferenceById(identificador);
		medico.inativar();
		return ResponseEntity.noContent().build();
	}

}
