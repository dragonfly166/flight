package com.flight.controller;

import com.flight.domain.dto.Airport;
import com.flight.domain.result.FlightItem;
import com.flight.result.ApiResult;
import com.flight.service.FlightService;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunlongfei
 */
@RestController
@RequestMapping("/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    /**
     * 航班列表
     */
    @GetMapping("/list")
    ApiResult<List<FlightItem>> list(@NotEmpty(message = "fromAirportId不能为空") @RequestParam("fromAirport") String fromAirport,
        @NotEmpty(message = "toAirportId不能为空") @RequestParam("toAirport") String toAirport,
        @NotEmpty(message = "time不能为空") @RequestParam("time") String time) throws ParseException {

        List<FlightItem> routes = flightService.getList(fromAirport, toAirport, time);

        return ApiResult.success(routes);
    }

    /**
     * 获取所有机场信息
     */
    @GetMapping("/airport")
    ApiResult<Set<Airport>> airport() {
        Set<Airport> airports = flightService.getAirports();

        return ApiResult.success(airports);
    }
}
