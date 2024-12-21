package alura.ForumHub.dto.curso;

import alura.ForumHub.domain.Curso;

public record DadosListagemCurso(Long id, String nome) {
    public DadosListagemCurso(Curso curso) {
        this(curso.getId(), curso.getNome());
    }
}
