package hanaback.hanabackuser.service;

import net.nurigo.sdk.NurigoApp;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.request.SingleMessageSendingRequest;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class SmsService {

    // SDK를 사용하기 위한 Service 객체 초기화
    private final DefaultMessageService messageService;



    @Value("${coolsms.from}")
    private String fromNumber;

    // Constructor에서 NurigoApp을 초기화
    public SmsService() {
        this.messageService = NurigoApp.INSTANCE.initialize("NCSKJQYBDPM34HMU", "TBDVGTGGXJWROKICBQMPXCAHZ7ARY9YC", "https://api.coolsms.co.kr");
    }

    // SMS 전송 로직
    public String sendSMS(String toNumber, String transactionType, double withdrawalAmount, double newKrw, double targetRemitSend, double targetRemitReceive, double requestAmount) {

        System.out.println("구매완료");
        String randomNum = makeRandomNumber();  // 랜덤 인증번호 생성
        // 천 단위 콤마 추가 형식으로 변경
        DecimalFormat df = new DecimalFormat("#,###");
        String formattedAmount = df.format(withdrawalAmount); // 콤마 형식 변환
        int formattedKrw = (int) newKrw; // 정수로 변환
        String formattedNewKrw = df.format(formattedKrw);
        String formattedRequest = df.format(requestAmount);


        // transactionType 변환
        String transactionAction = transactionType.equalsIgnoreCase("buy") ? "사기" : "팔기";
        double targetRate = transactionType.equalsIgnoreCase("buy") ? targetRemitSend : targetRemitReceive;

        String formattedTargetRate = df.format(targetRate);
        // 현재시간 추가
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedTime = now.format(formatter);

        //String msgString = String.format("[Travel Home] \n회원가입을 위한 인증번호는 %s 입니다.", randomNum);
        String msgString = "[HANA EX] \n양하나님 예약된 건이 채결되었습니다." + "\n" +
                "[결제내역] \n"+
                "채결타입 : " + transactionAction + "\n" +
                "채결환율 : " + formattedTargetRate + "\n" +
                "요청환율 : " + formattedRequest + "\n" +
                "채결금액 : " + formattedAmount + "\n" +
                "통장잔액 : " + formattedNewKrw + "\n" +
                "결제시간 : " + formattedTime + "\n" +
                "* 80% 우대율이 적용된 금액입니다.";
        // 메시지 생성
        Message message = new Message();
        message.setFrom(fromNumber);  // 발신자 번호 설정
        message.setTo(toNumber.replaceAll("-", ""));  // 수신자 번호에서 하이픈 제거
        message.setText(msgString);  // 메시지 내용 설정

        // 메시지 전송
        messageService.sendOne(new SingleMessageSendingRequest(message));

        return randomNum;  // 생성한 랜덤 인증번호 반환
    }

    // 6자리 랜덤 인증번호 생성 메서드
    private String makeRandomNumber() {
        int randomNum = (int)(Math.random() * 900000) + 100000;  // 6자리 숫자 생성
        return String.valueOf(randomNum);
    }
}
