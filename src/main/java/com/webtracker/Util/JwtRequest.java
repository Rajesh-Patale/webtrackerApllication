package com.webtracker.Util;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class JwtRequest {

    private String username;
    private String password;
}
