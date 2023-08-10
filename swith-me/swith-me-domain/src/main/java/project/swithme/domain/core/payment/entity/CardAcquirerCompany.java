package project.swithme.domain.core.payment.entity;

import java.util.Arrays;
import java.util.function.Predicate;

public enum CardAcquirerCompany {
    HYUNDAI("현대", "61"),
    SAMSUNG("삼성", "51"),
    LOTTE("롯데", "71"),
    TOSSBANK("토스뱅크", "24"),
    HANA("하나", "21"),
    KOOKMIN("국민", "11"),
    BC("비씨", "31"),
    NONGHYEOP("농협", "91"),
    KAKAOBANK("카카오뱅크", "15"),
    KBANK("케이뱅크", "3A"),
    IBK_BC("기업", "3K"),
    CITI("씨티", "36"),
    SAEMAUL("새마을", "38"),
    POST("우체국", "37"),
    SUHYEOP("수협", "34"),
    SHINHYEOP("신협", "62"),
    GWANGJUBANK("광주", "46"),
    JEONBUKBANK("전북", "35"),
    KDBBANK("산업", "30"),
    JEJUBANK("제주", "42"),
    SAVINGBANK("저축", "39"),
    WOORI("우리", "33"),
    VISA("비자", "4V"),
    MASTER("마스터", "4M"),
    UNIONPAY("유니온", "3C");

    private final String company;
    private final String code;

    CardAcquirerCompany(
        String company,
        String code
    ) {
        this.company = company;
        this.code = code;
    }

    public static CardAcquirerCompany findByCode(String code) {
        return Arrays.stream(values())
            .filter(equalTo(code))
            .findAny()
            .orElseThrow(() -> new IllegalArgumentException("올바른 카드 공급사 코드 입력해주세요."));
    }

    private static Predicate<CardAcquirerCompany> equalTo(String code) {
        return company -> company.code.equals(code);
    }
}
