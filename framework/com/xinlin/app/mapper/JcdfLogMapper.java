package com.xinlin.app.mapper;

import com.xinlin.app.entity.pojo.JcdfLog;
import com.xinlin.app.entity.pojo.Menu;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface JcdfLogMapper {
	
	/**
	 * 分页查询菜单数据
	 * 
	 * @param menu	数据筛选参数，包括分页参数
	 * 
	 * @return	分页数据的封装对象
	 */
	public List<JcdfLog> pageQuery(JcdfLog jcdflog);

    @Delete({
        "delete from jcdf_log",
        "where log_id = #{logId,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String logId);

    @Insert({
        "insert into jcdf_log (log_id, log_ip, ",
        "log_mac, user_id, ",
        "user_name, operator_time, ",
        "operato_content, module_name, ",
        "log_para1, log_para2, ",
        "log_para3, log_para4)",
        "values (#{logId,jdbcType=VARCHAR}, #{logIp,jdbcType=VARCHAR}, ",
        "#{logMac,jdbcType=VARCHAR}, #{userId,jdbcType=VARCHAR}, ",
        "#{userName,jdbcType=VARCHAR}, #{operatorTime,jdbcType=TIMESTAMP}, ",
        "#{operatoContent,jdbcType=VARCHAR}, #{moduleName,jdbcType=VARCHAR}, ",
        "#{logPara1,jdbcType=INTEGER}, #{logPara2,jdbcType=INTEGER}, ",
        "#{logPara3,jdbcType=VARCHAR}, #{logPara4,jdbcType=VARCHAR})"
    })
    int insert(JcdfLog record);

    int insertSelective(JcdfLog record);


    @Select({
        "select",
        "log_id, log_ip, log_mac, user_id, user_name, operator_time, operato_content, ",
        "module_name, log_para1, log_para2, log_para3, log_para4",
        "from jcdf_log",
        "where log_id = #{logId,jdbcType=VARCHAR}"
    })
    @ResultMap("BaseResultMap")
    JcdfLog selectByPrimaryKey(String logId);

    
    @Select({
    	 "select",
         "log_id, log_ip, log_mac, user_id, user_name, operator_time, operato_content, ",
         "module_name, log_para1, log_para2, log_para3, log_para4",
         "from jcdf_log",
         "where 1=1"
    })
    @ResultMap("BaseResultMap")
    List<JcdfLog> listAll();
    
    

    int updateByPrimaryKeySelective(JcdfLog record);

    @Update({
        "update jcdf_log",
        "set log_ip = #{logIp,jdbcType=VARCHAR},",
          "log_mac = #{logMac,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=VARCHAR},",
          "user_name = #{userName,jdbcType=VARCHAR},",
          "operator_time = #{operatorTime,jdbcType=TIMESTAMP},",
          "operato_content = #{operatoContent,jdbcType=VARCHAR},",
          "module_name = #{moduleName,jdbcType=VARCHAR},",
          "log_para1 = #{logPara1,jdbcType=INTEGER},",
          "log_para2 = #{logPara2,jdbcType=INTEGER},",
          "log_para3 = #{logPara3,jdbcType=VARCHAR},",
          "log_para4 = #{logPara4,jdbcType=VARCHAR}",
        "where log_id = #{logId,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(JcdfLog record);
}