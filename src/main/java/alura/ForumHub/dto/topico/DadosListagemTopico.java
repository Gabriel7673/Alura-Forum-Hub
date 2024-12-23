package alura.ForumHub.dto.topico;

import alura.ForumHub.domain.Curso;
import alura.ForumHub.domain.Status;
import alura.ForumHub.domain.Topico;
import alura.ForumHub.domain.Usuario;
import alura.ForumHub.dto.curso.DadosListagemCurso;
import alura.ForumHub.dto.usuario.DadosListagemUsuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DadosListagemTopico(
        Long id,
        String titulo,
        String mensagem,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime data,
        Status status,
        DadosListagemUsuario autor,
        DadosListagemCurso curso
) {
    public DadosListagemTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                new DadosListagemUsuario(topico.getAutor()),
                new DadosListagemCurso(topico.getCurso()));
    }
}
