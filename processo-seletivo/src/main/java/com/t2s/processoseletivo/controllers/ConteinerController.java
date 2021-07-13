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

    @GetMapping(path = "/filtrar")
    public ResponseEntity<List<Conteiner>> getAllFiltrado(
            @RequestParam(value = "cliente", required = false) String cliente,
            @RequestParam(value = "num_container", required = false) String num_container,
            @RequestParam(value = "tipo", required = false) String tipo,
            @RequestParam(value = "status", required = false) String status,
            @RequestParam(value = "categoria", required = false) String categoria
    ){
        List<Conteiner> conteiners = new ArrayList<>();
        if(cliente != null && num_container != null && tipo != null && status != null && categoria != null){
            conteiners = conteinerService.findByClienteNum_containerTipoStatusCategoria(
                    cliente.toLowerCase(), num_container.toUpperCase(), tipo,
                    status.toUpperCase(), categoria.toUpperCase()
            );
        }else if(cliente != null && num_container != null && tipo != null && status != null){
            conteiners = conteinerService.findByClienteNum_containerTipoStatus(
                    cliente.toLowerCase(), num_container.toUpperCase(), tipo, status.toUpperCase()
            );
        }else if(cliente != null && num_container != null && tipo != null && categoria != null){
            conteiners = conteinerService.findByClienteNum_containerTipoCategoria(
                    cliente.toLowerCase(), num_container.toUpperCase(), tipo, categoria.toUpperCase()
            );
        }else if(cliente != null && num_container != null && status != null && categoria != null){
            conteiners = conteinerService.findByClienteNum_containerStatusCategoria(
                    cliente.toLowerCase(), num_container.toUpperCase(),
                    status.toUpperCase(), categoria.toUpperCase()
            );
        }else if(cliente != null && tipo != null && status != null && categoria != null){
            conteiners = conteinerService.findByClienteTipoStatusCategoria(
                    cliente.toLowerCase(), tipo, status.toUpperCase(), categoria.toUpperCase()
            );
        }else if(cliente != null && num_container != null && tipo != null){
            conteiners = conteinerService.findByClienteNum_containerTipo(
                    cliente.toLowerCase(), num_container.toUpperCase(), tipo
            );
        }else if(cliente != null && num_container != null && status != null){
            conteiners = conteinerService.findByClienteNum_containerStatus(
                    cliente.toLowerCase(), num_container.toUpperCase(), status.toUpperCase()
            );
        }else if(cliente != null && tipo != null && status != null){
            conteiners = conteinerService.findByClienteTipoStatus(
                    cliente.toLowerCase(), tipo, status.toUpperCase()
            );
        }else if(cliente != null && num_container != null && categoria != null){
            conteiners = conteinerService.findByClienteNum_containerCategoria(
                    cliente.toLowerCase(), num_container.toUpperCase(), categoria.toUpperCase()
            );
        }else if(cliente != null && tipo != null && categoria != null){
            conteiners = conteinerService.findByClienteTipoCategoria(
                    cliente.toLowerCase(), tipo, categoria.toUpperCase()
            );
        }else if(cliente != null && status != null && categoria != null){
            conteiners = conteinerService.findByClienteStatusCategoria(
                    cliente.toLowerCase(), status.toUpperCase(), categoria.toUpperCase()
            );
        }else if(cliente != null && num_container != null){
            conteiners = conteinerService.findByClienteNumContainer(
                    cliente.toLowerCase(), num_container.toUpperCase()
            );
        }else if(cliente != null && tipo != null){
            conteiners = conteinerService.findByClienteTipo( cliente.toLowerCase(), tipo );
        }else if(cliente != null && status != null){
            conteiners = conteinerService.findByClienteStatus(
                    cliente.toLowerCase(), status.toUpperCase()
            );
        }else if(cliente != null && categoria != null){
            conteiners = conteinerService.findByClienteCategoria(
                    cliente.toLowerCase(), categoria.toUpperCase()
            );
        }else if(cliente != null){
            conteiners = conteinerService.findCliente( cliente.toLowerCase() );
        }else if(num_container != null && tipo != null && status != null && categoria != null){
            conteiners = conteinerService.findByNum_containerTipoStatusCategoria(
                    num_container.toUpperCase(), tipo, status.toUpperCase(), categoria.toUpperCase()
            );
        }else if(num_container != null && tipo != null && status != null){
            conteiners = conteinerService.findByNum_containerTipoStatus(
                    num_container.toUpperCase(), tipo, status.toUpperCase()
            );
        }else if(num_container != null && tipo != null && categoria != null){
            conteiners = conteinerService.findByNum_containerTipoCategoria(
                    num_container.toUpperCase(), tipo, categoria.toUpperCase()
            );
        }else if(num_container != null && status != null && categoria != null){
            conteiners = conteinerService.findByNum_containerStatusCategoria(
                    num_container.toUpperCase(), status.toUpperCase(), categoria.toUpperCase()
            );
        }else if(num_container != null && tipo != null){
            conteiners = conteinerService.findByNum_containerTipo(
                    num_container.toUpperCase(), tipo
            );
        }else if(num_container != null && status != null){
            conteiners = conteinerService.findByNum_containerStatus(
                    num_container.toUpperCase(), status.toUpperCase()
            );
        }else if(num_container != null && categoria != null){
            conteiners = conteinerService.findByNum_containerCategoria(
                    num_container.toUpperCase(), categoria.toUpperCase()
            );
        }else if(num_container != null){
            conteiners = conteinerService.findNum_container( num_container.toUpperCase() );
        }else if(tipo != null && status != null && categoria != null){
            conteiners = conteinerService.findByTipoStatusCategoria(
                    tipo, status.toUpperCase(), categoria.toUpperCase()
            );
        }else if(tipo != null && status != null){
            conteiners = conteinerService.findByTipoStatus(
                    tipo, status.toUpperCase()
            );
        }else if(tipo != null && categoria != null){
            conteiners = conteinerService.findByTipoCategoria( tipo, categoria.toUpperCase());
        }else if(tipo != null){
            conteiners = conteinerService.findTipo( tipo );
        }else if(status != null && categoria != null){
            conteiners = conteinerService.findByStatusCategoria(
                    status.toUpperCase(), categoria.toUpperCase()
            );
        }else if(status != null){
            conteiners = conteinerService.findStatus( status.toUpperCase() );
        }else if(categoria != null){
            conteiners = conteinerService.findCategoria( categoria.toUpperCase() );
        }else{
            conteiners = conteinerService.findAll();
        }

        return new ResponseEntity<>(conteiners, HttpStatus.OK);
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
