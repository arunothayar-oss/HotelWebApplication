package com.project.hotel.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.project.hotel.Entity.User;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long>
{
    Optional<User> findByUsername(String username);
    Optional<User> findByUsernameIgnoreCase(String username);
    Optional<User> findByUsernameAndRole(String username, String role);
}
