package com.flight.service;

import com.flight.config.AirlineConfig;
import com.flight.domain.dao.PlaneSeatStruct;
import com.flight.domain.dto.Airport;
import com.flight.domain.dto.FlightDetail;
import com.flight.domain.request.FlightDetailRequest;
import com.flight.domain.result.FlightDetailItem;
import com.flight.domain.result.FlightInfo;
import com.flight.domain.result.FlightItem;
import com.flight.mapper.airline1.FlightMapper1;
import com.flight.mapper.airline2.FlightMapper2;
import com.flight.mapper.airline3.FlightMapper3;
import com.flight.util.UserUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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
     * 获取航班列表折扣
     * 单人购票每购买一次票，指定航班公司所有票减1%
     * 团体购票有多少人则减百分之多少
     * 至多减50%
     */
    private List<FlightDetail> getDiscount(List<FlightDetail> flights1, List<FlightDetail> flights2, List<FlightDetail> flights3) {
        int discount1, discount2, discount3;
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

        List<FlightDetail> flights = new ArrayList<>(flights1);
        flights.addAll(flights2);
        flights.addAll(flights3);

        return flights;
    }

    /**
     * 获取航班详情折扣
     * 单人购票每购买一次票，指定航班公司所有票减1%
     * 团体购票有多少人则减百分之多少
     * 至多减50%
     */
    public List<FlightDetailItem> getDiscount(List<FlightDetailItem> detailItems) {
        for (FlightDetailItem item: detailItems) {
            int discount;
            if (UserUtil.getUsers().size() > 1) {
                discount = UserUtil.getUsers().size();
            } else {
                if (airlineConfig.getAirline1().equals(item.getAirline())) {
                    discount = flightMapper1.queryNumOfTaking(UserUtil.getUsers().get(0).getIdCardNum());
                } else if (airlineConfig.getAirline2().equals(item.getAirline())) {
                    discount = flightMapper2.queryNumOfTaking(UserUtil.getUsers().get(0).getIdCardNum());
                } else {
                    discount = flightMapper3.queryNumOfTaking(UserUtil.getUsers().get(0).getIdCardNum());
                }
            }

            item.setCost(item.getCost() * (100 - discount) / 100);
        }

        return detailItems;
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
            if (first != null && first.getTransitTime() * 60 * 1000 < (first.getStartTime().getTime() - first.getEndTime().getTime()) % (24 * 60 * 60 * 1000)) {
                List<FlightDetail> flightsForRoute = new ArrayList<>();
                flightsForRoute.add(first);
                flightsForRoute.add(flight);
                flightsList.add(flightsForRoute);
            }
        }

        for (List<FlightDetail> flightsForRoute: flightsList) {
            List<FlightInfo> flightInfos = new ArrayList<>(2);
            Integer cost = 0;

            for (FlightDetail flight: flightsForRoute) {
                FlightInfo flightInfo = new FlightInfo(flight.getId(), flight.getAirline(), fromAirport,
                    toAirport, flight.getPlaneTypeId(), flight.getPlaneTypeName(), flight.getStartTime(),
                    flight.getEndTime(), flight.getSeatNum());
                flightInfos.add(flightInfo);
                cost += flight.getCost();
            }

            long startTime = flightInfos.get(0).getStartTime().getTime() % (24 * 60 * 60 * 1000);
            long endTime = flightInfos.get(0).getEndTime().getTime() % (24 * 60 * 60 * 1000);
            if (flightInfos.size() == 2) {
                endTime = flightInfos.get(1).getEndTime().getTime() % (24 * 60 * 60 * 1000);
            }

            routes.add(new FlightItem(flightInfos, (int) (endTime - startTime) / (1000 * 60), cost));
        }

        return routes;
    }

    /**
     * 获取航班列表
     */
    public List<FlightItem> getList(String fromAirport, String toAirport, String time) {
        List<FlightDetail> flights1 = flightMapper1.queryFlights(fromAirport, toAirport, time);
        for (FlightDetail flight: flights1) {
            flight.setAirline(airlineConfig.getAirline1());
        }
        List<FlightDetail> flights2 = flightMapper2.queryFlights(fromAirport, toAirport, time);
        for (FlightDetail flight: flights2) {
            flight.setAirline(airlineConfig.getAirline2());
        }
        List<FlightDetail> flights3 = flightMapper3.queryFlights(fromAirport, toAirport, time);
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

        Set<Airport> airportSet = new TreeSet<>(airports1);
        airportSet.addAll(airports2);
        airportSet.addAll(airports3);
        return airportSet;
    }

    /**
     * 获取航班详情
     */
    public List<FlightDetailItem> getFlightsDetail(List<FlightDetailRequest> requestList) {
        List<FlightDetailItem> detailItems = new ArrayList<>(requestList.size() * 5);
        for (FlightDetailRequest request: requestList) {
            List<FlightDetailItem> flightItems;
            if (airlineConfig.getAirline1().equals(request.getAirline())) {
                flightItems = flightMapper1.queryFlightDetail(request.getId(), request.getTime());
            } else if (airlineConfig.getAirline2().equals(request.getAirline())) {
                flightItems = flightMapper2.queryFlightDetail(request.getId(), request.getTime());
            } else {
                flightItems = flightMapper3.queryFlightDetail(request.getId(), request.getTime());
            }
            detailItems.addAll(flightItems);
        }

        return getDiscount(detailItems);
    }

    /**
     * 查询可用的座位
     */
    public List<PlaneSeatStruct> getAvailableSeats(Integer flightId, String airline, String type, Integer planeTypeId, String time) {
        List<PlaneSeatStruct> seats;

        if (airlineConfig.getAirline1().equals(airline)) {
            seats = flightMapper1.queryAvailableSeats(flightId, type, planeTypeId, time);
        } else if (airlineConfig.getAirline2().equals(airline)) {
            seats = flightMapper2.queryAvailableSeats(flightId, type, planeTypeId, time);
        } else {
            seats = flightMapper3.queryAvailableSeats(flightId, type, planeTypeId, time);
        }

        return seats;
    }
}
