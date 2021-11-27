package com.flight.controller;

import com.flight.domain.request.SeatInfo;
import com.flight.domain.result.OrderInfo;
import com.flight.result.ApiResult;
import com.flight.service.RecordService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;
import javax.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sunlongfei
 */
@RestController
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private Gson gson;

    /**
     * 预定航班
     */
    @PostMapping("/order")
    public ApiResult<List<OrderInfo>> order(@NotBlank(message = "airline不能为空") @RequestParam("airline") String airline,
        @RequestParam("flightId") Integer flightId,
        @NotBlank(message = "time") @RequestParam("time") String time) {

        List<OrderInfo> orderInfos = recordService.order(airline, flightId, time);

        return ApiResult.success(orderInfos);
    }

    /**
     * 值机
     */
    @PostMapping("/checkin")
    public ApiResult<?> checkin(@RequestParam("recordId") Integer recordId,
        @NotBlank(message = "airline不能为空") @RequestParam("airline") String airline,
        @RequestParam("planeTypeId") Integer planeTypeId,
        @NotBlank(message = "seats不能为空") @RequestParam("seats") String seats) {

        List<SeatInfo> seatInfos = gson.fromJson(seats, new TypeToken<List<SeatInfo>>() {}.getType());

        recordService.checkin(recordId, airline, planeTypeId, seatInfos);

        return ApiResult.success();
    }

    /**
     * 退票
     */
    @PostMapping("/refund")
    public ApiResult<?> refund(@NotBlank(message = "airline不能为空") @RequestParam("airline") String airline,
        @RequestParam("recordId") Integer recordId) {

        recordService.refund(airline, recordId);

        return ApiResult.success();
    }
}
