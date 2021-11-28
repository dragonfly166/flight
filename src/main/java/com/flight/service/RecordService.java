package com.flight.service;

import com.flight.config.AirlineConfig;
import com.flight.domain.dao.Passenger;
import com.flight.domain.dto.AutoIncreasedId;
import com.flight.domain.request.SeatInfo;
import com.flight.domain.result.OrderInfo;
import com.flight.mapper.airline1.RecordMapper1;
import com.flight.mapper.airline2.RecordMapper2;
import com.flight.mapper.airline3.RecordMapper3;
import com.flight.util.UserUtil;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author sunlongfei
 */
@Service
public class RecordService {

    @Autowired
    private RecordMapper1 recordMapper1;

    @Autowired
    private RecordMapper2 recordMapper2;

    @Autowired
    private RecordMapper3 recordMapper3;

    @Autowired
    private AirlineConfig airlineConfig;

    @Autowired
    private AutoIncreasedId autoIncreasedId;

    /**
     * 预定机票并返回预定信息
     */
    @Transactional(rollbackFor = Exception.class)
    public List<OrderInfo> order(String airline, Integer flightId,String time) {
        List<OrderInfo> orderInfos = new ArrayList<>(UserUtil.getUsers().size());

        for (Passenger passenger: UserUtil.getUsers()) {
            if (airlineConfig.getAirline1().equals(airline)) {
                recordMapper1.insertPassenger(passenger.getName(), passenger.getIdCardNum(), autoIncreasedId);
                recordMapper1.insertRecord(flightId, time, autoIncreasedId.getId(), autoIncreasedId);
            } else if (airlineConfig.getAirline2().equals(airline)) {
                recordMapper2.insertPassenger(passenger.getName(), passenger.getIdCardNum(), autoIncreasedId);
                recordMapper2.insertRecord(flightId, time, autoIncreasedId.getId(), autoIncreasedId);
            } else {
                recordMapper3.insertPassenger(passenger.getName(), passenger.getIdCardNum(), autoIncreasedId);
                recordMapper3.insertRecord(flightId, time, autoIncreasedId.getId(), autoIncreasedId);
            }

            orderInfos.add(new OrderInfo(airline, passenger.getName(), passenger.getIdCardNum(), autoIncreasedId.getId()));
        }

        return orderInfos;
    }

    /**
     * 值机
     */
    @Transactional(rollbackFor = Exception.class)
    public void checkin(String airline, Integer planeTypeId, List<SeatInfo> seatInfos) {
        if (seatInfos == null) {
            return;
        }

        for (SeatInfo seatInfo: seatInfos) {
            if (airlineConfig.getAirline1().equals(airline)) {
                recordMapper1.updateSeatId(seatInfo.getRecordId(), planeTypeId, seatInfo.getSeatId());
            } else if (airlineConfig.getAirline2().equals(airline)) {
                recordMapper2.updateSeatId(seatInfo.getRecordId(), planeTypeId, seatInfo.getSeatId());
            } else {
                recordMapper3.updateSeatId(seatInfo.getRecordId(), planeTypeId, seatInfo.getSeatId());
            }
        }
    }

    /**
     * 退票
     */
    public void refund(String airline, Integer recordId) {
        if (airlineConfig.getAirline1().equals(airline)) {
            recordMapper1.deleteRecord(recordId);
        } else if (airlineConfig.getAirline2().equals(airline)) {
            recordMapper2.deleteRecord(recordId);
        } else {
            recordMapper3.deleteRecord(recordId);
        }
    }
}
