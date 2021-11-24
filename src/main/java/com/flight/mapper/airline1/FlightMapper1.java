package com.flight.mapper.airline1;

import com.flight.domain.dao.PlaneSeatStruct;
import com.flight.domain.dto.Airport;
import com.flight.domain.dto.FlightDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

/**
 * @author sunlongfei
 */
@Mapper
public interface FlightMapper1 {

    /**
     * 查询符合条件的航班
     */
    @Select("SELECT flight.plane_type_id AS planeTypeId, flight.id, plane_type.name AS planeTypeName, start_time AS startTime, end_time AS endTime, cost,"
                + "from_airport_id AS fromAirportId, to_airport_id AS toAirportId, transit_time AS transitTime, "
                + "(SELECT COUNT(*) FROM plane_seat_struct WHERE plane_seat_struct.plane_type_id = flight.plane_type_id "
                + "AND plane_seat_struct.id NOT IN (SELECT flight_record.plane_seat_struct_id FROM flight_record)) AS seatNum,"
                + "(SELECT airport.name FROM airport WHERE airport.id = from_airport_id) AS fromAirport, (SELECT airport.name FROM airport WHERE airport.id = to_airport_id) AS toAirport "
                + "FROM flight, plane_type WHERE plane_type_id = plane_type.id "
                + "AND (from_airport_id IN (SELECT airport.id FROM airport WHERE airport.name = #{fromAirport}) "
                + "OR to_airport_id IN (SELECT airport.id FROM airport WHERE airport.name = #{toAirport})) AND TO_DAYS(start_time) = #{days})")
    List<FlightDetail> queryFlights(String fromAirport, String toAirport, long days);

    /**
     * 查询某用户坐过该公司航班的次数
     */
    @Select("SELECT COUNT(*) FROM flight_record, passenger WHERE passenger_id = passenger.id AND id_card = #{idCardNum}")
    Integer queryNumOfTaking(String idCardNum);

    /**
     * 查询所有机场
     */
    @Select("SELECT * FROM airport")
    @Result(column = "transit_time", property = "transitTime")
    List<Airport> queryAirports();

    /**
     * 查询航班详情
     */
    @Select("SELECT flight.id, plane_type.name AS planeTypeName, start_time AS startTime, end_time AS endTime, cost,"
                + "from_airport_id AS fromAirportId, to_airport_id AS toAirportId, transit_time AS transitTime, "
                + "(SELECT COUNT(*) FROM plane_seat_struct WHERE plane_seat_struct.plane_type_id = flight.plane_type_id "
                + "AND plane_seat_struct.id NOT IN (SELECT flight_record.plane_seat_struct_id FROM flight_record)) AS seatNum,"
                + "(SELECT airport.name FROM airport WHERE airport.id = from_airport_id) AS fromAirport, (SELECT airport.name FROM airport WHERE airport.id = to_airport_id) AS toAirport "
                + "FROM flight, plane_type WHERE plane_type_id = plane_type.id "
                + "AND flight.id = #{id}")
    FlightDetail queryFlightDetail(Integer id);

    /**
     * 查询可用的座位信息
     */
    @Select("SELECT * FROM plane_seat_struct WHERE plane_type_id = #{planeTypeId} AND id NOT IN (SELECT plane_seat_struct_id)")
    @Result(column = "plane_type_id", property = "planeTypeId")
    List<PlaneSeatStruct> queryAvailableSeats(Integer flightId, String type, Integer planeTypeId);
}
