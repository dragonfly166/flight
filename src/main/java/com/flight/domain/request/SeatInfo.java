package com.flight.domain.request;

import lombok.Data;

/**
 * 值机请求信息
 * @author sunlongfei
 */
@Data
public class SeatInfo {

    private String idCardNum;

    private Integer row;

    private Integer column;
}
