package dte.tzevaadomtracker.repositories;

import dte.tzevaadomtracker.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
    boolean existsByName(String name);
}
