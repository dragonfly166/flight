package com.flight.controller;

import com.flight.domain.result.OrderInfo;
import com.flight.result.ApiResult;
import com.flight.service.RecordService;
import java.util.List;
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
@RequestMapping("/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    /**
     * 预定航班
     */
    @GetMapping("/order")
    public ApiResult<List<OrderInfo>> order(@NotBlank(message = "airline不能为空") @RequestParam("airline") String airline,
        @RequestParam("flightId") Integer flightId,
        @NotBlank(message = "time") @RequestParam("time") String time) {

        List<OrderInfo> orderInfos = recordService.order(airline, flightId, time);

        return ApiResult.success(orderInfos);
    }
}
