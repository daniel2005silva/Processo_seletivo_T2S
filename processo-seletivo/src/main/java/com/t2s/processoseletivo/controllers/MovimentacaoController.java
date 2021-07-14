package com.t2s.processoseletivo.controllers;

import com.t2s.processoseletivo.services.DTOs.MovByClienteByTipo;
import com.t2s.processoseletivo.models.Movimentacao;
import com.t2s.processoseletivo.services.ConteinerService;
import com.t2s.processoseletivo.services.MovimentacaoService;
import com.t2s.processoseletivo.util.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping(path = "/movimentacao")
public class MovimentacaoController {

    @Autowired
    MovimentacaoService movimentacaoService;

    @Autowired
    ConteinerService conteinerService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(ValidationException e){
        return new ErrorMessage("400", e.getMessage());
    }

    @PostMapping
    public ResponseEntity<Movimentacao> create(@RequestBody Movimentacao movimentacao) throws ValidationException {

        verificarValores(movimentacao);
        movimentacao.setTipo(movimentacao.getTipo().toUpperCase());
        if(movimentacaoService.findByDt_hr_inicioAndConteiner(movimentacao.getDt_hr_inicio(), movimentacao.getConteiner().getId()) > 0){
            throw new ValidationException("Erro na inserção dos dados: " +
                    "Data e hora de início para este conteiner já existe. ");
        }
        movimentacaoService.save(movimentacao);
        return  new ResponseEntity<>(movimentacao, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Movimentacao>> getAll(){
        List<Movimentacao> movimentacoes = new ArrayList<>();
        movimentacoes = movimentacaoService.findAll();
        return new ResponseEntity<>(movimentacoes, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Movimentacao>> getById(@PathVariable Integer id){
        Optional<Movimentacao> movimentacao;
        try{
            movimentacao = movimentacaoService.findById(id);
            return new ResponseEntity<Optional<Movimentacao>>(movimentacao, HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return  new ResponseEntity<Optional<Movimentacao>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Movimentacao>> deleteById(@PathVariable Integer id){
        try{
            movimentacaoService.deleteById(id);
            return  new ResponseEntity<Optional<Movimentacao>>(HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<Movimentacao>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Movimentacao> update(@PathVariable Integer id, @RequestBody Movimentacao newMovimentacao) throws ValidationException {

        verificarValores(newMovimentacao);
        if(movimentacaoService.returnIdByDt_hr_inicioAndConteiner(newMovimentacao.getDt_hr_inicio(), newMovimentacao.getConteiner().getId()) != id){
            throw new ValidationException("Erro na inserção dos dados: " +
                    "Provavelmente essa movimentação com essa data e hora de início e conteiner já exista. ");
        }
        return movimentacaoService.findById(id)
                .map(movimentacao -> {
                    movimentacao.setConteiner(newMovimentacao.getConteiner());
                    movimentacao.setDt_hr_fim(newMovimentacao.getDt_hr_fim());
                    movimentacao.setDt_hr_inicio(newMovimentacao.getDt_hr_inicio());
                    movimentacao.setTipo(newMovimentacao.getTipo().toUpperCase());
                    Movimentacao containerUpdate = movimentacaoService.save(movimentacao);
                    return  ResponseEntity.ok().body(containerUpdate);
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = "/porClienteEporTipoMovimentacao")
    public List<MovByClienteByTipo> getAllMovimentacoesPorClienteEporTipoMovimentacao(){
        List<MovByClienteByTipo> movimentacoes = new ArrayList<>();
        movimentacoes = movimentacaoService.tlMovimentacoesPorClienteEPorTipoMovimentacao();
        return movimentacoes;
    }

    @GetMapping(path = "/porClienteEporTipoMovimentacao/filtrar")
    public List<MovByClienteByTipo> getMovimentacoesClienteTipoMovimentacaoFiltrado(
            @RequestParam(value = "cliente", required = false) String cliente,
            @RequestParam(value = "tipo", required = false) String tipo
    ){
        List<MovByClienteByTipo> movimentacoes = new ArrayList<>();
        if(cliente != null && tipo != null){
            movimentacoes = movimentacaoService.porClienteTipoMovimentacao(
                    cliente.toLowerCase(),tipo.toUpperCase());
        }else if(cliente != null){
            movimentacoes = movimentacaoService.porCliente(cliente.toLowerCase());
        }else if(tipo != null){
            movimentacoes = movimentacaoService.porTipoMovimentacao(tipo.toUpperCase());
        }else{
            movimentacoes = movimentacaoService.tlMovimentacoesPorClienteEPorTipoMovimentacao();
        }
        return movimentacoes;
    }

    @GetMapping(path = "/filtrar")
    public ResponseEntity<List<Movimentacao>> getAllFiltrado(
            @RequestParam(value = "cliente", required = false) String cliente,
            @RequestParam(value = "conteiner", required = false) String conteiner,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "dtInicio", required = false) String dtInicio,
            @RequestParam(value = "dtFim", required = false) String dtFim
    ){
        List<Movimentacao> movimentacoes = new ArrayList<>();

        if(cliente != null && conteiner != null && tipo != null && dtInicio != null && dtFim != null){
            movimentacoes = movimentacaoService.findClienteConteinerTipoDtInicioDtFim(
                    cliente.toLowerCase(), conteiner.toUpperCase(), tipo.toUpperCase(), dtInicio, dtFim
            );
        }else if(cliente != null && conteiner != null && tipo != null && dtInicio != null){
            movimentacoes = movimentacaoService.findClienteConteinerTipoDtInicio(
                    cliente.toLowerCase(), conteiner.toUpperCase(), tipo.toUpperCase(), dtInicio
            );
        }else if(cliente != null && conteiner != null && tipo != null && dtFim != null){
            movimentacoes = movimentacaoService.findClienteConteinerTipoDtFim(
                    cliente.toLowerCase(), conteiner.toUpperCase(), tipo.toUpperCase(), dtFim
            );
        }else if(cliente != null && conteiner != null && dtInicio != null && dtFim != null){
            movimentacoes = movimentacaoService.findClienteConteinerDtInicioDtFim(
                    cliente.toLowerCase(), conteiner.toUpperCase(), dtInicio, dtFim
            );
        }else if(cliente != null && tipo != null && dtInicio != null && dtFim != null){
            movimentacoes = movimentacaoService.findClienteTipoDtInicioDtFim(
                    cliente.toLowerCase(), tipo.toUpperCase(), dtInicio, dtFim
            );
        }else if(cliente != null && conteiner != null && tipo != null){
            movimentacoes = movimentacaoService.findClienteConteinerTipo(
                    cliente.toLowerCase(), conteiner.toUpperCase(), tipo.toUpperCase()
            );
        }else if(cliente != null && conteiner != null && dtInicio != null){
            movimentacoes = movimentacaoService.findClienteConteinerDtInicio(
                    cliente.toLowerCase(), conteiner.toUpperCase(), dtInicio
            );
        }else if(cliente != null && tipo != null && dtInicio != null){
            movimentacoes = movimentacaoService.findClienteTipoDtInicio(
                    cliente.toLowerCase(), tipo.toUpperCase(), dtInicio
            );
        }else if(cliente != null && conteiner != null & dtFim != null){
            movimentacoes = movimentacaoService.findClienteConteinerDtFim(
                    cliente.toLowerCase(), conteiner.toUpperCase(), dtFim
            );
        }else if(cliente != null && tipo != null && dtFim != null){
            movimentacoes = movimentacaoService.findClienteTipoDtFim(
                    cliente.toLowerCase(), tipo.toUpperCase(), dtFim
            );
        }else if(cliente != null && dtInicio != null && dtFim != null){
            movimentacoes = movimentacaoService.findClienteDtInicioDtFim(
                    cliente.toLowerCase(), dtInicio, dtFim
            );
        }else if(cliente != null && conteiner != null){
            movimentacoes = movimentacaoService.findClienteConteiner(
                    cliente.toLowerCase(), conteiner.toUpperCase()
            );
        }else if(cliente != null && tipo != null){
            movimentacoes = movimentacaoService.findClienteTipo(
                    cliente.toLowerCase(), tipo.toUpperCase()
            );
        }else if(cliente != null && dtInicio != null){
            movimentacoes = movimentacaoService.findClienteDtInicio(
                    cliente.toLowerCase(), dtInicio
            );
        }else if(cliente != null && dtFim != null){
            movimentacoes = movimentacaoService.findClienteDtFim(
                    cliente.toLowerCase(), dtFim
            );
        }else if(cliente != null){
            movimentacoes = movimentacaoService.findCliente(
                    cliente.toLowerCase()
            );
        }else if(conteiner != null && tipo != null && dtInicio != null && dtFim != null){
            movimentacoes = movimentacaoService.findConteinerTipoDtInicioDtFim(
                    conteiner.toUpperCase(), tipo.toUpperCase(), dtInicio, dtFim
            );
        }else if(conteiner != null && tipo != null && dtInicio != null){
            movimentacoes = movimentacaoService.findConteinerTipoDtInicio(
                    conteiner.toUpperCase(), tipo.toUpperCase(), dtInicio
            );
        }else if(conteiner != null && tipo != null && dtFim != null){
            movimentacoes = movimentacaoService.findConteinerTipoDtFim(
                    conteiner.toUpperCase(), tipo.toUpperCase(), dtFim
            );
        }else if(conteiner != null && dtInicio != null && dtFim != null){
            movimentacoes = movimentacaoService.findConteinerDtInicioDtFim(
                    conteiner.toUpperCase(), dtInicio, dtFim
            );
        }else if(conteiner != null && tipo != null){
            movimentacoes = movimentacaoService.findConteinerTipo(
                    conteiner.toUpperCase(), tipo.toUpperCase()
            );
        }else if(conteiner != null && dtInicio != null){
            movimentacoes = movimentacaoService.findConteinerDtInicio(
                    conteiner.toUpperCase(), dtInicio
            );
        }else if(conteiner != null && dtFim != null){
            movimentacoes = movimentacaoService.findConteinerDtFim(
                    conteiner.toUpperCase(), dtFim
            );
        }else if(conteiner != null){
            movimentacoes = movimentacaoService.findConteiner(
                    conteiner.toUpperCase()
            );
        }else if(tipo != null && dtInicio != null && dtFim != null){
            movimentacoes = movimentacaoService.findTipoDtInicioDtFim(
                    tipo.toUpperCase(), dtInicio, dtFim
            );
        }else if(tipo != null && dtInicio != null){
            movimentacoes = movimentacaoService.findTipoDtInicio(
                    tipo.toUpperCase(), dtInicio
            );
        }else if(tipo != null && dtFim != null){
            movimentacoes = movimentacaoService.findTipoDtFim(
                   tipo.toUpperCase(), dtFim
            );
        }else if(tipo != null){
            movimentacoes = movimentacaoService.findTipo(
                    tipo.toUpperCase()
            );
        }else if(dtInicio != null && dtFim != null){
            movimentacoes = movimentacaoService.findDtInicioDtFim(
                    dtInicio, dtFim
            );
        }else if(dtInicio != null){
            movimentacoes = movimentacaoService.findDtInicio(
                    dtInicio
            );
        }else if(dtFim != null){
            movimentacoes = movimentacaoService.findDtFim(
                   dtFim
            );
        }else{
            movimentacoes = movimentacaoService.findAll();
        }

        return new ResponseEntity<>(movimentacoes, HttpStatus.OK);
    }

    public void verificarValores(Movimentacao movimentacao) throws ValidationException {
        if(        !movimentacao.getTipo().equalsIgnoreCase("EMBARQUE")
                && !movimentacao.getTipo().equalsIgnoreCase("DESCARGA")
                && !movimentacao.getTipo().equalsIgnoreCase("GATE IN")
                && !movimentacao.getTipo().equalsIgnoreCase("GATE OUT")
                && !movimentacao.getTipo().equalsIgnoreCase("POSICIONAMENTO")
                && !movimentacao.getTipo().equalsIgnoreCase("PILHA")
                && !movimentacao.getTipo().equalsIgnoreCase("PESAGEM")
                && !movimentacao.getTipo().equalsIgnoreCase("SCANNER") ){

            throw new ValidationException("Erro na inserção dos dados: " +
                    "Tipo deve ser EMBARQUE, ou DESCARGA, ou GATE IN, ou GATE OUT, ou" +
                    " POSICIONAMENTO, ou PILHA, ou PESAGEM, ou SCANNER.");
        }else if(!conteinerService.findById(movimentacao.getConteiner().getId()).isPresent()){
            throw new ValidationException("Erro na inserção dos dados: " +
                    "Conteiner não existe, adicione um conteiner existente.");
        }
    }
}
