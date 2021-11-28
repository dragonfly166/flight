package com.flight.mapper.airline3;

import com.flight.domain.dto.AutoIncreasedId;
import org.apache.ibatis.annotations.*;

/**
 * @author sunlongfei
 */
@Mapper
public interface RecordMapper3 {

    /**
     * 插入乘客信息并返回自增id
     */
    @Insert("INSERT INTO passenger(name, id_card) VALUES (#{name}, #{idCardNum})")
    @Options(keyColumn = "id", keyProperty = "autoIncreasedId.id", useGeneratedKeys = true)
    void insertPassenger(@Param("name") String name, @Param("idCardNum") String idCardNum,
        @Param("autoIncreasedId") AutoIncreasedId autoIncreasedId);

    /**
     * 插入购票记录
     */
    @Insert("INSERT INTO flight_record(flight_id, create_time, passenger_id) VALUES (#{flightId}, #{time}, #{passengerId})")
    @Options(keyColumn = "id", keyProperty = "autoIncreasedId.id", useGeneratedKeys = true)
    void insertRecord(@Param("flightId") Integer flightId, @Param("time") String time,
        @Param("passengerId") Integer passengerId, @Param("autoIncreasedId") AutoIncreasedId autoIncreasedId);

    /**
     * 更新购票记录座位信息
     */
    @Update("UPDATE flight_record SET plane_seat_struct_id = #{seatId} "
            + "WHERE id = #{recordId}")
    void updateSeatId(Integer recordId, Integer planeTypeId, Integer seatId);

    /**
     * 删除购票记录(软删除)
     */
    @Update("UPDATE flight_record SET is_deleted = 1 WHERE id = #{recordId}")
    void deleteRecord(Integer recordId);
}