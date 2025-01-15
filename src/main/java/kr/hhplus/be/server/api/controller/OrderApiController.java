package kr.hhplus.be.server.api.controller;

import kr.hhplus.be.server.api.OrderApi;
import kr.hhplus.be.server.api.model.CartProduct;
import kr.hhplus.be.server.api.model.CartRegist;
import kr.hhplus.be.server.api.model.OrderProduct;
import kr.hhplus.be.server.application.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderApiController implements OrderApi {
    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderProduct> orderCartProduct(Long id) {
        return null;
    }

    @Override
    public ResponseEntity<List<CartProduct>> registCartProduct(CartRegist cartRegist) {
        return null;
    }

    @Override
    public ResponseEntity<List<CartProduct>> searchCartByUser(Long id) {
        return null;
    }
}
