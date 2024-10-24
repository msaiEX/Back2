package hanaback.hanabackuser.service;

import hanaback.hanabackuser.dto.UsersDto;
import hanaback.hanabackuser.repository.UsersTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private UsersTableRepository usersTableRepository;

    public UsersDto login(String userId, String password) {
        UsersDto user = usersTableRepository.findByUserId(userId);

        if (user != null && user.getUser_pwd().equals(password)) {
            return user; // 비밀번호가 일치하면 유저 정보 반환
        }
        return null; // 로그인 실패 시 null 반환
    }
}