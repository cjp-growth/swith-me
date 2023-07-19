CREATE TABLE `order`
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY                        NOT NULL COMMENT '주문 PK',
    user_id          BIGINT                                                   NOT NULL COMMENT '사용자 PK',
    deposit_deadline DATETIME(6)                                              NOT NULL COMMENT '입금 마감일',
    order_status     ENUM ('PAYMENT_REQUEST', 'COMPLETE', 'CANCEL', 'REFUND') NOT NULL COMMENT '주문 상태',
    refund_reason    VARCHAR(1000)                                            NOT NULL COMMENT '환불 이유',
    created_by       BIGINT                                                   NOT NULL COMMENT '생성자',
    create_at        DATETIME(6)                                              NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                                                   NULL COMMENT '최종 수정자',
    last_modified_at DATETIME(6)                                              NULL COMMENT '최종 수정일',
    deleted          BIT                                                      NOT NULL COMMENT '삭제 유무'
) ENGINE = InnoDB COMMENT '주문';

CREATE TABLE order_line
(
    id               BIGINT AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '주문 상세 PK',
    order_id         BIGINT                            NOT NULL COMMENT '주문 PK',
    study_cafe_id    BIGINT                            NOT NULL COMMENT '스터디 카페 PK',
    product_id       BIGINT                            NOT NULL COMMENT '상품 PK',
    locker_id        BIGINT                            NULL COMMENT '사물함 PK',
    price            DECIMAL(38, 2)                    NOT NULL COMMENT '가격',
    created_by       BIGINT                            NOT NULL COMMENT '생성자',
    create_at        DATETIME(6)                       NOT NULL COMMENT '생성일',
    last_modified_by BIGINT                            NULL COMMENT '최종 수정자',
    last_modified_at DATETIME(6)                       NULL COMMENT '최종 수정일',
    deleted          BIT                               NOT NULL COMMENT '삭제 유무',

    CONSTRAINT fk_order_line_order_id
        FOREIGN KEY (order_id) REFERENCES `order` (id)
) ENGINE = InnoDB COMMENT '주문 상세';