package project.swithme.domain.core.payment.entity;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public enum Bank {
    KYONGNAMBANK("경남은행", List.of("039", "39")),
    GWANGJUBANK("광주", List.of("034", "34")),
    LOCALNONGHYEOP("단위농협", List.of("012", "12")),
    BUSANBANK("부산", List.of("032", "32")),
    SAEMAUL("새마을", List.of("045", "45")),
    SANLIM("산림", List.of("064", "64")),
    SHINHAN("신한", List.of("088", "88")),
    SHINHYEOP("신협", List.of("048", "48")),
    CITI("씨티", List.of("027", "27")),
    WOORI("우리", List.of("020", "20")),
    POST("우체국", List.of("071", "71")),
    SAVINGBANK("저축", List.of("050", "50")),
    JEONBUKBANK("전북", List.of("037", "37")),
    JEJUBANK("제주", List.of("035", "35")),
    KAKAOBANK("카카오", List.of("090", "90")),
    KBANK("케이", List.of("089", "89")),
    TOSSBANK("토스", List.of("092", "92")),
    HANA("하나", List.of("081", "81")),
    HSBC("홍콩상하이은행", List.of("054", "54")),
    IBK("기업", List.of("003", "03")),
    KOOKMIN("국민", List.of("004", "06")),
    DAEGUBANK("대구", List.of("031", "31")),
    KDBBANK("산업", List.of("002", "02")),
    NONGHYEOP("농협", List.of("011", "11")),
    SC("SC제일", List.of("023", "23")),
    SUHYEOP("수협", List.of("007", "07"));

    private final String name;
    private final List<String> codes;

    Bank(
        String name,
        List<String> codes
    ) {
        this.name = name;
        this.codes = codes;
    }

    public static Bank findByCode(String bankCode) {
        return Arrays.stream(values())
            .filter(contains(bankCode))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 은행 코드를 입력해주세요."));
    }

    private static Predicate<Bank> contains(String bankCode) {
        return bank -> bank.codes.contains(bankCode);
    }
}
