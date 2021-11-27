package com.flight.domain.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 预定机票返回信息
 * @author sunlongfei
 */
@Data
@AllArgsConstructor
public class OrderInfo {

    private String airline;

    private String idCardNum;

    private Integer recordId;
}
