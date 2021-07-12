package com.t2s.processoseletivo.services;

import com.t2s.processoseletivo.models.Conteiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConteinerService extends JpaRepository<Conteiner, Integer> {

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE cliente LIKE '%?1%' " +
            "AND num_container LIKE '%?2%' " +
            "AND CAST(tipo AS TEXT) LIKE '%?3%' " +
            "AND status LIKE '%?4%' " +
            "AND categoria LIKE '%?5%' ", nativeQuery = true)
    List<Conteiner> findByClienteAndNum_containerAndTipoAndStatusAndCategoria(String cliente, String num_container, String tipo, String status, String categoria);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE cliente LIKE '%?1%' " +
            "AND num_container LIKE '%?2%' " +
            "AND CAST(tipo AS TEXT) LIKE '%?3%' " +
            "AND status LIKE '%?4%' ", nativeQuery = true)
    List<Conteiner> findByClienteAndNum_containerAndTipoAndStatus(String cliente, String num_container, String tipo, String status);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE cliente LIKE '%?1%' " +
            "AND num_container LIKE '%?2%' " +
            "AND CAST(tipo AS TEXT) LIKE '%?3%' " +
            "AND categoria LIKE '%?4%' ", nativeQuery = true)
    List<Conteiner> findByClienteAndNum_containerAndTipoAndCategoria(String cliente, String num_container, String tipo, String categoria);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE cliente LIKE '%?1%' " +
            "AND num_container LIKE '%?2%' " +
            "AND status LIKE '%?3%' " +
            "AND categoria LIKE '%?4%' ", nativeQuery = true)
    List<Conteiner> findByClienteAndNum_containerAndStatusAndCategoria(String cliente, String num_container, String status, String categoria);
    List<Conteiner> findByClienteAndTipoAndStatusAndCategoria(String cliente, String tipo, String status, String categoria);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE cliente LIKE '%?1%' " +
            "AND num_container LIKE '%?2%' " +
            "AND CAST(tipo AS TEXT) LIKE '%?3%' ", nativeQuery = true)
    List<Conteiner> findByClienteAndNum_containerAndTipo(String cliente, String num_container, String tipo);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE cliente LIKE '%?1%' " +
            "AND num_container LIKE '%?2%' " +
            "AND status LIKE '%?3%' ", nativeQuery = true)
    List<Conteiner> findByClienteAndNum_containerAndStatus(String cliente, String num_container, String status);
    List<Conteiner> findByClienteAndTipoAndStatus(String cliente, String tipo, String status);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE cliente LIKE '%?1%' " +
            "AND num_container LIKE '%?2%' " +
            "AND categoria LIKE '%?3%' ", nativeQuery = true)
    List<Conteiner> findByClienteAndNum_containerAndCategoria(String cliente, String num_container, String categoria);
    List<Conteiner> findByClienteAndTipoAndCategoria(String cliente, String tipo, String categoria);
    List<Conteiner> findByClienteAndStatusAndCategoria(String cliente, String status, String categoria);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE cliente LIKE '%?1%' " +
            "AND num_container LIKE '%?2%' ", nativeQuery = true)
    List<Conteiner> findByClienteAndNumContainer(String cliente, String num_container);
    List<Conteiner> findByClienteAndTipo(String cliente, String tipo);
    List<Conteiner> findByClienteAndStatus(String cliente, String status);
    List<Conteiner> findByClienteAndCategoria(String cliente, String categoria);
    List<Conteiner> findByCliente(String cliente);

    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE '%?1%' " +
            "AND CAST(tipo AS TEXT) LIKE '%?2%' " +
            "AND status LIKE '%?3%' " +
            "AND categoria LIKE '%?4%' ", nativeQuery = true)
    List<Conteiner> findByNum_containerAndTipoAndStatusAndCategoria(String num_container, String tipo, String status, String categoria);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE '%?1%' " +
            "AND CAST(tipo AS TEXT) LIKE '%?2%' " +
            "AND status LIKE '%?3%' ", nativeQuery = true)
    List<Conteiner> findByNum_containerAndTipoAndStatus(String num_container, String tipo, String status);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE '%?1%' " +
            "AND CAST(tipo AS TEXT) LIKE '%?2%' " +
            "AND categoria LIKE '%?3%' ", nativeQuery = true)
    List<Conteiner> findByNum_containerAndTipoAndCategoria(String num_container, String tipo, String categoria);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE '%?1%' " +
            "AND status LIKE '%?2%' " +
            "AND categoria LIKE '%?3%' ", nativeQuery = true)
    List<Conteiner> findByNum_containerAndStatusAndCategoria(String num_container, String status, String categoria);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE '%?1%' " +
            "AND CAST(tipo AS TEXT) LIKE '%?2%' ", nativeQuery = true)
    List<Conteiner> findByNum_containerAndTipo(String num_container, String tipo);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE '%?1%' " +
            "AND status LIKE '%?2%' ", nativeQuery = true)
    List<Conteiner> findByNum_containerAndStatus(String num_container, String status);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE '%?1%' " +
            "AND categoria LIKE '%?2%' ", nativeQuery = true)
    List<Conteiner> findByNum_containerAndCategoria(String num_container, String categoria);
    @Query(value = "SELECT * FROM CONTEINERS " +
            "WHERE num_container LIKE '%?1%' ", nativeQuery = true)
    List<Conteiner> findByNum_container(String num_container);

    List<Conteiner> findByTipoAndStatusAndCategoria(String tipo, String status, String categoria);
    List<Conteiner> findByTipoAndStatus(String tipo, String status);
    List<Conteiner> findByTipoAndCategoria(String tipo, String categoria);
    List<Conteiner> findByTipo(String tipo);

    List<Conteiner> findByStatusAndCategoria(String status, String categoria);
    List<Conteiner> findByStatus(String status);

    List<Conteiner> findByCategoria(String categoria);

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
