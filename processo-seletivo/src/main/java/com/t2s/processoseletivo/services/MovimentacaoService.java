package com.t2s.processoseletivo.services;

import com.t2s.processoseletivo.services.DTOs.MovByClienteByTipo;
import com.t2s.processoseletivo.models.Movimentacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface MovimentacaoService extends JpaRepository<Movimentacao, Integer> {
        @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND c.num_container LIKE %?2% " +
            "AND m.tipo LIKE %?3% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?4,'YYYY-MM-DD') " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?5,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteConteinerTipoDtInicioDtFim(
            String cliente, String num_container, String tipo, String dt_hr_inicio, String dt_hr_fim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND c.num_container LIKE %?2% " +
            "AND m.tipo LIKE %?3% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?4,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteConteinerTipoDtInicio(
            String cliente, String conteiner, String tipo, String dtInicio
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND c.num_container LIKE %?2% " +
            "AND m.tipo LIKE %?3% " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?4,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteConteinerTipoDtFim(
            String cliente, String conteiner, String tipo, String dtFim
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND c.num_container LIKE %?2% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?3,'YYYY-MM-DD') " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?4,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteConteinerDtInicioDtFim(
            String cliente, String conteiner, String dtInicio, String dtFim
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND m.tipo LIKE %?2% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?3,'YYYY-MM-DD') " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?4,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteTipoDtInicioDtFim(
            String cliente, String tipo, String dtInicio, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND c.num_container LIKE %?2% " +
            "AND m.tipo LIKE %?3% ", nativeQuery = true)
    List<Movimentacao> findClienteConteinerTipo(
            String cliente, String conteiner, String tipo
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND c.num_container LIKE %?2% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteConteinerDtInicio(
            String cliente, String conteiner, String dtInicio
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND m.tipo LIKE %?2% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteTipoDtInicio(
            String cliente, String tipo, String dtInicio
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND c.num_container LIKE %?2% " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteConteinerDtFim(
            String cliente, String conteiner, String dtFim
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND m.tipo LIKE %?2% " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteTipoDtFim(
            String cliente, String tipo, String dtFim
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?2,'YYYY-MM-DD') " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteDtInicioDtFim(
            String cliente, String dtInicio, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND c.num_container LIKE %?2% ", nativeQuery = true)
    List<Movimentacao> findClienteConteiner(
            String cliente, String conteiner
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND m.tipo LIKE %?2% ", nativeQuery = true)
    List<Movimentacao> findClienteTipo(
            String cliente, String tipo
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?2,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteDtInicio(
            String cliente, String dtInicio
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?2,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findClienteDtFim(
            String cliente, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE LOWER(c.cliente) LIKE %?1% ", nativeQuery = true)
    List<Movimentacao> findCliente(
            String cliente
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE c.num_container LIKE %?1% " +
            "AND m.tipo LIKE %?2% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?3,'YYYY-MM-DD') " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?4,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findConteinerTipoDtInicioDtFim(
            String conteiner, String tipo, String dtInicio, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE c.num_container LIKE %?1% " +
            "AND m.tipo LIKE %?2% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findConteinerTipoDtInicio(
            String conteiner, String tipo, String dtInicio
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE c.num_container LIKE %?1% " +
            "AND m.tipo LIKE %?2% " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findConteinerTipoDtFim(
            String conteiner, String tipo, String dtFim
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE c.num_container LIKE %?1% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?2,'YYYY-MM-DD') " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findConteinerDtInicioDtFim(
            String conteiner, String dtInicio, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE c.num_container LIKE %?1% " +
            "AND m.tipo LIKE %?2% ", nativeQuery = true)
    List<Movimentacao> findConteinerTipo(
            String conteiner, String tipo
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE c.num_container LIKE %?1% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?2,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findConteinerDtInicio(
            String conteiner, String dtInicio
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE c.num_container LIKE %?1% " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?2,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findConteinerDtFim(
            String conteiner, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE c.num_container LIKE %?1% ", nativeQuery = true)
    List<Movimentacao> findConteiner(
            String conteiner
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE m.tipo LIKE %?1% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?2,'YYYY-MM-DD') " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?3,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findTipoDtInicioDtFim(
            String tipo, String dtInicio, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE m.tipo LIKE %?1% " +
            "AND m.dt_hr_inicio >= TO_TIMESTAMP(?2,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findTipoDtInicio(
            String tipo, String dtInicio
    );
    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE m.tipo LIKE %?1% " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?2,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findTipoDtFim(
            String tipo, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE m.tipo LIKE %?1% ", nativeQuery = true)
    List<Movimentacao> findTipo(
            String tipo
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE m.dt_hr_inicio >= TO_TIMESTAMP(?1,'YYYY-MM-DD') " +
            "AND m.dt_hr_fim <= TO_TIMESTAMP(?2,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findDtInicioDtFim(
            String dtInicio, String dtFim
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE m.dt_hr_inicio >= TO_TIMESTAMP(?1,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findDtInicio(
            String dtInicio
    );

    @Query(value = "SELECT DISTINCT(m.*) FROM MOVIMENTACOES as m,  CONTEINERS as c " +
            "WHERE m.dt_hr_fim <= TO_TIMESTAMP(?1,'YYYY-MM-DD') ", nativeQuery = true)
    List<Movimentacao> findDtFim(
            String dtFim
    );

    //=======================================================================
    //=======================================================================
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
