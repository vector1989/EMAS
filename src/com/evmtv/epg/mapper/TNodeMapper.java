package com.evmtv.epg.mapper;

import com.evmtv.epg.entity.TNode;
import com.evmtv.epg.entity.TNodeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TNodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int countByExample(TNodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int deleteByExample(TNodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int insert(TNode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int insertSelective(TNode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    List<TNode> selectByExample(TNodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    TNode selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int updateByExampleSelective(@Param("record") TNode record, @Param("example") TNodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int updateByExample(@Param("record") TNode record, @Param("example") TNodeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int updateByPrimaryKeySelective(TNode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbggenerated Mon Sep 09 16:36:28 CST 2013
     */
    int updateByPrimaryKey(TNode record);
}