package alura.ForumHub.service.topico;

import java.time.LocalDateTime;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.ForumHub.domain.Status;
import alura.ForumHub.domain.Topico;
import alura.ForumHub.domain.validadores.ValidadorTitulosEMensagensIguais;
import alura.ForumHub.dto.topico.DadosCriacaoTopico;
import alura.ForumHub.repository.CursoRepository;
import alura.ForumHub.repository.TopicoRepository;
import alura.ForumHub.repository.UsuarioRepository;

@Service
public class CriacaoDeTopico {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private ValidadorTitulosEMensagensIguais validador;

    public Topico criarTopico(DadosCriacaoTopico dados){
        if (!usuarioRepository.existsById(dados.idAutor())) {
            throw new ValidationException("Usuário não existe");
        }
        if (!cursoRepository.existsById(dados.idAutor())) {
            throw new ValidationException("Curso não existe");
        }

        validador.validar(dados);
        
        var usuario = usuarioRepository.getReferenceById(dados.idAutor());
        var curso = cursoRepository.getReferenceById(dados.idCurso());

        var topico = new Topico(
                null,
                dados.titulo(),
                dados.mensagem(),
                LocalDateTime.now(),
                Status.ABERTO,
                usuario,
                curso,
                null
                );
        topicoRepository.save(topico);

        return topico;

    }

}
