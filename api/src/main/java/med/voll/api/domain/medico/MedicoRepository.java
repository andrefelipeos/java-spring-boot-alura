package med.voll.api.domain.medico;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

	Page<Medico> findAllByAtivoTrue(Pageable paginacao);

	@Query("""
			SELECT m FROM Medico m
			WHERE m.ativo = 1
			AND m.especialidade = :especialidade
			AND m.identificador NOT IN(
				SELECT c.medico.identificador FROM Consulta c
				WHERE c.horario = :horarioComData
			)
			ORDER BY RAND()
			LIMIT 1
			""")
	Medico escolherMedicoAleatorioLivreNoHorario(Especialidade especialidade, LocalDateTime horarioComData);

	@Query("""
			SELECT m.ativo FROM Medico m
			WHERE m.id = :identificador
			""")
	boolean findAtivoById(Long identificador);

}
