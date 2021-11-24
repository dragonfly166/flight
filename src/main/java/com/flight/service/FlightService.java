package com.flight.service;

import com.flight.config.AirlineConfig;
import com.flight.domain.dao.PlaneSeatStruct;
import com.flight.domain.dto.Airport;
import com.flight.domain.dto.FlightDetail;
import com.flight.domain.request.FlightIdInfo;
import com.flight.domain.result.FlightDetailItem;
import com.flight.domain.result.FlightInfo;
import com.flight.domain.result.FlightItem;
import com.flight.mapper.airline1.FlightMapper1;
import com.flight.mapper.airline2.FlightMapper2;
import com.flight.mapper.airline3.FlightMapper3;
import com.flight.util.UserUtil;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sunlongfei
 */
@Service
public class FlightService {

    @Autowired
    private AirlineConfig airlineConfig;

    @Autowired
    private FlightMapper1 flightMapper1;

    @Autowired
    private FlightMapper2 flightMapper2;

    @Autowired
    private FlightMapper3 flightMapper3;

    /**
     * 获取折扣
     * 单人购票每购买一次票，指定航班公司所有票减1%
     * 团体购票有多少人则减百分之多少
     * 至多减50%
     */
    private List<FlightDetail> getDiscount(List<FlightDetail> flights1, List<FlightDetail> flights2, List<FlightDetail> flights3) {
        int discount1 = 0, discount2 = 0, discount3 = 0;
        if (UserUtil.getUsers().size() > 1) {
            discount1 = discount2 = discount3 = UserUtil.getUsers().size();
        } else {
            String idCardNum = UserUtil.getUsers().get(0).getIdCardNum();
            discount1 = flightMapper1.queryNumOfTaking(idCardNum);
            discount2 = flightMapper2.queryNumOfTaking(idCardNum);
            discount3 = flightMapper3.queryNumOfTaking(idCardNum);
        }

        for (FlightDetail flight: flights1) {
            flight.setCost(flight.getCost() * (100 - Math.max(50, discount1)) / 100);
        }
        for (FlightDetail flight: flights2) {
            flight.setCost(flight.getCost() * (100 - Math.max(50, discount2)) / 100);
        }
        for (FlightDetail flight: flights3) {
            flight.setCost(flight.getCost() * (100 - Math.max(50, discount3)) / 100);
        }

        flights1.addAll(flights2);
        flights1.addAll(flights3);

        return flights1;
    }

    /**
     * 获取行程
     */
    private List<FlightItem> getRoute(List<FlightDetail> flights, String fromAirport, String toAirport) {
        Map<String, FlightDetail> firstFlightMap = new HashMap<>(flights.size());
        List<FlightItem> routes = new ArrayList<>(flights.size());
        List<List<FlightDetail>> flightsList = new ArrayList<>(flights.size());

        for (FlightDetail flight: flights) {
            if (flight.getFromAirport().equals(fromAirport) && flight.getToAirport().equals(toAirport)) {
                List<FlightDetail> flightsForRoute = new ArrayList<>();
                flightsForRoute.add(flight);
                flightsList.add(flightsForRoute);
                continue;
            }
            if (flight.getFromAirport().equals(fromAirport)) {
                firstFlightMap.put(flight.getToAirport(), flight);
            }
        }
        for (FlightDetail flight: flights) {
            FlightDetail first = firstFlightMap.get(flight.getFromAirport());
            if (first != null && first.getTransitTime() < (first.getStartTime().getTime() - first.getEndTime().getTime()) / (1000 * 60)) {
                List<FlightDetail> flightsForRoute = new ArrayList<>();
                flightsForRoute.add(first);
                flightsForRoute.add(flight);
                flightsList.add(flightsForRoute);
            }
        }

        for (List<FlightDetail> flightsForRoute: flightsList) {
            List<FlightInfo> flightInfos = new ArrayList<>(2);
            Integer cost = 0;
            Integer time = 0;

            for (FlightDetail flight: flightsForRoute) {
                FlightInfo flightInfo = new FlightInfo(flight.getId(), flight.getAirline(), fromAirport,
                    toAirport, flight.getPlaneTypeName(), flight.getStartTime(), flight.getEndTime(), flight.getSeatNum());
                flightInfos.add(flightInfo);
                cost += flight.getCost();
            }
            if (flightInfos.size() == 1) {
                time = (int) (flightInfos.get(0).getEndTime().getTime() - flightInfos.get(0).getStartTime().getTime()) / (1000 * 60);
            }

            routes.add(new FlightItem(flightInfos, time, cost));
        }

        return routes;
    }

