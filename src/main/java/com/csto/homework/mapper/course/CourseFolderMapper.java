package com.csto.homework.mapper.course;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据库层对文件夹操作
 */
@Mapper
public interface CourseFolderMapper {

    //创建文件夹
    int createFolders(@Param("userInfoId") int userInfoId,
                      @Param("folderName") String folderName,
                      @Param("courseClass") List<String> courseClass,
                      @Param("folderCreateTime")String folderCreateTime);

    //查询老师为文件夹添加的班级
    List<String> findListCourseClass(@Param("courseInfoId") int courseInfoId,
                                     @Param("courseFolderName")String courseFolderName);

    //查询老师添加的文件夹名称（如：实验报告一）
    List<String> findListCourseFolderName(int courseInfoId);
}
