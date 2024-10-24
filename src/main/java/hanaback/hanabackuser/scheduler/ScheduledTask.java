package hanaback.hanabackuser.scheduler;

import hanaback.hanabackuser.service.TrService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScheduledTask {

    private final TrService trService;

    /**
     * 매 분마다 예약된 거래를 처리하는 메서드
     */
    @Scheduled(cron = "*/10 * * * * *") // 매 분 0초에 실행
//    @Scheduled(cron = "* * * * * *")
    public void scheduledProcess() {
        String state = "USD"; // 필요한 상태 값을 설정하세요.
        System.out.println("동작중");
        trService.processReservedTransactions(state);
    }
}
