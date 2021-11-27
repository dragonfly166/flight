package com.flight.domain.dto;

import lombok.Data;
import org.springframework.stereotype.Repository;

/**
 * 存储自增id
 * @author sunlongfei
 */
@Data
@Repository("autoIncreasedId")
public class AutoIncreasedId {

    private Integer id;
}
