package kr.hhplus.be.server.application;

public interface UserService {
    void registerUser(String name);

    void chargeUserAsset(Long userId, Long amount);
}
