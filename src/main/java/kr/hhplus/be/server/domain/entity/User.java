package kr.hhplus.be.server.domain.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@Entity
@Table(name = "user")
@ToString
@NoArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", insertable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "asset_amount")
    private long assetAmount;

    @Builder
    public User(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<CouponHistory> couponHistory;

    public void chargeAsset(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }
        this.assetAmount += amount;
    }

    public void useAsset(long amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than 0");
        }
        if (this.assetAmount == 0 || this.assetAmount < amount) {
            throw new IllegalArgumentException("asset amount is not enough");
        }
        this.assetAmount -= amount;
    }
}
