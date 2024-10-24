package hanaback.hanabackuser.controller;

import hanaback.hanabackuser.dto.AccountsTrDto;
import hanaback.hanabackuser.dto.LoginRequestDto;
import hanaback.hanabackuser.dto.UsersDto;
import hanaback.hanabackuser.service.DetService;
import hanaback.hanabackuser.service.LoginService;
import hanaback.hanabackuser.service.TrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginPageController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private TrService trService;
    @Autowired
    private DetService detService;

    @PostMapping("/api/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequest) {
        String id = loginRequest.getId();
        String password = loginRequest.getPassword();

        try {
            UsersDto user = loginService.login(id, password);
            if (user != null) {
                return ResponseEntity.ok(user);
            } else {
                return ResponseEntity.status(401).body("아이디 또는 비밀번호가 잘못되었습니다.");
            }
        } catch (Exception e) {
            // 로그 추가하여 오류 원인 파악
            e.printStackTrace();  // 실제 환경에서는 로그 라이브러리 사용 추천 (ex. SLF4J)
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
    @PostMapping("/api/trinsert")
    public ResponseEntity<?> insertTransaction(@RequestBody AccountsTrDto accountsTrDto) {
        System.out.println("trinsert");
        try {
            trService.processTransaction(accountsTrDto);
            detService.updateAccountsDet(accountsTrDto);
            return ResponseEntity.ok("Transaction inserted successfully");
        } catch (Exception e) {
            e.printStackTrace();  // 실제 환경에서는 로그 라이브러리 사용 추천 (ex. SLF4J)
            return ResponseEntity.status(500).body("서버 오류: " + e.getMessage());
        }
    }
}