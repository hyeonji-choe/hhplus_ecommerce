package kr.hhplus.be.server.api.controller;

import kr.hhplus.be.server.api.AssetApi;
import kr.hhplus.be.server.api.model.AssetResult;
import kr.hhplus.be.server.application.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AssetApiController implements AssetApi {

    private final UserService userService;

    @Override
    public ResponseEntity<AssetResult> chargeAssetByUser(Long id, Long amount) {
        AssetResult result = userService.chargeUserAsset(id, amount);
        return ResponseEntity.ok(result);
    }

    @Override
    public ResponseEntity<AssetResult> searchAssetByUser(Long id) {
        AssetResult result = userService.getAssetByUserId(id);
        return ResponseEntity.ok(result);
    }
}
