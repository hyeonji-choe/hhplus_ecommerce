package kr.hhplus.be.server.application.user;

import jakarta.persistence.EntityNotFoundException;
import kr.hhplus.be.server.api.model.AssetResult;
import kr.hhplus.be.server.api.model.UserResult;
import kr.hhplus.be.server.domain.user.entity.User;
import kr.hhplus.be.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserResult registerUser(String name) {
        User user = User.create(name);
        userRepository.save(user);
        return UserResult.toResult(user);
    }

    @Override
    public AssetResult chargeUserAsset(Long userId, Long amount) {
        User user = userRepository.findByUserIdWithLock(userId);
        if (ObjectUtils.isEmpty(user)) throw new EntityNotFoundException("User not found.");
        user.chargeAsset(amount);
        userRepository.save(user);

        return AssetResult.toResult(user);
    }

    @Override
    public AssetResult useUserAsset(Long userId, Long amount) {
        User user = userRepository.findByUserIdWithLock(userId);
        if (ObjectUtils.isEmpty(user)) throw new EntityNotFoundException("User not found.");
        user.useAsset(amount);
        userRepository.save(user);

        return AssetResult.toResult(user);
    }

    @Transactional(readOnly = true)
    @Override
    public AssetResult getAssetByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);

        if (ObjectUtils.isEmpty(user)) throw new EntityNotFoundException("User not found.");
        return AssetResult.toResult(user);
    }

    @Transactional(readOnly = true)
    @Override
    public UserResult getUserByUserId(Long userId) {
        User user = userRepository.findByUserId(userId);

        if (ObjectUtils.isEmpty(user)) throw new EntityNotFoundException("User not found.");
        return UserResult.toResult(user);
    }
}
