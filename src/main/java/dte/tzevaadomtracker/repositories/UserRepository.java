package dte.tzevaadomtracker.repositories;

import dte.tzevaadomtracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByPersonalToken(UUID token);

    boolean existsByPersonalToken(UUID personalToken);
}
