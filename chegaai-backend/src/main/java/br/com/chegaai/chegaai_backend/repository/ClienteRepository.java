package br.com.chegaai.chegaai_backend.repository;

import br.com.chegaai.chegaai_backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
