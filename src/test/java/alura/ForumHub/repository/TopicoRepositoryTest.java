package alura.ForumHub.repository;

import alura.ForumHub.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class TopicoRepositoryTest {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("Deveria retornar null caso o tópico testado tenha título e mensagem diferentes do tópico cadastrado")
    void findTopicoComMesmoTituloEMensagemCenario1() {

        var usuario = cadastrarUsuario("User1", "user1@email.com", "$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
        var curso = cadastrarCurso("Curso", Categoria.JAVA, "16h");
        cadastrarTopico("Titulo", "Mensagem", LocalDateTime.now(), Status.ABERTO, usuario, curso);

        var topico = topicoRepository.findTopicoComMesmoTituloEMensagem("Titulo1", "Mensagem1");
        assertThat(topico).isEmpty();
    }

    @Test
    @DisplayName("Deveria retornar null caso o tópico testado tenha título diferente do tópico cadastrado")
    void findTopicoComMesmoTituloEMensagemCenario2() {

        var usuario = cadastrarUsuario("User1", "user1@email.com", "$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
        var curso = cadastrarCurso("Curso", Categoria.JAVA, "16h");
        cadastrarTopico("Titulo", "Mensagem", LocalDateTime.now(), Status.ABERTO, usuario, curso);

        var topico = topicoRepository.findTopicoComMesmoTituloEMensagem("Titulo1", "Mensagem");
        assertThat(topico).isEmpty();
    }

    @Test
    @DisplayName("Deveria retornar null caso o tópico testado tenha mensagem diferente do tópico cadastrado")
    void findTopicoComMesmoTituloEMensagemCenario3() {

        var usuario = cadastrarUsuario("User1", "user1@email.com", "$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
        var curso = cadastrarCurso("Curso", Categoria.JAVA, "16h");
        cadastrarTopico("Titulo", "Mensagem", LocalDateTime.now(), Status.ABERTO, usuario, curso);

        var topico = topicoRepository.findTopicoComMesmoTituloEMensagem("Titulo", "Mensagem1");
        assertThat(topico).isEmpty();
    }

    @Test
    @DisplayName("Deveria retornar o tópico cadastrado caso o tópico testado tenha título e mensagem iguais ao tópico cadastrado")
    void findTopicoComMesmoTituloEMensagemCenario4() {

        var usuario = cadastrarUsuario("User1", "user1@email.com", "$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.");
        var curso = cadastrarCurso("Curso", Categoria.JAVA, "16h");
        cadastrarTopico("Titulo", "Mensagem", LocalDateTime.now(), Status.ABERTO, usuario, curso);

        var topico = topicoRepository.findTopicoComMesmoTituloEMensagem("Titulo", "Mensagem");
        assertThat(topico).isNotEmpty();
    }



    private void cadastrarTopico(String titulo, String mensagem, LocalDateTime data, Status status, Usuario usuario, Curso curso){
        em.persist(new Topico(null, titulo, mensagem, data, status, usuario, curso, null));
    }

    private Usuario cadastrarUsuario(String nome, String email, String senha){
        var usuario = new Usuario(null, nome, email, senha);
        em.persist(usuario);
        return usuario;
    }

    private Curso cadastrarCurso(String nome, Categoria categoria, String cargaHoraria){
        var curso = new Curso(null, nome, categoria, cargaHoraria);
        em.persist(curso);
        return curso;
    }
}