package com.webtracker.Util;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class JwtResponse {

    private String token;
    private String username;
    private String refreshTokenId;
}
