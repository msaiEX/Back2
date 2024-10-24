package hanaback.hanabackuser.service;

import hanaback.hanabackuser.dto.AccountsTrDto;
import hanaback.hanabackuser.repository.AccountDetTableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class DetService {

    @Autowired
    private AccountDetTableRepository accountDetTableRepository;

    public void updateAccountsDet(AccountsTrDto accountsTrDto) throws SQLException {
        // 비즈니스 로직에 따라 accounts_det 업데이트
        accountDetTableRepository.updateAccountsDet(accountsTrDto);
    }
}
