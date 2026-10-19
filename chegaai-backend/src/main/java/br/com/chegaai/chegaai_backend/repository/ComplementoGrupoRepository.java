package br.com.chegaai.chegaai_backend.repository;

import br.com.chegaai.chegaai_backend.entity.ComplementoGrupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplementoGrupoRepository extends JpaRepository<ComplementoGrupo, Long> {
}
