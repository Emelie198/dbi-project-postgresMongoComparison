package at.movierating.postgres.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import at.movierating.postgres.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
