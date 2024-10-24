package hanaback.hanabackuser.controller;

import hanaback.hanabackuser.dto.AccountsDetDto;
import hanaback.hanabackuser.dto.AccountsTrDto;
import hanaback.hanabackuser.service.MyPageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MypageController {
    private final MyPageService myPageService;

    @Autowired
    public MypageController(MyPageService myPageService) {
        this.myPageService = myPageService;
    }

    @GetMapping("/api/getallaccount")
    public Map<String, Object> getAllData() {
        List<AccountsDetDto> accounts = myPageService.getAllAccounts();
        List<AccountsTrDto> transactions = myPageService.getAllTransactions();

        // 데이터를 묶어서 반환
        Map<String, Object> response = new HashMap<>();
        response.put("accounts", accounts);
        response.put("transactions", transactions);
        return response;
    }
}