    /**
     * 获取航班列表
     */
    public List<FlightItem> getList(String fromAirport, String toAirport, String time)
        throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(time);
        long days = date.getTime() / (1000 * 60 * 60 * 24);

        List<FlightDetail> flights1 = flightMapper1.queryFlights(fromAirport, toAirport, days);
        for (FlightDetail flight: flights1) {
            flight.setAirline(airlineConfig.getAirline1());
        }
        List<FlightDetail> flights2 = flightMapper2.queryFlights(fromAirport, toAirport, days);
        for (FlightDetail flight: flights2) {
            flight.setAirline(airlineConfig.getAirline2());
        }
        List<FlightDetail> flights3 = flightMapper3.queryFlights(fromAirport, toAirport, days);
        for (FlightDetail flight: flights3) {
            flight.setAirline(airlineConfig.getAirline3());
        }

        List<FlightDetail> flights = getDiscount(flights1, flights2, flights3);

        return getRoute(flights, fromAirport, toAirport);
    }

    /**
     * 获取所有机场信息
     */
    public Set<Airport> getAirports() {
        List<Airport> airports1 = flightMapper1.queryAirports();
        List<Airport> airports2 = flightMapper2.queryAirports();
        List<Airport> airports3 = flightMapper3.queryAirports();

        Set<Airport> airportSet = new HashSet<>(airports1);
        airportSet.addAll(airports2);
        airportSet.addAll(airports3);
        return airportSet;
    }

    /**
     * 获取航班详情
     */
    public List<FlightDetailItem> getFlightsDetail(List<FlightIdInfo> idInfoList) {
        List<FlightDetail> flights = new ArrayList<>(2);
        List<FlightDetailItem> result = new ArrayList<>(2);

        FlightDetail flightDetail;
        for (FlightIdInfo idInfo: idInfoList) {
            if (airlineConfig.getAirline1().equals(idInfo.getAirline())) {
                flightDetail = flightMapper1.queryFlightDetail(idInfo.getId());
                flightDetail.setAirline(idInfo.getAirline());
                flights.add(flightDetail);
            } else if(airlineConfig.getAirline2().equals(idInfo.getAirline())) {
                flightDetail = flightMapper2.queryFlightDetail(idInfo.getId());
                flightDetail.setAirline(idInfo.getAirline());
                flights.add(flightDetail);
            } else {
                flightDetail = flightMapper3.queryFlightDetail(idInfo.getId());
                flightDetail.setAirline(idInfo.getAirline());
                flights.add(flightDetail);
            }
        }

        flights = getDiscount(flights, new ArrayList<>(0), new ArrayList<>(0));
        for (FlightDetail flight: flights) {
            FlightDetailItem item = new FlightDetailItem(flight.getId(), flight.getAirline(), flight.getPlaneTypeId(),
                flight.getPlaneTypeName(), flight.getCost(), flight.getSeatNum());
            result.add(item);
        }

        return result;
    }

    /**
     * 查询可用的座位
     */
    public List<PlaneSeatStruct> getAvailableSeats(Integer flightId, String airline, String type, Integer planeTypeId) {
        List<PlaneSeatStruct> seats;

        if (airlineConfig.getAirline1().equals(airline)) {
            seats = flightMapper1.queryAvailableSeats(flightId, type, planeTypeId);
        } else if (airlineConfig.getAirline2().equals(airline)) {
            seats = flightMapper2.queryAvailableSeats(flightId, type, planeTypeId);
        } else {
            seats = flightMapper3.queryAvailableSeats(flightId, type, planeTypeId);
        }

        return seats;
    }
}
