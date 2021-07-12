package com.t2s.processoseletivo.controllers;

import com.t2s.processoseletivo.models.Conteiner;
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
@RequestMapping(path = "/conteiner")
public class ConteinerController {

    @Autowired
    ConteinerService conteinerService;

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler
    ErrorMessage exceptionHandler(ValidationException e){
        return new ErrorMessage("400", e.getMessage());
    }

    @PostMapping
    public ResponseEntity<Conteiner> create(@RequestBody Conteiner conteiner) throws ValidationException {
        verificarValores(conteiner);
        conteiner = padronizarConteiner(conteiner);
        if(conteinerService.findByClienteAndNum_container(conteiner.getCliente(), conteiner.getNum_container()) > 0){
            throw new ValidationException("Erro na inserção dos dados: " +
                    "Provavelmente esse número de conteiner para esse cliente já exista. ");
        }
        conteinerService.save(conteiner);
        return new ResponseEntity<>(conteiner, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Conteiner>> getAll(){
        List<Conteiner> conteiners = new ArrayList<>();
        conteiners = conteinerService.findAll();
        return new ResponseEntity<>(conteiners, HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Optional<Conteiner>> getById(@PathVariable Integer id){
        Optional<Conteiner> conteiner;
        try{
            conteiner = conteinerService.findById(id);
            return new ResponseEntity<Optional<Conteiner>>(conteiner, HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return  new ResponseEntity<Optional<Conteiner>>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Optional<Conteiner>> deleteById(@PathVariable Integer id){
        try{
            conteinerService.deleteById(id);
            return  new ResponseEntity<Optional<Conteiner>>(HttpStatus.OK);
        }catch (NoSuchElementException nsee){
            return new ResponseEntity<Optional<Conteiner>>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Conteiner> update(@PathVariable Integer id, @RequestBody Conteiner newConteiner)  throws ValidationException {

            verificarValores(newConteiner);
            if(conteinerService.returnIdByClienteAndNum_container(newConteiner.getCliente(), newConteiner.getNum_container()) != id){
                throw new ValidationException("Erro na inserção dos dados: " +
                        "Provavelmente esse número de conteiner para esse cliente já exista. ");
            }
                return conteinerService.findById(id)
                        .map(conteiner -> {
                            conteiner.setCategoria(newConteiner.getCategoria().toUpperCase());
                            conteiner.setCliente(newConteiner.getCliente());
                            conteiner.setNum_container(newConteiner.getNum_container());
                            conteiner.setStatus(newConteiner.getStatus().toUpperCase());
                            conteiner.setTipo(newConteiner.getTipo());
                            Conteiner containerUpdate = conteinerService.save(conteiner);
                            return  ResponseEntity.ok().body(containerUpdate);
                        }).orElse(ResponseEntity.notFound().build());


    }

    @GetMapping(path = "/totalCategoria/{categoria}")
    public int getTotalCategoria(@PathVariable String categoria){
        int total = conteinerService.tlCategoria(categoria);
        return total;
    }

    public void verificarValores(Conteiner conteiner) throws ValidationException {
        if(conteiner.getTipo() != 20 && conteiner.getTipo() != 40){
            throw new ValidationException("Erro na inserção dos dados: " +
                    "Tipo deve ser 20 ou 40.");
        }else if(!conteiner.getStatus().equalsIgnoreCase("CHEIO") && !conteiner.getStatus().equalsIgnoreCase("VAZIO")){
            throw new ValidationException("Erro na inserção dos dados: " +
                    "Status deve ser CHEIO ou VAZIO.");
        }else if(!conteiner.getCategoria().equalsIgnoreCase("IMPORTAÇÃO") && !conteiner.getCategoria().equalsIgnoreCase("EXPORTAÇÃO")){
            throw new ValidationException("Erro na inserção dos dados: " +
                    "Categoria deve ser IMPORTAÇÃO ou EXPORTAÇÃO.");
        }
    }

    public Conteiner padronizarConteiner(Conteiner conteiner){
        conteiner.setStatus(conteiner.getStatus().toUpperCase());
        conteiner.setCategoria(conteiner.getCategoria().toUpperCase());
        return conteiner;
    }
}
