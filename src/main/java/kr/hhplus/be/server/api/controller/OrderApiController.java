package kr.hhplus.be.server.api.controller;

import kr.hhplus.be.server.api.OrderApi;
import kr.hhplus.be.server.api.model.OrderRegist;
import kr.hhplus.be.server.api.model.OrderResult;
import kr.hhplus.be.server.application.order.OrderService;
import kr.hhplus.be.server.application.order.OrderServiceRequest;
import kr.hhplus.be.server.domain.order.entity.OrderItem;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class OrderApiController implements OrderApi {
    private final OrderService orderService;

    @Override
    public ResponseEntity<OrderResult> registOrder(OrderServiceRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @Override
    public ResponseEntity<List<OrderItem>> registOrderProduct(OrderRegist orderRegist) {
        return ResponseEntity.ok(orderService.createOrderProduct(orderRegist.getOrderId(), orderRegist.getRequests()));
    }

    @Override
    public ResponseEntity<OrderResult> searchOrderByOrderId(Long id) {
        return ResponseEntity.ok(orderService.findOrderInfo(id));
    }
}
