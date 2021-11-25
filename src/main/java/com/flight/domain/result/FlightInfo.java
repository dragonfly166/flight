package com.flight.domain.result;

import java.sql.Timestamp;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 单次航班信息
 * @author sunlongfei
 */
@Data
@AllArgsConstructor
public class FlightInfo {

    private Integer flightId;

    private String airline;

    private String fromAirport;

    private String toAirport;

    private Integer planeTypeId;

    private String planeTypeName;

    private String startTime;

    private String endTime;

    private Integer seatNum;
}
