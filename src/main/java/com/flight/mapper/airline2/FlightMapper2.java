package com.flight.mapper.airline2;

import com.flight.domain.dao.PlaneSeatStruct;
import com.flight.domain.dto.Airport;
import com.flight.domain.dto.FlightDetail;
import com.flight.domain.result.FlightDetailItem;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;

/**
 * @author sunlongfei
 */
@Mapper
public interface FlightMapper2 {

    /**
     * 查询符合条件的航班
     */
    @Select("SELECT flight.plane_type_id AS planeTypeId, flight.id, plane_type.name AS planeTypeName, start_time AS startTime, end_time AS endTime, cost,"
            + "from_airport_id AS fromAirportId, to_airport_id AS toAirportId, transit_time AS transitTime, "
            + "(SELECT COUNT(*) FROM plane_seat_struct WHERE plane_seat_struct.plane_type_id = flight.plane_type_id "
            + "AND plane_seat_struct.id NOT IN (SELECT flight_record.plane_seat_struct_id FROM flight_record WHERE is_deleted = 0 AND create_time = #{time})) AS seatNum,"
            + "(SELECT airport.name FROM airport WHERE airport.id = from_airport_id) AS fromAirport, (SELECT airport.name FROM airport WHERE airport.id = to_airport_id) AS toAirport "
            + "FROM flight, plane_type WHERE plane_type_id = plane_type.id "
            + "AND (from_airport_id IN (SELECT airport.id FROM airport WHERE airport.name = #{fromAirport}) "
            + "OR to_airport_id IN (SELECT airport.id FROM airport WHERE airport.name = #{toAirport})) AND #{time} BETWEEN from_date AND to_date")
    List<FlightDetail> queryFlights(String fromAirport, String toAirport, String time);

    /**
     * 查询某用户坐过该公司航班的次数
     */
    @Select("SELECT COUNT(*) FROM flight_record, passenger WHERE passenger_id = passenger.id AND id_card = #{idCardNum} AND is_deleted = 0")
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
    @Select("SELECT flight.id AS flightId, flight.plane_type_id AS planeTypeId, plane_type.name AS planeTypeName, type, COUNT(*) AS remain,"
            + "(SELECT price FROM flight_seat_price WHERE seat_type = type AND flight_id = flight.id) AS cost "
            + "FROM flight, plane_type, plane_seat_struct WHERE flight.plane_type_id = plane_type.id AND plane_seat_struct.plane_type_id = plane_type.id "
            + "AND plane_seat_struct.id NOT IN (SELECT plane_seat_struct_id FROM flight_record WHERE is_deleted = 0 AND flight_id = flight.id AND create_time = #{time}) "
            + "AND flight.id = #{flightId} GROUP BY type")
    List<FlightDetailItem> queryFlightDetail(Integer flightId, String time);

    /**
     * 查询可用的座位信息
     */
    @Select("SELECT * FROM plane_seat_struct WHERE plane_type_id = #{planeTypeId} AND type = #{type} "
            + "AND id NOT IN (SELECT plane_seat_struct_id FROM flight_record WHERE is_deleted = 0 AND create_time = #{time} AND flight_id = #{flightId})")
    @Result(column = "plane_type_id", property = "planeTypeId")
    List<PlaneSeatStruct> queryAvailableSeats(Integer flightId, String type, Integer planeTypeId, String time);
}
