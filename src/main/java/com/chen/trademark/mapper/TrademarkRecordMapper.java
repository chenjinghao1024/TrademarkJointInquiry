package com.chen.trademark.mapper;

import com.chen.trademark.entity.TrademarkRecord;
import com.chen.trademark.entity.TrademarkRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TrademarkRecordMapper {
    /**
     * countByExample
     */
    long countByExample(TrademarkRecordExample example);

    /**
     * deleteByExample
     */
    int deleteByExample(TrademarkRecordExample example);

    /**
     * deleteByPrimaryKey
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     */
    int insert(TrademarkRecord record);

    /**
     * insertSelective
     */
    int insertSelective(TrademarkRecord record);

    /**
     * selectByExample
     */
    List<TrademarkRecord> selectByExample(TrademarkRecordExample example);

    /**
     * selectByPrimaryKey
     */
    TrademarkRecord selectByPrimaryKey(Integer id);

    /**
     * updateByExampleSelective
     */
    int updateByExampleSelective(@Param("record") TrademarkRecord record, @Param("example") TrademarkRecordExample example);

    /**
     * updateByExample
     */
    int updateByExample(@Param("record") TrademarkRecord record, @Param("example") TrademarkRecordExample example);

    /**
     * updateByPrimaryKeySelective
     */
    int updateByPrimaryKeySelective(TrademarkRecord record);

    /**
     * updateByPrimaryKey
     */
    int updateByPrimaryKey(TrademarkRecord record);
}