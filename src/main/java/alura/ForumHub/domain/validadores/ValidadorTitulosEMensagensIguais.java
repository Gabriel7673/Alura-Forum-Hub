package alura.ForumHub.domain.validadores;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import alura.ForumHub.domain.Topico;
import alura.ForumHub.dto.topico.DadosAtualizacaoTopico;
import alura.ForumHub.dto.topico.DadosCadastroTopico;
import alura.ForumHub.repository.TopicoRepository;
import jakarta.validation.ValidationException;

@Component
public class ValidadorTitulosEMensagensIguais {
    
    @Autowired
    private TopicoRepository topicoRepository;

    public void validar(DadosCadastroTopico dados){
        Optional<Topico> topico = topicoRepository.findTopicoComMesmoTituloEMensagem(dados.titulo(), dados.mensagem());

        if (topico.isPresent()) {
            throw new ValidationException("Já existe um tópico com estes título e mensagem");
        }
    }

    public void validar(DadosAtualizacaoTopico dados){
        Optional<Topico> topico = topicoRepository.findTopicoComMesmoTituloEMensagem(dados.titulo(), dados.mensagem());

        if (topico.isPresent()) {
            throw new ValidationException("Já existe um tópico com estes título e mensagem");
        }
    }
}
