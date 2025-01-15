package kr.hhplus.be.server.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.hhplus.be.server.api.model.IssueCouponResult;
import kr.hhplus.be.server.application.coupon.CouponService;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CouponApiController.class)
@ExtendWith(MockitoExtension.class)
public class CouponApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CouponService couponService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void CouponIssue() throws Exception {
        // Given
        Long couponId = 1L;
        Long userId = 100L;

        IssueCouponResult mockResponse = IssueCouponResult.create(1L, couponId, userId, "쿠폰발급테스트", "");
        when(couponService.issueCoupon(any(Long.class), any(Long.class))).thenReturn(mockResponse);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.post("/coupons/get/" + userId + "/" + couponId))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserCoupons_Success() throws Exception {
        // Given
        Long userId = 1L;

        List<IssueCouponResult> mockResponseList = Arrays.asList(
                IssueCouponResult.create(1L, 1L, 101L, "쿠폰1", ""),
                IssueCouponResult.create(2L, 1L, 102L, "쿠폰2", "")
        );
        when(couponService.userIssuedCoupons(userId)).thenReturn(mockResponseList);

        // When & Then
        mockMvc.perform(MockMvcRequestBuilders.get("/coupons/" + String.valueOf(userId))
                        .param("userId", String.valueOf(userId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}