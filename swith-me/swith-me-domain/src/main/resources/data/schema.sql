CREATE TABLE orders
(
    id                     BIGINT AUTO_INCREMENT PRIMARY KEY                             NOT NULL COMMENT '주문 PK',
    user_id                BIGINT                                                        NOT NULL COMMENT '사용자 PK',
    reservation_id         BIGINT                                                        NULL COMMENT '예약 PK',
    unique_id              BINARY(16)                                                    NOT NULL COMMENT '주문 조회 노출용 아이디',
    title                  VARCHAR(50)                                                   NOT NULL COMMENT '주문명',
    total_price            DECIMAL(38, 2)                                                NULL COMMENT '총 주문 가격',
    discounted_total_price DECIMAL(38, 2)                                                NULL COMMENT '할인된 주문 가격',
    pay_type               ENUM ('CARD', 'KAKAO_PAY', 'NAVER_PAY', 'TOSS', 'REMITTANCE') NOT NULL COMMENT '결제 수단',
    order_status           ENUM ('PAYMENT_REQUEST', 'COMPLETE', 'CANCEL', 'REFUND')      NOT NULL COMMENT '주문 상태',
    deposit_deadline       DATETIME(6)                                                   NOT NULL COMMENT '입금 마감일',
    refund_reason          VARCHAR(1000)                                                 NULL COMMENT '환불 사유',
    created_by             BIGINT                                                        NOT NULL COMMENT '생성자',
    create_at              DATETIME(6)                                                   NOT NULL COMMENT '생성일',
    last_modified_by       BIGINT                                                        NULL COMMENT '최종 수정한 사람',
    last_modified_at       DATETIME(6)                                                   NULL COMMENT '최종 수정일',
    deleted                BIT                                                           NOT NULL COMMENT '삭제 유무'
) ENGINE = InnoDB COMMENT '주문';

CREATE TABLE order_line
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '주문 상세 PK',

    order_id         BIGINT                            NOT NULL COMMENT '주문 PK',
    study_cafe_id    BIGINT                            NOT NULL COMMENT '스터디 카페 PK',
    product_id       BIGINT                            NOT NULL COMMENT '상품 PK',
    price            DECIMAL(38, 2)                    NOT NULL COMMENT '가격',

    created_by       BIGINT                            NULL COMMENT '생성자',
    create_at        DATETIME(6)                       NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                            NULL COMMENT '최종 수정자',
    last_modified_at DATETIME(6)                       NULL COMMENT '최종 수정일',
    deleted          BIT                               NOT NULL COMMENT '삭제 유무'
) ENGINE = InnoDB COMMENT '주문 상세';

CREATE TABLE reservation
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY             NOT NULL COMMENT '예약 PK',
    user_id            BIGINT                                        NOT NULL COMMENT '사용자 PK',
    study_cafe_id      BIGINT                                        NOT NULL COMMENT '스터디 카페 PK',
    product_id         BIGINT                                        NOT NULL COMMENT '상품 PK',
    seat_id            BIGINT                                        NOT NULL COMMENT '좌석 PK',
    start_time         DATETIME(6)                                   NOT NULL COMMENT '시작 시간',
    end_time           DATETIME(6)                                   NOT NULL COMMENT '끝나는 시간',
    reservation_status ENUM ('PAYMENT_REQUEST','RESERVED', 'CANCEL') NOT NULL COMMENT '예약 상태',
    cancel_reason      VARCHAR(1000)                                 NULL COMMENT '취소 사유',
    created_by         BIGINT                                        NOT NULL COMMENT '생성자',
    create_at          DATETIME(6)                                   NOT NULL COMMENT '생성일',
    last_modified_by   BIGINT                                        NULL COMMENT '최종 수정자',
    last_modified_at   DATETIME(6)                                   NULL COMMENT '최종 수정일',
    deleted            BIT                                           NOT NULL COMMENT '삭제 유무'
) ENGINE = InnoDB COMMENT '예약';

