package com.flight.domain.result;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 航班列表返回结果项
 * @author sunlongfei
 */
@Data
@AllArgsConstructor
public class FlightItem {

    private List<FlightInfo> flights;

    private Integer time;

    private Integer cost;
}
