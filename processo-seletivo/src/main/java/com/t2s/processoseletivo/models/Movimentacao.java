package com.t2s.processoseletivo.models;

import org.hibernate.annotations.Check;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(
        name = "MOVIMENTACOES",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"dt_hr_inicio", "conteiner"}),
                @UniqueConstraint(columnNames = {"dt_hr_fim", "conteiner"})
        })
public class Movimentacao {

    @GeneratedValue
    @Id
    private int id;

    @Column(nullable = false, length = 15)
    private String tipo;

    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date dt_hr_inicio;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dt_hr_fim;

    @ManyToOne
    @JoinColumn(name = "conteiner", nullable = false)
    private Conteiner conteiner;

    public Movimentacao() {
    }

    public Movimentacao(String tipo, Date dt_hr_inicio, Date dt_hr_fim, Conteiner conteiner) {
        this.tipo = tipo;
        this.dt_hr_inicio = dt_hr_inicio;
        this.dt_hr_fim = dt_hr_fim;
        this.conteiner = conteiner;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getDt_hr_inicio() {
        return dt_hr_inicio;
    }

    public void setDt_hr_inicio(Date dt_hr_inicio) {
        this.dt_hr_inicio = dt_hr_inicio;
    }

    public Date getDt_hr_fim() {
        return dt_hr_fim;
    }

    public void setDt_hr_fim(Date dt_hr_fim) {
        this.dt_hr_fim = dt_hr_fim;
    }

    public Conteiner getConteiner() {
        return conteiner;
    }

    public void setConteiner(Conteiner conteiner) {
        this.conteiner = conteiner;
    }
}
