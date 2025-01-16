package kr.hhplus.be.server.api.model;

import kr.hhplus.be.server.application.order.OrderProductServiceRequest;
import lombok.Getter;

import java.util.List;

@Getter
public class OrderRegist {
    private Long orderId;
    private List<OrderProductServiceRequest> requests;
}
