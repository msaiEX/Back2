package hanaback.hanabackuser.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class AccountsTrDto {
    private String user_id;
    private String transaction_id;
    private String from_account_number;
    private String to_account_number;
    private double withdrawal_amount;
    private double deposit_amount;
    private String currency_code;
    private String transaction_date;

    // 추가된 컬럼들
    private String transaction_type;
    private String conclusion_status;
    private String reservation_period;

    //
    private double request_amount;
}
