package com.flight.domain.dto;

import lombok.Data;

/**
 * 机场部分信息
 * @author sunlongfei
 */
@Data
public class Airport implements Comparable<Airport> {

  private String name;

  private String city;

  private String country;

  private Integer transitTime;

  @Override
  public int compareTo(Airport airport) {
    return name.compareTo(airport.getName());
  }
}
