package alura.ForumHub.dto.topico;

import alura.ForumHub.domain.Status;
import alura.ForumHub.domain.Topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        String titulo,
        String mensagem,
        LocalDateTime data,
        Status status,
        String autor,
        String curso
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getStatus(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}
