package com.ecommerce.openapi.apis.controller;

import com.ecommerce.openapi.apis.UserApi;
import com.ecommerce.openapi.apis.models.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController implements UserApi {

    @Override
    public ResponseEntity<User> registUser(String name) {
        User body = new User();
        return ResponseEntity.ok(body);
    }
}
