//package hanaback.hanabackuser.repository;
//
//import hanaback.hanabackuser.dto.AccountsTrDto;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class AccountTrTableRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public AccountTrTableRepository(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    // 트랜잭션 데이터를 삽입하는 메서드
//    public void insertTransaction(AccountsTrDto accountsTrDto) {
//        String sql = "INSERT INTO accounts_tr (user_id, from_account_number, to_account_number, withdrawal_amount, deposit_amount, currency_code, transaction_date, transaction_type, conclusion_status, krw, usd, jpy, eur) " +
//                "VALUES (?, ?, ?, ?, ?, ?, SYSDATE, ?, ?, ?, ?, ?, ?)";
//
//        jdbcTemplate.update(sql,
//                accountsTrDto.getUser_id(),
//                accountsTrDto.getFrom_account_number(),
//                accountsTrDto.getTo_account_number(),
//                accountsTrDto.getWithdrawal_amount(),
//                accountsTrDto.getDeposit_amount(),
//                accountsTrDto.getCurrency_code(),
//                accountsTrDto.getTransaction_type(),
//                accountsTrDto.getConclusion_status(),
//                accountsTrDto.getKrw(),
//                accountsTrDto.getUsd(),
//                accountsTrDto.getJpy(),
//                accountsTrDto.getEur()
//        );
//    }
//}

package hanaback.hanabackuser.repository;

import hanaback.hanabackuser.dto.AccountsTrDto;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public class AccountTrTableRepository {
    private final SqlSessionTemplate sqlSession;


    @Autowired
    public AccountTrTableRepository(SqlSessionTemplate sqlSession) {
        this.sqlSession = sqlSession;
    }

    // 트랜잭션 데이터를 삽입하는 메서드 (MyBatis 사용)
    public void insertTransaction(AccountsTrDto accountsTrDto) {
        sqlSession.insert("hanaback.hanabackuser.repository.AccountTrTableRepository.insertTransaction", accountsTrDto);
    }

    // reserved 상태의 트랜잭션을 조회하는 메서드
    public List<AccountsTrDto> getReservedTransactions() {
        return sqlSession.selectList("hanaback.hanabackuser.repository.AccountTrTableRepository.getReservedTransactions");
    }

    // 트랜잭션을 업데이트하는 메서드
    public void updateTransaction(AccountsTrDto accountsTrDto) {
        sqlSession.update("hanaback.hanabackuser.repository.AccountTrTableRepository.updateTransaction", accountsTrDto);
    }

    // 전체 트랜잭션 데이터를 가져오는 메서드
    public List<AccountsTrDto> getAllTransactions() {
        return sqlSession.selectList("hanaback.hanabackuser.repository.AccountTrTableRepository.getAllTransactions");
    }


}

