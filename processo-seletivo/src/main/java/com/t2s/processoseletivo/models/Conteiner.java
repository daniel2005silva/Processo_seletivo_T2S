package com.t2s.processoseletivo.models;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(
        name = "CONTEINERS",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"cliente", "num_container"})
        })
public class Conteiner {

    @GeneratedValue
    @Id
    private int id;

    //  @NotNull(message = "namnam é obrigatório")
    @Column(nullable = false, length = 30)
    private String cliente;

    @Column(nullable = false, length = 11)
    @Pattern(regexp = "[A-Z]{4}+[0-9]{7}$", message = "O padrão permitido é apenas 4 letras e 7 números!")
    private String num_container;

    @Column(nullable = false, length = 2, name = "TIPO")
    private int tipo;

    @Column(nullable = false, length = 5)
    private String status;

    @Column(nullable = false, length = 10)
    private String categoria;

    public Conteiner() {
    }

    public Conteiner(String cliente, String num_container, int tipo, String status, String categoria) {
        this.cliente = cliente;
        this.num_container = num_container;
        this.tipo = tipo;
        this.status = status;
        this.categoria = categoria;
    }

    public int getId(){
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getNum_container() {
        return num_container;
    }

    public void setNum_container(String num_container) {
        this.num_container = num_container;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
