package com.t2s.processoseletivo.services;

import com.t2s.processoseletivo.services.DTOs.MovByClienteByTipo;
import com.t2s.processoseletivo.models.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MovimentacaoService extends JpaRepository<Movimentacao, Integer> {
    @Query(value = "SELECT COUNT(id) FROM MOVIMENTACOES " +
            "WHERE dt_hr_inicio = ?1 " +
            " AND conteiner = ?2 ", nativeQuery = true)
    int findByDt_hr_inicioAndConteiner(Date dt_hr_inicio, int conteiner);

    @Query(value = "SELECT id FROM MOVIMENTACOES " +
            "WHERE dt_hr_inicio = ?1 " +
            " AND conteiner = ?2 ", nativeQuery = true)
    int returnIdByDt_hr_inicioAndConteiner(Date dt_hr_inicio, int conteiner);

    @Query(value = "SELECT " +
            "c.cliente as cliente, m.tipo as tipoDeMovimentacao, COUNT(m.id) as total " +
            "FROM movimentacoes m, conteiners c WHERE m.conteiner = c.id " +
            "GROUP BY c.cliente, m.tipo ORDER BY cliente, total DESC", nativeQuery = true)
    List<MovByClienteByTipo> tlMovimentacoesPorClienteEPorTipoMovimentacao();

}
