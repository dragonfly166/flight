package com.flight.domain.request;

import lombok.Data;

/**
 * 请求的id和公司名信息
 * @author sunlongfei
 */
@Data
public class FlightIdInfo {

    Integer id;

    String airline;
}
