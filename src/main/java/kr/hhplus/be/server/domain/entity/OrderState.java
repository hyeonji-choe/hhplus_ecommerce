package kr.hhplus.be.server.domain.entity;

/**
 * 주문 상태 종류
 * - PEND : 대기
 * - COMPLETE : 완료
 * - CANCEL : 취소
 */
public enum OrderState {
    PEND, COMPLETE, CANCEL
}
