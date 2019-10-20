package com.csto.homework.controller.teacher;

import com.csto.homework.dto.Result;
import com.csto.homework.entity.course.CourseInfo;
import com.csto.homework.service.course.CourseInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 教师对增删课程的操作
 * @author fjw
 */
@RestController
@RequestMapping(value = "/CourseInfo")
public class CourseInfoController {

    @Autowired
    CourseInfoService courseInfoService;

    /**
     * 教师添加课程
     * @param courseInfo 课程信息
     * @return 提示结果
     */
    @PostMapping("/createCourse")
    public Result createCourse(CourseInfo courseInfo){

        int resultCode = courseInfoService.createCourse(courseInfo);
        if(resultCode == 1){
            return new Result<>(1,"创建课程成功");
        }
        else if(resultCode == -1){
            return new Result(3,"该课程名与您之前添加的课程名重复，请更换课程名或删除之前课程后重新添加");
        }
        return new Result<>(2,"添加课程失败");
    }

    /**
     * 根据教师id查询创建的所有课程
     * @param userInfoId 教师id
     * @return 课程id，课程名称列表
     */
    @GetMapping("/findListMyCourse")
    public Result findListMyCourse(int userInfoId) {
        List<Map<String, String>> myCourseList = courseInfoService.findListMyCourse(userInfoId);
        if (myCourseList.isEmpty()) {
            return new Result<>(2, "课程列表为空，请创建课程");
        }
        return new Result<List>(1, "查询所有课程信息成功", myCourseList);
    }

    /**
     * 修改课程信息
     * @param courseInfoId 课程id
     * @param courseName 修改的课程名称
     * @return 返回修改行数
     */
    @PutMapping("/updateCourse")
    public Result updateCourse(@RequestParam("courseInfoId")int courseInfoId,
                               @RequestParam("courseName")String courseName){
        int resultCode = courseInfoService.updateCourseName(courseInfoId, courseName);
        if(resultCode == 1){
            return new Result(1,"修改课程名称成功");
        }
        return new Result(2,"修改课程名称失败");
    }

    /**
     * 查询对应教师所开设的课程的教学资料
     *
     * @param request
     * @return
     */
    @GetMapping(value = "/read")
    public Result readResources(HttpServletRequest request) {

        HttpSession session = request.getSession();
        String account = "9";//(String)session.getAttribute("account");

        List<Map> list = courseInfoService.listCourseByAccount(Integer.parseInt(account));

        return new Result(200,"查询成功",list);
    }


}