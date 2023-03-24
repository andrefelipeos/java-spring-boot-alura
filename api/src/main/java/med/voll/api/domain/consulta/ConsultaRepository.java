package med.voll.api.domain.consulta;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

	boolean existsByMedicoIdentificadorAndHorario(Long idDoMedico, LocalDateTime horarioComData);

}
