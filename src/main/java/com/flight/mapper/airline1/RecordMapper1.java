package com.flight.mapper.airline1;

import com.flight.domain.dto.AutoIncreasedId;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

/**
 * @author sunlongfei
 */
@Mapper
public interface RecordMapper1 {

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
}