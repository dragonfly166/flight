package com.flight.domain.result;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 航班详情项
 * @author sunlongfei
 */
@Data
@AllArgsConstructor
public class FlightDetailItem {

    private Integer flightId;

    private String airline;

    private String planeTypeName;

    private Integer cost;

    private Integer remain;
}