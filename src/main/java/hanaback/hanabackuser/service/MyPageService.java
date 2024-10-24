package hanaback.hanabackuser.service;
import hanaback.hanabackuser.dto.AccountsDetDto;
import hanaback.hanabackuser.dto.AccountsTrDto;
import hanaback.hanabackuser.repository.AccountDetTableRepository;
import hanaback.hanabackuser.repository.AccountTrTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MyPageService {
    private final AccountTrTableRepository accountTrTableRepository;
    private final AccountDetTableRepository accountDetTableRepository;

    @Autowired
    public MyPageService(
            AccountTrTableRepository accountTrTableRepository,
            AccountDetTableRepository accountDetTableRepository
    ) {
        this.accountDetTableRepository = accountDetTableRepository;
        this.accountTrTableRepository = accountTrTableRepository;
    }
    // 모든 계좌 정보 조회
    public List<AccountsDetDto> getAllAccounts() {
        return accountDetTableRepository.findAllAccounts();
    }
    // 전체 트랜잭션 데이터를 가져오는 메서드
    public List<AccountsTrDto> getAllTransactions() {
        return accountTrTableRepository.getAllTransactions();
    }
}
