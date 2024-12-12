package alura.ForumHub.dto.topico;

import jakarta.validation.constraints.NotNull;

public record DadosCadastroTopico(
        @NotNull
        String titulo,

        @NotNull
        String mensagem,

        @NotNull
        Long idAutor,

        @NotNull
        Long idCurso
) {
}
