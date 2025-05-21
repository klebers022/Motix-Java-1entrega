package br.com.motix.repositories;

import br.com.motix.models.Motorcycle;
import br.com.motix.models.User;
import jakarta.persistence.QueryHint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.QueryHints;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

   User findAllByRm(String rm);

   List<User> findByName(String name);

   void deleteByRm(String rm);

   boolean existsByRm(String rm);
}
