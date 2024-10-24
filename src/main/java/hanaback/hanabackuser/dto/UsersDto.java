package hanaback.hanabackuser.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UsersDto {
    private long id;
    private String user_name;
    private String user_id;
    private String user_pwd;
    private int user_lv;
    private int point;
}
