package hanaback.hanabackuser.repository;

import hanaback.hanabackuser.dto.AccountsDetDto;
import hanaback.hanabackuser.dto.AccountsTrDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AccountDetTableRepository {

    private final SqlSession sqlSession;

    @Autowired
    public AccountDetTableRepository(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    // accounts_det 업데이트 메서드
    public void updateAccountsDet(AccountsTrDto accountsTrDto) throws SQLException {
        String fromAccount = accountsTrDto.getFrom_account_number();
        String toAccount = accountsTrDto.getTo_account_number();
        String currencyColumn = accountsTrDto.getCurrency_code().toLowerCase();
        double withdrawalAmount = accountsTrDto.getWithdrawal_amount();
        double depositAmount = accountsTrDto.getDeposit_amount();

        // 매개변수 맵 생성
        Map<String, Object> params = new HashMap<>();
        params.put("withdrawalAmount", withdrawalAmount);
        params.put("depositAmount", depositAmount);
        params.put("currencyColumn", currencyColumn);

        if ("completed".equals(accountsTrDto.getConclusion_status())) {
            // 1. from_account가 "하나저축예금"일 경우
            if ("하나저축예금".equals(fromAccount)) {
                // 하나저축예금 계좌에서 KRW 차감
                sqlSession.update("hanaback.hanabackuser.repository.AccountDetTableRepository.updateFromAccount", params);

                // 하나밀리언달러통장에 해당 통화 추가
                sqlSession.update("hanaback.hanabackuser.repository.AccountDetTableRepository.updateToAccount", params);

                // 2. from_account가 "하나밀리언달러통장"일 경우
            } else if ("하나밀리언달러통장".equals(fromAccount)) {
                // 하나밀리언달러통장에서 해당 통화 차감
                sqlSession.update("hanaback.hanabackuser.repository.AccountDetTableRepository.updateFromMillionDollar", params);

                // 하나저축예금 계좌에 KRW 추가
                sqlSession.update("hanaback.hanabackuser.repository.AccountDetTableRepository.updateToSavingAccount", params);
            }
        }
    }

    // 모든 계좌 정보 조회
    public List<AccountsDetDto> findAllAccounts() {
        return sqlSession.selectList("hanaback.hanabackuser.repository.AccountDetTableRepository.findAllAccounts");
    }
}