CREATE TABLE payment
(
    id                          BIGINT AUTO_INCREMENT PRIMARY KEY                   NOT NULL COMMENT '토스 결제 PK',
    version                     VARCHAR(10)                                         NOT NULL COMMENT '토스 결제 시스템 버전',
    payment_key                 VARCHAR(200)                                        NOT NULL COMMENT '결제 키 값(최대 2000자)',
    payment_type                ENUM ('NORMAL', 'BILLING', 'BRANDPAY')              NOT NULL COMMENT '결제 타입 정보. NOTMAL(일반 결제), BILLING(자동 결제), BRANDPAY(브랜드페이)',
    order_id                    BIGINT                                              NOT NULL COMMENT '주문 PK',
    order_name                  VARCHAR(100)                                        NOT NULL COMMENT '주문명',
    m_id                        VARCHAR(14)                                         NULL COMMENT '상점 아이디(최대 14자)',
    currency                    VARCHAR(255)                                        NULL COMMENT '결제시 사용한 통화',
    method                      ENUM (
        'CARD', 'VIRTUAL_ACCOUNT', 'SIMPLE_PAYMENT', 'MOBILE_PHONE',
        'ACCOUNT_TRANSFER','CULTURAL_GIFT_CERTIFICATE',
        'BOOK_CULTURE_GIFT_CERTIFICATE', 'GAME_CULTURE_GIFT_CERTIFICATE'
        )                                                                           NOT NULL COMMENT '결제 수단',
    total_amount                DECIMAL(38, 2)                                      NOT NULL COMMENT '총 결제 금액',
    balance_amount              DECIMAL(38, 2)                                      NOT NULL COMMENT '취소할 수 있는 금액(잔고)',
    payment_status              ENUM (
        'READY', 'IN_PROGRESS', 'WAITING_FOR_DEPOSIT', 'DONE',
        'CANCELED','PARTIAL_CANCELED', 'ABORTED', 'EXPIRED'
        )                                                                           NOT NULL COMMENT '결제 처리 상태',
    requested_at                DATETIME(6)                                         NOT NULL COMMENT '결제가 발생 날짜',
    approved_at                 DATETIME(6)                                         NOT NULL COMMENT '끝나는 승인 날짜',
    use_escrow                  BIT                                                 NOT NULL COMMENT '에스크로 사용 여부',
    last_transaction_key        VARCHAR(64)                                         NULL COMMENT '마지막 거래 키(최대 64)',
    supplied_amount             DECIMAL(38, 2)                                      NOT NULL COMMENT '공급가액',
    vat                         DECIMAL(38, 2)                                      NOT NULL COMMENT '부가세',
    culture_expense             BIT                                                 NOT NULL COMMENT '문화비',
    tax_free_amount             DECIMAL(38, 2)                                      NOT NULL COMMENT '면세 금액',
    tax_exemption_amount        DECIMAL(38, 2)                                      NOT NULL COMMENT '과세 제외 결제 금액',
    is_partial_cancelable       BIT                                                 NULL COMMENT '부분 취소 가능 여부',

    -- 카드 --
    card_amount                 DECIMAL(38, 2) DEFAULT 0                            NULL COMMENT '카드 결제 요청 금액',
    card_issuer_company         ENUM (
        'HYUNDAI', 'SAMSUNG', 'LOTTE', 'TOSSBANK', 'HANA', 'KOOKMIN',
        'BC', 'NONGHYEOP', 'KAKAOBANK', 'KBANK', 'IBK_BC', 'CITI',
        'SAEMAUL', 'POST', 'SUHYEOP', 'SHINHYEOP','GWANGJUBANK',
        'JEONBUKBANK', 'KDBBANK', 'JEJUBANK', 'SAVINGBANK', 'WOORI',
        'VISA', 'MASTER', 'UNIONPAY'
        )                                                                           NULL COMMENT '카드 발급사',
    card_acquirer_comany        ENUM (
        'HYUNDAI', 'SAMSUNG', 'LOTTE', 'TOSSBANK', 'HANA', 'KOOKMIN',
        'BC', 'NONGHYEOP', 'KAKAOBANK', 'KBANK', 'IBK_BC', 'CITI',
        'SAEMAUL', 'POST', 'SUHYEOP', 'SHINHYEOP''GWANGJUBANK',
        'JEONBUKBANK', 'KDBBANK', 'JEJUBANK', 'SAVINGBANK',
        'WOORI', 'VISA', 'MASTER', 'UNIONPAY'
        )                                                                           NULL COMMENT '카드 매입사',
    card_number                 VARCHAR(255)                                        NULL COMMENT '카드 번호',
    installment_plan_months     INTEGER                                             NULL COMMENT '할부 개월 수',
    card_approve_no             VARCHAR(8)                                          NULL COMMENT '카드사 승인 번호(최대 8자)',
    use_card_point              BIT                                                 NULL COMMENT '카드사 포인트 사용 여부',
    card_type                   ENUM ('CREDIT', 'CHECK', 'GIFT', 'UN_IDENTIFIED')   NULL COMMENT '카드 종류(신용, 체크, 기프트, 미확인)',
    owner_type                  ENUM ('INDIVIDUAL', 'CORPORATION', 'UN_IDENTIFIED') NULL COMMENT '카드 소유자 타입(개인, 법인, 미확인)',
    acquire_status              ENUM (
        'READY', 'REQUESTED', 'COMPLETED', 'CANCEL_REQUESTED', 'CANCELED'
        )                                                                           NULL COMMENT '카드 결제 매입 상태',

    is_interest_free            BIT                                                 NULL COMMENT '무이자 할부 적용 여부',
    interest_payer              ENUM ('BUYER', 'CARD_COMPANY', 'MERCHANT')          NULL COMMENT '할부 수수료 부담 주체',

    -- 가상 계좌 --
    account_type                ENUM ('GENERAL', 'FIXED')                           NULL COMMENT '가상 계좌 유형(일반, 고정)',
    account_number              VARCHAR(20)                                         NULL COMMENT '발급된 계좌 번호(최대 20자)',
    virtual_account_bank        ENUM (
        'KYONGNAMBANK', 'GWANGJUBANK', 'LOCALNONGHYEOP', 'BUSANBANK', 'SAEMAUL',
        'SANLIM', 'SHINHAN', 'SHINHYEOP', 'CITI', 'WOORI', 'POST', 'SAVINGBANK',
        'JEONBUKBANK', 'JEJUBANK', 'KAKAOBANK', 'KBANK', 'TOSSBANK', 'HANA', 'HSBC',
        'IBK', 'KOOKMIN', 'DAEGUBANK', 'KDBBANK', 'NONGHYEOP', 'SC', 'SUHYEOP'
        )                                                                           NULL COMMENT '가상계좌 은행',
    customer_name               VARCHAR(13)                                         NULL COMMENT '가상계좌 발급 고객 이름',
    due_date                    DATETIME(6)                                         NULL COMMENT '입금 기한',
    refund_status               enum (
        'NONE', 'PENDING', 'FAILED', 'PARTIAL_FAILED', 'COMPLETED'
        )                                                                           NULL COMMENT '환불 처리 상태',
    expired_date                BIT                                                 NULL COMMENT '가상계좌 만료 여부',
    v_account_settlement_status ENUM ('COMPLETED', 'INCOMPLETED')                   NULL COMMENT '가상계좌 결제 정산 상태',
    holder_name                 VARCHAR(13)                                         NULL COMMENT '예금주 정보',
    refund_account_number       VARCHAR(255)                                        NULL COMMENT '환불계좌',
    refund_bank                 ENUM (
        'KYONGNAMBANK', 'GWANGJUBANK', 'LOCALNONGHYEOP', 'BUSANBANK', 'SAEMAUL',
        'SANLIM', 'SHINHAN', 'SHINHYEOP', 'CITI', 'WOORI', 'POST', 'SAVINGBANK',
        'JEONBUKBANK', 'JEJUBANK', 'KAKAOBANK', 'KBANK', 'TOSSBANK', 'HANA', 'HSBC',
        'IBK', 'KOOKMIN', 'DAEGUBANK', 'KDBBANK', 'NONGHYEOP', 'SC', 'SUHYEOP'
        )                                                                           NULL COMMENT '가상계좌 은행',

    secret                      VARCHAR(50)                                         NULL COMMENT '가상계좌 웹훅 정상 요청 검증',

    -- 휴대폰 결제 --
    customer_mobile_phone       VARCHAR(13)                                         NULL COMMENT '결제에 사용된 휴대폰',
    mobile_settlement_status    smallint                                            NULL COMMENT '핸드폰 결제 성산 상태',
    mobile_receipt_url          varchar(2000)                                       NULL COMMENT '휴대폰 결제 내역 영수증 URL',

    -- 상품권 결제 --
    gift_approve_no             VARCHAR(8)                                          NULL COMMENT '결제 승인 번호(최대 8자)',
    gift_settlement_status      ENUM ('COMPLETED', 'INCOMPLETED')                   NULL COMMENT '상품권 결제 정산 상태',

    -- 계좌 이체 --
    transfer_bank               ENUM (
        'KYONGNAMBANK', 'GWANGJUBANK', 'LOCALNONGHYEOP', 'BUSANBANK', 'SAEMAUL',
        'SANLIM', 'SHINHAN', 'SHINHYEOP', 'CITI', 'WOORI', 'POST', 'SAVINGBANK',
        'JEONBUKBANK', 'JEJUBANK', 'KAKAOBANK', 'KBANK', 'TOSSBANK', 'HANA', 'HSBC',
        'IBK', 'KOOKMIN', 'DAEGUBANK', 'KDBBANK', 'NONGHYEOP', 'SC', 'SUHYEOP'
        )                                                                           NULL COMMENT '계좌이체 은행',
    transfer_settlement_status  ENUM ('COMPLETED', 'INCOMPLETED')                   NULL COMMENT '계좌이체 결제 정산 상태',

    receipt_url                 VARCHAR(2000)                                       NULL COMMENT '영수증 확인 URL',

    -- 간편 결제 --
    provider                    VARCHAR(5)                                          NULL COMMENT '간편 결제사',
    easy_pay_amount             DECIMAL(38, 2)                                      NULL COMMENT '간편결제 서비스에 등록된 계좌/현금성 포인트로 결제한 금액',
    easy_pay_discounted_amount  DEC(38, 2)                                          NULL COMMENT '간편결제 서비스에 등록된 계좌/현금성 포인트로 결제한 금액',

    country                     VARCHAR(2)                                          NOT NULL COMMENT '국가 코드(최대 2자)',
    discounted_amount           DECIMAL(38, 2)                                      NULL COMMENT '카드사의 즉시 할인 프로모션 적용 금액',

    created_by                  BIGINT                                              NOT NULL COMMENT '생성자',
    create_at                   DATETIME(6)                                         NOT NULL COMMENT '생성일',
    last_modified_by            BIGINT                                              NULL COMMENT '최종 수정자',
    last_modified_at            DATETIME(6)                                         NULL COMMENT '최종 수정일',
    deleted                     BIT                                                 NOT NULL COMMENT '삭제 유무'
) ENGINE = InnoDB COMMENT '토스 결제';

