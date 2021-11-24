package com.flight.controller;

import com.flight.domain.dao.PlaneSeatStruct;
import com.flight.domain.dto.Airport;
import com.flight.domain.request.FlightIdInfo;
import com.flight.domain.result.FlightDetailItem;
import com.flight.domain.result.FlightItem;
import com.flight.result.ApiResult;
import com.flight.service.FlightService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import javax.validation.constraints.NotBlank;
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
    public ApiResult<List<FlightItem>> list(@NotBlank(message = "fromAirportId不能为空") @RequestParam("fromAirport") String fromAirport,
        @NotBlank(message = "toAirportId不能为空") @RequestParam("toAirport") String toAirport,
        @NotBlank(message = "time不能为空") @RequestParam("time") String time) throws ParseException {

        List<FlightItem> routes = flightService.getList(fromAirport, toAirport, time);

        return ApiResult.success(routes);
    }

    /**
     * 获取所有机场信息
     */
    @GetMapping("/airport")
    public ApiResult<Set<Airport>> airport() {
        Set<Airport> airports = flightService.getAirports();

        return ApiResult.success(airports);
    }

    /**
     * 航班详情
     */
    @GetMapping("/detail")
    public ApiResult<List<FlightDetailItem>> detail(@NotBlank(message = "flights不能为空") @RequestParam("flights") String flights) {
        Gson gson = new Gson();
        List<FlightIdInfo> idInfoList =
            gson.fromJson(flights, new TypeToken<List<FlightIdInfo>>() {}.getType());

        List<FlightDetailItem> flightsDetail = flightService.getFlightsDetail(idInfoList);

        return ApiResult.success(flightsDetail);
    }

    /**
     * 查看可用的座位
     */
    @GetMapping("/seat")
    public ApiResult<List<PlaneSeatStruct>> seat(@RequestParam("flightId") Integer flightId,
        @NotBlank(message = "airline不能为空") @RequestParam("airline") String airline,
        @NotBlank(message = "type不能为空") @RequestParam("type") String type,
        @RequestParam("planeTypeId") Integer planeTypeId) {

        List<PlaneSeatStruct> seats = flightService.getAvailableSeats(flightId, airline, type, planeTypeId);

        return ApiResult.success(seats);
    }
}
