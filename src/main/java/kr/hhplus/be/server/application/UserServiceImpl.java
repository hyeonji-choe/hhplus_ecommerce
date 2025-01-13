package kr.hhplus.be.server.application;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.domain.entity.User;
import kr.hhplus.be.server.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void registerUser(String name) {
        User user = new User(name);
        userRepository.save(user);
    }

    @Override
    public void chargeUserAsset(Long userId, Long amount) {
        User user = getUserByUserId(userId);
        user.chargeAsset(amount);
        userRepository.save(user);
    }

    private User getUserByUserId(Long userId) {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found."));
    }
}
