package net.codejava.admin;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    public User findByEmail(String email); 
}
