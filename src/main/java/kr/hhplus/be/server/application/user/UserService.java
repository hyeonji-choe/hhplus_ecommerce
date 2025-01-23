package kr.hhplus.be.server.application.user;

import kr.hhplus.be.server.api.model.AssetResult;
import kr.hhplus.be.server.api.model.UserResult;

public interface UserService {
    UserResult registerUser(String name);

    UserResult getUserByUserId(Long userId);

    AssetResult getAssetByUserId(Long userId);

    AssetResult chargeUserAsset(Long userId, Long amount);

    AssetResult useUserAsset(Long userId, Long amount);
}
