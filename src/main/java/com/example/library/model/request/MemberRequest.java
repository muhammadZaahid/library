package com.example.library.model.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {
    private String name;
    private String email;
    private String phone;
}