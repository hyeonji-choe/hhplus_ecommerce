package kr.hhplus.be.server.domain.entity;

/**
 * 결제 상태 종류
 * - FAIL : 실패 ?
 * - COMPLETE : 완료
 * - CANCEL : 취소
 */
public enum PaymentState {
    FAIL, COMPLETE, CANCEL
}
