package com.flight.domain.request;

import lombok.Data;

/**
 * 航班详情请求内容
 * @author sunlongfei
 */
@Data
public class FlightDetailRequest {

    private Integer id;

    private String time;

    private String airline;
}
