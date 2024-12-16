package alura.ForumHub.service.topico;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import alura.ForumHub.domain.Topico;
import alura.ForumHub.dto.topico.DadosAtualizacaoTopico;
import alura.ForumHub.repository.CursoRepository;
import alura.ForumHub.repository.TopicoRepository;
import jakarta.validation.ValidationException;

@Service
public class AtualizacaoDeTopico {
    
    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico atualizarTopico(DadosAtualizacaoTopico dados, Long id){
        
        if (!topicoRepository.existsById(id)) {
            throw new ValidationException("Tópico não existe");
        }
        
        if (!cursoRepository.existsById(dados.idCurso())) {
            throw new ValidationException("Curso não existe");
        }

        var topico = topicoRepository.getReferenceById(id);

        var curso = cursoRepository.getReferenceById(dados.idCurso());

        topico.atualizar(dados.titulo(), dados.mensagem(), curso);

        return topico;
        

    }

}
