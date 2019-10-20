package com.csto.homework.service.course;

import java.util.List;
import java.util.Map;

public interface CourseFolderService {

    /**
     * 教师为课程创建文件夹
     * @param courseInfoId 课程id
     * @param folderName 文件夹名称
     * @param courseClass 班级列表
     * @return 插入行数
     */
    int createFolder(int courseInfoId, String folderName, List<String> courseClass);

    /**
     * 根据课程的id查询所有的文件夹信息（实验报告以及班级）
     * @param courseInfoId 课程id
     * @return 实验报告和班级文件夹
     */
    Map<String,List<String>> findListFolder(int courseInfoId);
}
