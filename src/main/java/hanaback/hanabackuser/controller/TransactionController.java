package hanaback.hanabackuser.controller;

import hanaback.hanabackuser.dto.AccountsTrDto;
import hanaback.hanabackuser.service.TrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TrService trService;

    // Get 요청: reserved 상태의 트랜잭션 조회
    @GetMapping("/api/reserved")
    public ResponseEntity<List<AccountsTrDto>> getReservedTransactions() {
        List<AccountsTrDto> reservedTransactions = trService.getReservedTransactions();
        return ResponseEntity.ok(reservedTransactions);
    }

    // Put 요청: 트랜잭션 업데이트
    @PutMapping("/api/updateTransaction")
    public ResponseEntity<?> updateTransaction(@RequestBody AccountsTrDto accountsTrDto) {
        System.out.println("updateTransaction");
        try {
            trService.updateTransaction(accountsTrDto);
            System.out.println("2 : updateTransaction");
            return ResponseEntity.ok("Transaction updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error updating transaction: " + e.getMessage());
        }
    }
}
