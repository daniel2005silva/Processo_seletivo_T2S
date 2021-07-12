package com.t2s.processoseletivo.services;

import com.t2s.processoseletivo.models.Conteiner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ConteinerService extends JpaRepository<Conteiner, Integer> {
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
