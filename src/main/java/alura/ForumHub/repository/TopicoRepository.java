package alura.ForumHub.repository;

import alura.ForumHub.domain.Topico;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    @Query("""
        SELECT t FROM Topico t WHERE 
        t.titulo = :titulo 
        AND 
        t.mensagem = :mensagem    
        """)
    Optional<Topico> findTopicoComMesmoTituloEMensagem(String titulo, String mensagem);
}
