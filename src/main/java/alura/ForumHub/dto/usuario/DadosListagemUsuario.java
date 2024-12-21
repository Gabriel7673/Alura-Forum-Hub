package alura.ForumHub.dto.usuario;

import alura.ForumHub.domain.Usuario;

public record DadosListagemUsuario(Long id, String nome) {
    public DadosListagemUsuario(Usuario autor) {
        this(autor.getId(), autor.getNome());
    }
}
