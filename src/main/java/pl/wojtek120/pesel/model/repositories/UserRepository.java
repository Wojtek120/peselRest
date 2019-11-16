package pl.wojtek120.pesel.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wojtek120.pesel.model.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
