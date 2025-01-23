package kr.hhplus.be.server.domain.user.entity;

import jakarta.persistence.*;
import kr.hhplus.be.server.domain.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "user")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", insertable = false, nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "asset_amount")
    private long assetAmount;

    public static User create(String name) {
        return User.builder()
                .name(name)
                .assetAmount(0)
                .build();
    }

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
