package com.flight.domain.result;

import lombok.Data;

/**
 * 航班详情返回结果项
 * @author sunlongfei
 */
@Data
public class FlightDetailItem {

    private Integer flightId;

    private String airline;

    private Integer planeTypeId;

    private String planeTypeName;

    private String type;

    private Integer cost;

    private Integer remain;
}