package shop.makaroni.bunjang.src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.makaroni.bunjang.src.domain.user.Users;

public interface UserRepository extends JpaRepository<Users, Long> {

	boolean existsByLoginId(String loginId);
}
