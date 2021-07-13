package com.t2s.processoseletivo.services;

import com.t2s.processoseletivo.models.Conteiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConteinerService extends JpaRepository<Conteiner, Integer> {

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND num_container LIKE %?2% " +
            "AND CAST(tipo AS TEXT) LIKE %?3% " +
            "AND status LIKE %?4% " +
            "AND categoria LIKE %?5% ", nativeQuery = true)
    List<Conteiner> findByClienteNum_containerTipoStatusCategoria(String cliente, String num_container, String tipo, String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND num_container LIKE %?2% " +
            "AND CAST(tipo AS TEXT) LIKE %?3% " +
            "AND status LIKE %?4% ", nativeQuery = true)
    List<Conteiner> findByClienteNum_containerTipoStatus(String cliente, String num_container, String tipo, String status);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND num_container LIKE %?2% " +
            "AND CAST(tipo AS TEXT) LIKE %?3% " +
            "AND categoria LIKE %?4% ", nativeQuery = true)
    List<Conteiner> findByClienteNum_containerTipoCategoria(String cliente, String num_container, String tipo, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND num_container LIKE %?2% " +
            "AND status LIKE %?3% " +
            "AND categoria LIKE %?4% ", nativeQuery = true)
    List<Conteiner> findByClienteNum_containerStatusCategoria(String cliente, String num_container, String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND CAST(tipo AS TEXT) LIKE %?2% " +
            "AND status LIKE %?3% " +
            "AND categoria LIKE %?4% ", nativeQuery = true)
    List<Conteiner> findByClienteTipoStatusCategoria(String cliente, String tipo, String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND num_container LIKE %?2% " +
            "AND CAST(tipo AS TEXT) LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByClienteNum_containerTipo(String cliente, String num_container, String tipo);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND num_container LIKE %?2% " +
            "AND status LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByClienteNum_containerStatus(String cliente, String num_container, String status);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND CAST(tipo AS TEXT) LIKE %?2% " +
            "AND status LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByClienteTipoStatus(String cliente, String tipo, String status);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND num_container LIKE %?2% " +
            "AND categoria LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByClienteNum_containerCategoria(String cliente, String num_container, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND CAST(tipo AS TEXT) LIKE %?2% " +
            "AND categoria LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByClienteTipoCategoria(String cliente, String tipo, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND status LIKE %?2% " +
            "AND categoria LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByClienteStatusCategoria(String cliente, String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND num_container LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByClienteNumContainer(String cliente, String num_container);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND CAST(tipo AS TEXT) LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByClienteTipo(String cliente, String tipo);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND status LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByClienteStatus(String cliente, String status);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% " +
            "AND categoria LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByClienteCategoria(String cliente, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE LOWER(cliente) LIKE %?1% ", nativeQuery = true)
    List<Conteiner> findCliente(String cliente);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE %?1% " +
            "AND CAST(tipo AS TEXT) LIKE %?2% " +
            "AND status LIKE %?3% " +
            "AND categoria LIKE %?4% ", nativeQuery = true)
    List<Conteiner> findByNum_containerTipoStatusCategoria(String num_container, String tipo, String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE %?1% " +
            "AND CAST(tipo AS TEXT) LIKE %?2% " +
            "AND status LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByNum_containerTipoStatus(String num_container, String tipo, String status);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE %?1% " +
            "AND CAST(tipo AS TEXT) LIKE %?2% " +
            "AND categoria LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByNum_containerTipoCategoria(String num_container, String tipo, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE %?1% " +
            "AND status LIKE %?2% " +
            "AND categoria LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByNum_containerStatusCategoria(String num_container, String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE %?1% " +
            "AND CAST(tipo AS TEXT) LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByNum_containerTipo(String num_container, String tipo);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE %?1% " +
            "AND status LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByNum_containerStatus(String num_container, String status);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE %?1% " +
            "AND categoria LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByNum_containerCategoria(String num_container, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE %?1% ", nativeQuery = true)
    List<Conteiner> findNum_container(String num_container);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE CAST(tipo AS TEXT) LIKE %?1% " +
            "AND status LIKE %?2% " +
            "AND categoria LIKE %?3% ", nativeQuery = true)
    List<Conteiner> findByTipoStatusCategoria(String tipo, String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE CAST(tipo AS TEXT) LIKE %?1% " +
            "AND status LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByTipoStatus(String tipo, String status);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE CAST(tipo AS TEXT) LIKE %?1% " +
            "AND categoria LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByTipoCategoria(String tipo, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE CAST(tipo AS TEXT) LIKE %?1% ", nativeQuery = true)
    List<Conteiner> findTipo(String tipo);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE status LIKE %?1% " +
            "AND categoria LIKE %?2% ", nativeQuery = true)
    List<Conteiner> findByStatusCategoria(String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE status LIKE %?1% ", nativeQuery = true)
    List<Conteiner> findStatus(String status);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE categoria LIKE %?1% ", nativeQuery = true)
    List<Conteiner> findCategoria(String categoria);

    //==============================================================
    //==============================================================
    @Query(value = "SELECT COUNT(id) FROM CONTEINERS " +
            "WHERE cliente = ?1 " +
            "AND num_container = ?2", nativeQuery = true)
    int findByClienteAndNum_container(String cliente, String num_container);

    @Query(value = "SELECT id FROM CONTEINERS " +
            "WHERE cliente = ?1 " +
            "AND num_container = ?2", nativeQuery = true)
    int returnIdByClienteAndNum_container(String cliente, String num_container);

    @Query(value = "SELECT " +
            "COUNT(id) " +
            "FROM conteiners WHERE categoria = ?1 ", nativeQuery = true)
    int tlCategoria(String categoria);

}
