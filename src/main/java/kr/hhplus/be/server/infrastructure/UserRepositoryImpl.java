package kr.hhplus.be.server.infrastructure;

import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import kr.hhplus.be.server.infrastructure.repository.UserJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final UserJpaRepository userJpaRepository;

    public UserRepositoryImpl(UserJpaRepository userJpaRepository) {
        this.userJpaRepository = userJpaRepository;
    }

    @Override
    public User findByUserId(Long userId) {
        return userJpaRepository.findByUserId(userId);
    }

    @Override
    public User findByUserIdWithLock(Long userId) {
        return userJpaRepository.findByUserIdWithLock(userId);
    }

    @Override
    public User save(User user) {
        return userJpaRepository.save(user);
    }
}
