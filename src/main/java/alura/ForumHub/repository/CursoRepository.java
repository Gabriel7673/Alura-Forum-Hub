package alura.ForumHub.repository;

import alura.ForumHub.domain.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