CREATE TABLE order_payment_history
(
    id         BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '주문/결제 히스토리 PK',
    created_at BIGINT                            NOT NULL COMMENT '생성일'
) ENGINE = InnoDB COMMENT '주문/결제 히스토리';

CREATE TABLE orders_aud
(
    id                     BIGINT                                                        NOT NULL COMMENT '주문 PK',
    rev                    BIGINT                                                        NOT NULL COMMENT '주문/결제 히스토리 PK',
    revtype                TINYINT                                                       NOT NULL COMMENT '변경 상태',
    user_id                BIGINT                                                        NOT NULL COMMENT '사용자 PK',
    reservation_id         BIGINT                                                        NULL COMMENT '예약 PK',
    unique_id              BINARY(16)                                                    NOT NULL COMMENT '주문 조회 노출용 아이디',
    title                  VARCHAR(50)                                                   NOT NULL COMMENT '주문명',
    total_price            DECIMAL(38, 2)                                                NULL COMMENT '총 주문 가격',
    discounted_total_price DECIMAL(38, 2)                                                NULL COMMENT '할인된 주문 가격',
    pay_type               ENUM ('CARD', 'KAKAO_PAY', 'NAVER_PAY', 'TOSS', 'REMITTANCE') NOT NULL COMMENT '결제 수단',
    order_status           ENUM ('PAYMENT_REQUEST', 'COMPLETE', 'CANCEL', 'REFUND')      NOT NULL COMMENT '주문 상태',
    deposit_deadline       DATETIME(6)                                                   NOT NULL COMMENT '입금 마감일',
    refund_reason          VARCHAR(1000)                                                 NULL COMMENT '환불 사유',
    created_by             BIGINT                                                        NOT NULL COMMENT '생성자',
    last_modified_by       BIGINT                                                        NULL COMMENT '최종 수정일',
    deleted                BIGINT                                                        NOT NULL COMMENT '삭제 유무',
    PRIMARY KEY (rev, id),
    CONSTRAINT fk_order_aud_order_payment_history_id
        FOREIGN KEY (rev) REFERENCES order_payment_history (id)
) ENGINE = InnoDB COMMENT '주문/결제 히스토리';

