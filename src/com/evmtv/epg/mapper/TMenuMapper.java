package com.evmtv.epg.mapper;

import com.evmtv.epg.entity.TMenu;
import com.evmtv.epg.entity.TMenuExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int countByExample(TMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int deleteByExample(TMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int insert(TMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int insertSelective(TMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    List<TMenu> selectByExample(TMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    TMenu selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") TMenu record, @Param("example") TMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") TMenu record, @Param("example") TMenuExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(TMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_menu
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TMenu record);
}