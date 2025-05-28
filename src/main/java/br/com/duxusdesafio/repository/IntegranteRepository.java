package br.com.duxusdesafio.repository;

import br.com.duxusdesafio.model.Integrante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IntegranteRepository extends JpaRepository<Integrante, Long> {

    @Query("SELECT c.integrante FROM ComposicaoTime c WHERE c.time.data BETWEEN :inicio AND :fim")
    List<Integrante> findAllByTimeDataBetween(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);

}
