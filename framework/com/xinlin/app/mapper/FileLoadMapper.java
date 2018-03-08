package com.xinlin.app.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xinlin.app.entity.pojo.FileLoad;

public interface FileLoadMapper {
     

    @Delete({
        "delete from jcdf_fileuploadbean",
        "where fileId = #{fileid,jdbcType=VARCHAR}"
    })
    int deleteByPrimaryKey(String fileid);

    @Insert({
        "insert into jcdf_fileuploadbean (fileId, fileName, ",
        "fileMark, blobFile)",
        "values (#{fileid,jdbcType=VARCHAR}, #{filename,jdbcType=VARCHAR}, ",
        "#{filemark,jdbcType=VARCHAR}, #{blobfile,jdbcType=LONGVARBINARY})"
    })
    int insert(FileLoad record);

    int insertSelective(FileLoad record);

   
    @Select({
        "select",
        "fileId, fileName, fileMark, blobFile",
        "from jcdf_fileuploadbean",
        "where fileId = #{fileid,jdbcType=VARCHAR}"
    })
    @ResultMap("ResultMapWithBLOBs")
    FileLoad selectByPrimaryKey(String fileid);
    
    
    @Select({
        "select",
        "fileId, fileName, fileMark, blobFile",
        "from jcdf_fileuploadbean",
        "where 1=1"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<FileLoad> listAll();

    int updateByPrimaryKeySelective(FileLoad record);

    @Update({
        "update jcdf_fileuploadbean",
        "set fileName = #{filename,jdbcType=VARCHAR},",
          "fileMark = #{filemark,jdbcType=VARCHAR},",
          "blobFile = #{blobfile,jdbcType=LONGVARBINARY}",
        "where fileId = #{fileid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKeyWithBLOBs(FileLoad record);

    @Update({
        "update jcdf_fileuploadbean",
        "set fileName = #{filename,jdbcType=VARCHAR},",
          "fileMark = #{filemark,jdbcType=VARCHAR}",
        "where fileId = #{fileid,jdbcType=VARCHAR}"
    })
    int updateByPrimaryKey(FileLoad record);
}