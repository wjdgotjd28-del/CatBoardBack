package com.catboardback.dto;

import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberDto
{
    private  String memberEmail;

    private  String password;

    private  String nickName;
}
