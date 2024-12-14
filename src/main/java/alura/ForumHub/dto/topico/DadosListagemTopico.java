package alura.ForumHub.dto.topico;

import alura.ForumHub.domain.Curso;
import alura.ForumHub.domain.Status;
import alura.ForumHub.domain.Topico;
import alura.ForumHub.domain.Usuario;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        LocalDateTime data,
        Status status,
        Usuario autor,
        Curso curso
) {
    public DadosListagemTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor(),
                topico.getCurso());
    }
}
