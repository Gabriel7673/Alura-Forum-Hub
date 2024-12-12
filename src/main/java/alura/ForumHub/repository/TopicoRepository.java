package alura.ForumHub.repository;

import alura.ForumHub.domain.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
}
