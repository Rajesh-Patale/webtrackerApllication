package com.webtracker.Util;

import com.webtracker.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
@Entity
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String tokenId;

    private String refreshTokenId;

    @Column(columnDefinition = "TIMESTAMP")
    private Instant expiry;

    @OneToOne
    private User userData;
}
