package com.flight.domain.dto;

import lombok.Data;

/**
 * 机场部分信息
 * @author sunlongfei
 */
@Data
public class Airport {

  private String name;

  private String city;

  private String country;

  private Integer transitTime;
}
