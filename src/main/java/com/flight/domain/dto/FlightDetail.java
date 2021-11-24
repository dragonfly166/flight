package com.flight.domain.dto;

import java.sql.Timestamp;
import lombok.Data;

/**
 * 航班信息
 * @author sunlongfei
 */
@Data
public class FlightDetail {

  private Integer id;

  private Timestamp startTime;

  private Timestamp endTime;

  private Integer cost;

  private String fromAirport;

  private String toAirport;

  private Integer transitTime;

  private Integer seatNum;

  private Integer planeTypeId;

  private String planeTypeName;

  private String airline;
}
