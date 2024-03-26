package co.istad.testmvc.respository;

import co.istad.testmvc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRespository extends JpaRepository<User,Integer> {
}
