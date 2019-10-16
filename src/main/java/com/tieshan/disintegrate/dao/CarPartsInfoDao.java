package com.tieshan.disintegrate.dao;

import com.tieshan.disintegrate.vo.PartsInfoVo;
import com.tieshan.disintegrate.vo.PartsListVo;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @description: 库管模块持久层
 * @author: Leavonson
 * @date: Created in 2019/10/8 10:48
 * @version: 1.0
 * @modified By:
 */
@Mapper
public interface CarPartsInfoDao {

    /** APP - 查询待入库拆车件*/
    @Select({"<script>" +
            "        SELECT\n" +
            "        \tIFNULL( i.id, '' ) AS carInfoId,\n" +
            "        \tIFNULL( p.parts_code, '' ) AS partsCode,\n" +
            "        \tIFNULL( p.parts_name, '' ) AS partsName,\n" +
            "        \tIFNULL( i.car_no, '' ) AS carNo,\n" +
            "        \tIFNULL( d.vin, '' ) AS vin,\n" +
            "        \tIFNULL( p.print_operator, '' ) AS operator,\n" +
            "        \tIFNULL( p.print_time, '' ) AS time\n" +
            "        FROM\n" +
            "        \tts_car_info AS i\n" +
            "        \tLEFT JOIN ts_car_parts AS p ON i.id = p.car_info_id\n" +
            "        \tLEFT JOIN ts_car_identity AS d ON i.id = d.car_info_id\n" +
            "        WHERE\n" +
            "        \ti.disintegrate_plant_id = #{disintegratePlantId}\n" +
            "and p.parts_status=1" +
            "        \t<if test=\"findMsg != null and findMsg != ''\">\n" +
            "                AND CONCAT(i.car_code,i.car_no,i.car_name) LIKE CONCAT('%','','%')\n" +
            "            </if>" +
            "</script>"})
    List<PartsInfoVo> selectPreParts(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                                     @Param(value = "findMsg") String findMsg);
    /** APP - 查询已库拆车件*/
    @Select({"<script>" +
            "        SELECT\n" +
            "        \tIFNULL( i.id, '' ) AS carInfoId,\n" +
            "        \tIFNULL( p.parts_code, '' ) AS partsCode,\n" +
            "        \tIFNULL( p.parts_name, '' ) AS partsName,\n" +
            "        \tIFNULL( i.car_no, '' ) AS carNo,\n" +
            "        \tIFNULL( d.vin, '' ) AS vin,\n" +
            "        \tIFNULL( p.create_operator, '' ) AS operator,\n" +
            "        \tIFNULL( p.create_time, '' ) AS time\n" +
            "        FROM\n" +
            "        \tts_car_info AS i\n" +
            "        \tLEFT JOIN ts_car_parts AS p ON i.id = p.car_info_id\n" +
            "        \tLEFT JOIN ts_car_identity AS d ON i.id = d.car_info_id\n" +
            "        WHERE\n" +
            "        \ti.disintegrate_plant_id = #{disintegratePlantId}\n" +
            "and p.parts_status=2" +
            "        \t<if test=\"findMsg != null and findMsg != ''\">\n" +
            "                AND CONCAT(i.car_code,i.car_no,i.car_name) LIKE CONCAT('%','','%')\n" +
            "            </if>" +
            "</script>"})
    List<PartsInfoVo> selectIsParts(@Param(value = "disintegratePlantId") Long disintegratePlantId,
                                             @Param(value = "findMsg") String findMsg);

    /**更改拆解状态*/
    @Update("update ts_car_parts set " +
            "parts_status=2," +
            "create_operator_id=#{create_operator_id}," +
            "create_operator=#{create_operator},create_time=now() " +
            "where car_info_id=#{carInfoId} and disintegrate_plant_id=#{companyId} and parts_code=#{partsCode}")
    int updateIsParts( @Param("create_operator_id") Long createOperatorId,
                       @Param("create_operator") String createOperator,
                       @Param("carInfoId") Long carInfoId,
                       @Param("companyId") Long companyId,
                       @Param("partsCode") String partsCode);





}
