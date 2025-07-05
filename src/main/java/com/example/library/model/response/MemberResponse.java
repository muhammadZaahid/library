package com.example.library.model.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponse {
    private String id;
    private String name;
    private String email;
    private String phone;
}