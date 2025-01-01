package alura.ForumHub.service.topico;

import alura.ForumHub.dto.topico.DadosCriacaoTopico;
import alura.ForumHub.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.ForumHub.domain.Topico;
import alura.ForumHub.domain.validadores.ValidadorTitulosEMensagensIguais;
import alura.ForumHub.repository.CursoRepository;
import alura.ForumHub.repository.TopicoRepository;
import jakarta.validation.ValidationException;

@Service
public class AtualizacaoDeTopico {
    
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ValidadorTitulosEMensagensIguais validador;

    public Topico atualizarTopico(DadosCriacaoTopico dados, Long id){
        
        if (!topicoRepository.existsById(id)) {
            throw new ValidationException("Tópico não existe");
        }

        if (!usuarioRepository.existsById(dados.idAutor())){
            throw new ValidationException("Usuário não existe");
        }

        if (!cursoRepository.existsById(dados.idCurso())) {
            throw new ValidationException("Curso não existe");
        }

        validador.validar(dados);

        var topico = topicoRepository.getReferenceById(id);
        var usuario = usuarioRepository.getReferenceById(dados.idAutor());
        var curso = cursoRepository.getReferenceById(dados.idCurso());

        topico.atualizar(dados.titulo(), dados.mensagem(), usuario, curso);

        return topico;
        

    }

}
