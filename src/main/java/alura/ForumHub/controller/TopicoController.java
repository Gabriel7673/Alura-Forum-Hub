package alura.ForumHub.controller;

import alura.ForumHub.domain.Topico;
import alura.ForumHub.dto.topico.DadosAtualizacaoTopico;
import alura.ForumHub.dto.topico.DadosCadastroTopico;
import alura.ForumHub.dto.topico.DadosDetalhamentoTopico;
import alura.ForumHub.dto.topico.DadosListagemTopico;
import alura.ForumHub.repository.TopicoRepository;
import alura.ForumHub.service.topico.AtualizacaoDeTopico;
import alura.ForumHub.service.topico.CriacaoDeTopico;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;    

    @Autowired
    private CriacaoDeTopico criacaoDeTopico;

    @Autowired
    private AtualizacaoDeTopico atualizacaoDeTopico;

    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(sort = {"dataCriacao"}) Pageable paginacao
    ) {
        var page = topicoRepository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @PostMapping
    @Transactional
    public ResponseEntity adicionar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder){
        
        var topico = criacaoDeTopico.criarTopico(dados);

        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados, @PathVariable Long id){
        Optional<Topico> topicoExiste = topicoRepository.findById(id);
        if (topicoExiste.isPresent()) {
            var topico = topicoExiste.get();
            topico = atualizacaoDeTopico.atualizarTopico(dados, id);
            return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
        }
        
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id){
        Optional<Topico> topico = topicoRepository.findById(id);
        if (topico.isPresent()) {
            topicoRepository.deleteById(id);    
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
