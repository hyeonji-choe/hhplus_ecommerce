package kr.hhplus.be.server.infrastructure.repository;

import jakarta.persistence.LockModeType;
import kr.hhplus.be.server.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.id = :userId")
    User findByUserId(Long userId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select u from User u where u.id = :userId")
    User findByUserIdWithLock(Long userId);
}
