package kr.hhplus.be.server.domain.payment.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import kr.hhplus.be.server.domain.EventState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "payment_event_outbox")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentEventOutBox extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_outbox_id", insertable = false, nullable = false)
    private Long id;
    @Column(name = "payment_id")
    private Long paymentId;
    @Column(name = "event_state")
    private EventState eventState;

    public static PaymentEventOutBox create(Long paymentId) {
        return PaymentEventOutBox.builder()
                .paymentId(paymentId)
                .eventState(EventState.INIT)
                .build();
    }

    public void updateState(EventState state) {
        this.eventState = state;
    }
}
