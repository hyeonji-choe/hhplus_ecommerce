package kr.hhplus.be.server.api.controller;

import kr.hhplus.be.server.api.UserApi;
import kr.hhplus.be.server.api.model.UserResult;
import kr.hhplus.be.server.application.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserApiController implements UserApi {
    private final UserService userService;

    @Override
    public ResponseEntity<UserResult> registUser(String name) {
        return ResponseEntity.ok(userService.registerUser(name));
    }
}
