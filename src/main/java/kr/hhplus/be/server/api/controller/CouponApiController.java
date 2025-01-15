package kr.hhplus.be.server.api.controller;

import kr.hhplus.be.server.api.CouponApi;
import kr.hhplus.be.server.api.model.CouponResult;
import kr.hhplus.be.server.api.model.IssueCouponResult;
import kr.hhplus.be.server.application.coupon.CouponService;
import kr.hhplus.be.server.common.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CouponApiController implements CouponApi {

    private final CouponService couponService;

    @Override
    public ResponseEntity<IssueCouponResult> issueCouponByUser(Long couponId, Long userId) throws CustomException {
        IssueCouponResult issueCouponResult = couponService.issueCoupon(couponId, userId);
        return ResponseEntity.ok(issueCouponResult);
    }

    @Override
    public ResponseEntity<List<CouponResult>> searchCoupons(int page, int rowsPerPage) {
        Page<CouponResult> couponResults = couponService.getCouponList(Pageable.ofSize(rowsPerPage).withPage(page));
        return ResponseEntity.ok((List<CouponResult>) couponResults);
    }

    @Override
    public ResponseEntity<List<IssueCouponResult>> searchCouponsByUser(Long id) {
        return ResponseEntity.ok(couponService.userIssuedCoupons(id));
    }
}
