package com.chen.trademark.mapper;

import com.chen.trademark.entity.FileRecord;
import com.chen.trademark.entity.FileRecordExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface FileRecordMapper {
    /**
     * countByExample
     */
    long countByExample(FileRecordExample example);

    /**
     * deleteByExample
     */
    int deleteByExample(FileRecordExample example);

    /**
     * deleteByPrimaryKey
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * insert
     */
    int insert(FileRecord record);

    /**
     * insertSelective
     */
    int insertSelective(FileRecord record);

    /**
     * selectByExample
     */
    List<FileRecord> selectByExample(FileRecordExample example);

    /**
     * selectByPrimaryKey
     */
    FileRecord selectByPrimaryKey(Integer id);

    /**
     * updateByExampleSelective
     */
    int updateByExampleSelective(@Param("record") FileRecord record, @Param("example") FileRecordExample example);

    /**
     * updateByExample
     */
    int updateByExample(@Param("record") FileRecord record, @Param("example") FileRecordExample example);

    /**
     * updateByPrimaryKeySelective
     */
    int updateByPrimaryKeySelective(FileRecord record);

    /**
     * updateByPrimaryKey
     */
    int updateByPrimaryKey(FileRecord record);
}