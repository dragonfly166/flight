package com.flight.domain.dto;

import java.sql.Timestamp;
import lombok.Data;

/**
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

  private String planeTypeName;

  private String airline;
}