CREATE TABLE order_line_aud
(
    id               BIGINT         NOT NULL COMMENT '주문 상세 PK',
    rev              BIGINT         NOT NULL COMMENT '주문/결제 히스토리 PK',
    revtype          TINYINT        NOT NULL COMMENT '변경 상태',

    order_id         BIGINT         NOT NULL COMMENT '주문 PK',
    study_cafe_id    BIGINT         NOT NULL COMMENT '스터디 카페 PK',
    product_id       BIGINT         NOT NULL COMMENT '상품 PK',
    price            DECIMAL(38, 2) NOT NULL COMMENT '가격',

    created_by       BIGINT         NOT NULL COMMENT '생성자',
    last_modified_by BIGINT         NULL COMMENT '최종 수정일',
    deleted          BIGINT         NOT NULL COMMENT '삭제 유무',
    PRIMARY KEY (rev, id),
    CONSTRAINT fk_order_line_aud_order_payment_history_id
        FOREIGN KEY (rev) REFERENCES order_payment_history (id)
) ENGINE = InnoDB COMMENT '주문 상세 히스토리';

CREATE TABLE payment_aud
(
    id                          BIGINT                                              NOT NULL COMMENT '결제 PK',
    rev                         BIGINT                                              NOT NULL COMMENT '주문/결제 히스토리 PK',
    revtype                     TINYINT                                             NOT NULL COMMENT '변경 상태',
    version                     VARCHAR(10)                                         NOT NULL COMMENT '토스 결제 시스템 버전',
    payment_key                 VARCHAR(200)                                        NOT NULL COMMENT '결제 키 값(최대 2000자)',
    payment_type                ENUM ('NORMAL', 'BILLING', 'BRANDPAY')              NOT NULL COMMENT '결제 타입 정보. NOTMAL(일반 결제), BILLING(자동 결제), BRANDPAY(브랜드페이)',
    order_id                    BIGINT                                              NOT NULL COMMENT '주문 PK',
    order_name                  VARCHAR(100)                                        NOT NULL COMMENT '주문명',
    m_id                        VARCHAR(14)                                         NULL COMMENT '상점 아이디(최대 14자)',
    currency                    VARCHAR(255)                                        NULL COMMENT '결제시 사용한 통화',
    method                      ENUM (
        'CARD', 'VIRTUAL_ACCOUNT', 'SIMPLE_PAYMENT', 'MOBILE_PHONE',
        'ACCOUNT_TRANSFER','CULTURAL_GIFT_CERTIFICATE',
        'BOOK_CULTURE_GIFT_CERTIFICATE', 'GAME_CULTURE_GIFT_CERTIFICATE'
        )                                                                           NOT NULL COMMENT '결제 수단',
    total_amount                DECIMAL(38, 2)                                      NOT NULL COMMENT '총 결제 금액',
    balance_amount              DECIMAL(38, 2)                                      NOT NULL COMMENT '취소할 수 있는 금액(잔고)',
    payment_status              ENUM (
        'READY', 'IN_PROGRESS', 'WAITING_FOR_DEPOSIT', 'DONE',
        'CANCELED','PARTIAL_CANCELED', 'ABORTED', 'EXPIRED'
        )                                                                           NOT NULL COMMENT '결제 처리 상태',
    requested_at                DATETIME(6)                                         NOT NULL COMMENT '결제가 발생 날짜',
    approved_at                 DATETIME(6)                                         NOT NULL COMMENT '끝나는 승인 날짜',
    use_escrow                  BIT                                                 NOT NULL COMMENT '에스크로 사용 여부',
    last_transaction_key        VARCHAR(64)                                         NULL COMMENT '마지막 거래 키(최대 64)',
    supplied_amount             DECIMAL(38, 2)                                      NOT NULL COMMENT '공급가액',
    vat                         DECIMAL(38, 2)                                      NOT NULL COMMENT '부가세',
    culture_expense             BIT                                                 NOT NULL COMMENT '문화비',
    tax_free_amount             DECIMAL(38, 2)                                      NOT NULL COMMENT '면세 금액',
    tax_exemption_amount        DECIMAL(38, 2)                                      NOT NULL COMMENT '과세 제외 결제 금액',
    is_partial_cancelable       BIT                                                 NULL COMMENT '부분 취소 가능 여부',

    -- 카드 --
    card_amount                 DECIMAL(38, 2) DEFAULT 0                            NULL COMMENT '카드 결제 요청 금액',
    card_issuer_company         ENUM (
        'HYUNDAI', 'SAMSUNG', 'LOTTE', 'TOSSBANK', 'HANA', 'KOOKMIN',
        'BC', 'NONGHYEOP', 'KAKAOBANK', 'KBANK', 'IBK_BC', 'CITI',
        'SAEMAUL', 'POST', 'SUHYEOP', 'SHINHYEOP','GWANGJUBANK',
        'JEONBUKBANK', 'KDBBANK', 'JEJUBANK', 'SAVINGBANK', 'WOORI',
        'VISA', 'MASTER', 'UNIONPAY'
        )                                                                           NULL COMMENT '카드 발급사',
    card_acquirer_comany        ENUM (
        'HYUNDAI', 'SAMSUNG', 'LOTTE', 'TOSSBANK', 'HANA', 'KOOKMIN',
        'BC', 'NONGHYEOP', 'KAKAOBANK', 'KBANK', 'IBK_BC', 'CITI',
        'SAEMAUL', 'POST', 'SUHYEOP', 'SHINHYEOP''GWANGJUBANK',
        'JEONBUKBANK', 'KDBBANK', 'JEJUBANK', 'SAVINGBANK',
        'WOORI', 'VISA', 'MASTER', 'UNIONPAY'
        )                                                                           NULL COMMENT '카드 매입사',
    card_number                 VARCHAR(255)                                        NULL COMMENT '카드 번호',
    installment_plan_months     INTEGER                                             NULL COMMENT '할부 개월 수',
    card_approve_no             VARCHAR(8)                                          NULL COMMENT '카드사 승인 번호(최대 8자)',
    use_card_point              BIT                                                 NULL COMMENT '카드사 포인트 사용 여부',
    card_type                   ENUM ('CREDIT', 'CHECK', 'GIFT', 'UN_IDENTIFIED')   NULL COMMENT '카드 종류(신용, 체크, 기프트, 미확인)',
    owner_type                  ENUM ('INDIVIDUAL', 'CORPORATION', 'UN_IDENTIFIED') NULL COMMENT '카드 소유자 타입(개인, 법인, 미확인)',
    acquire_status              ENUM (
        'READY', 'REQUESTED', 'COMPLETED', 'CANCEL_REQUESTED', 'CANCELED'
        )                                                                           NULL COMMENT '카드 결제 매입 상태',

    is_interest_free            BIT                                                 NULL COMMENT '무이자 할부 적용 여부',
    interest_payer              ENUM ('BUYER', 'CARD_COMPANY', 'MERCHANT')          NULL COMMENT '할부 수수료 부담 주체',

    -- 가상 계좌 --
    account_type                ENUM ('GENERAL', 'FIXED')                           NULL COMMENT '가상 계좌 유형(일반, 고정)',
    account_number              VARCHAR(20)                                         NULL COMMENT '발급된 계좌 번호(최대 20자)',
    virtual_account_bank        ENUM (
        'KYONGNAMBANK', 'GWANGJUBANK', 'LOCALNONGHYEOP', 'BUSANBANK', 'SAEMAUL',
        'SANLIM', 'SHINHAN', 'SHINHYEOP', 'CITI', 'WOORI', 'POST', 'SAVINGBANK',
        'JEONBUKBANK', 'JEJUBANK', 'KAKAOBANK', 'KBANK', 'TOSSBANK', 'HANA', 'HSBC',
        'IBK', 'KOOKMIN', 'DAEGUBANK', 'KDBBANK', 'NONGHYEOP', 'SC', 'SUHYEOP'
        )                                                                           NULL COMMENT '가상계좌 은행',
    customer_name               VARCHAR(13)                                         NULL COMMENT '가상계좌 발급 고객 이름',
    due_date                    DATETIME(6)                                         NULL COMMENT '입금 기한',
    refund_status               enum (
        'NONE', 'PENDING', 'FAILED', 'PARTIAL_FAILED', 'COMPLETED'
        )                                                                           NULL COMMENT '환불 처리 상태',
    expired_date                BIT                                                 NULL COMMENT '가상계좌 만료 여부',
    v_account_settlement_status ENUM ('COMPLETED', 'INCOMPLETED')                   NULL COMMENT '가상계좌 결제 정산 상태',
    holder_name                 VARCHAR(13)                                         NULL COMMENT '예금주 정보',
    refund_account_number       VARCHAR(255)                                        NULL COMMENT '환불계좌',
    refund_bank                 ENUM (
        'KYONGNAMBANK', 'GWANGJUBANK', 'LOCALNONGHYEOP', 'BUSANBANK', 'SAEMAUL',
        'SANLIM', 'SHINHAN', 'SHINHYEOP', 'CITI', 'WOORI', 'POST', 'SAVINGBANK',
        'JEONBUKBANK', 'JEJUBANK', 'KAKAOBANK', 'KBANK', 'TOSSBANK', 'HANA', 'HSBC',
        'IBK', 'KOOKMIN', 'DAEGUBANK', 'KDBBANK', 'NONGHYEOP', 'SC', 'SUHYEOP'
        )                                                                           NULL COMMENT '가상계좌 은행',

    secret                      VARCHAR(50)                                         NULL COMMENT '가상계좌 웹훅 정상 요청 검증',

    -- 휴대폰 결제 --
    customer_mobile_phone       VARCHAR(13)                                         NULL COMMENT '결제에 사용된 휴대폰',
    mobile_settlement_status    smallint                                            NULL COMMENT '핸드폰 결제 성산 상태',
    mobile_receipt_url          varchar(2000)                                       NULL COMMENT '휴대폰 결제 내역 영수증 URL',

    -- 상품권 결제 --
    gift_approve_no             VARCHAR(8)                                          NULL COMMENT '결제 승인 번호(최대 8자)',
    gift_settlement_status      ENUM ('COMPLETED', 'INCOMPLETED')                   NULL COMMENT '상품권 결제 정산 상태',

    -- 계좌 이체 --
    transfer_bank               ENUM (
        'KYONGNAMBANK', 'GWANGJUBANK', 'LOCALNONGHYEOP', 'BUSANBANK', 'SAEMAUL',
        'SANLIM', 'SHINHAN', 'SHINHYEOP', 'CITI', 'WOORI', 'POST', 'SAVINGBANK',
        'JEONBUKBANK', 'JEJUBANK', 'KAKAOBANK', 'KBANK', 'TOSSBANK', 'HANA', 'HSBC',
        'IBK', 'KOOKMIN', 'DAEGUBANK', 'KDBBANK', 'NONGHYEOP', 'SC', 'SUHYEOP'
        )                                                                           NULL COMMENT '계좌이체 은행',
    transfer_settlement_status  ENUM ('COMPLETED', 'INCOMPLETED')                   NULL COMMENT '계좌이체 결제 정산 상태',

    receipt_url                 VARCHAR(2000)                                       NULL COMMENT '영수증 확인 URL',

    -- 간편 결제 --
    provider                    VARCHAR(5)                                          NULL COMMENT '간편 결제사',
    easy_pay_amount             DECIMAL(38, 2)                                      NULL COMMENT '간편결제 서비스에 등록된 계좌/현금성 포인트로 결제한 금액',
    easy_pay_discounted_amount  DEC(38, 2)                                          NULL COMMENT '간편결제 서비스에 등록된 계좌/현금성 포인트로 결제한 금액',

    country                     VARCHAR(2)                                          NOT NULL COMMENT '국가 코드(최대 2자)',
    discounted_amount           DECIMAL(38, 2)                                      NULL COMMENT '카드사의 즉시 할인 프로모션 적용 금액',

    created_by                  BIGINT                                              NOT NULL COMMENT '생성자',
    last_modified_by            BIGINT                                              NULL COMMENT '최종 수정일',
    deleted                     BIGINT                                              NOT NULL COMMENT '삭제 유무',
    PRIMARY KEY (rev, id),
    CONSTRAINT fk_payment_aud_order_payment_history_id
        FOREIGN KEY (rev) REFERENCES order_payment_history (id)
) ENGINE = InnoDB COMMENT '주문/결제 히스토리';
