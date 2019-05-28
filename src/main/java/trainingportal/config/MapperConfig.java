package trainingportal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import trainingportal.mapper.*;
import trainingportal.mapper.generic.BaseObjectMapper;
import trainingportal.model.*;

@Configuration
public class MapperConfig {
    @Bean
    public BaseObjectMapper<Attendance> attendanceBaseObjectMapper(){
        return new AttendanceMapper();
    }
    @Bean
    public BaseObjectMapper<AttendanceType> attendanceTypeBaseObjectMapper(){
        return new AttendanceTypeMapper();
    }
    @Bean
    public BaseObjectMapper<Course> courseBaseObjectMapper(){
        return new CourseMapper();
    }
    @Bean
    public BaseObjectMapper<Group> groupBaseObjectMapper(){
        return new GroupMapper();
    }
    @Bean
    public BaseObjectMapper<Homework> homeworkBaseObjectMapper(){
        return new HomeworkMapper();
    }
    @Bean
    public BaseObjectMapper<Lesson> lessonBaseObjectMapper(){
        return new LessonMapper();
    }
    @Bean
    public BaseObjectMapper<MainCardModel> mainCardModelBaseObjectMapper(){
        return new MainCardMapper();
    }
    @Bean
    public BaseObjectMapper<MainSliderModel> mainSliderModelBaseObjectMapper(){
        return new MainSliderMapper();
    }
    @Bean
    public BaseObjectMapper<Material> materialBaseObjectMapper(){
        return new MaterialMapper();
    }
    @Bean
    public BaseObjectMapper<Weekday> weekdayBaseObjectMapper(){
        return new WeekdayMapper();
    }
    @Bean
    public BaseObjectMapper<Role> roleBaseObjectMapper(){
        return new RoleMapper();
    }
    @Bean
    public BaseObjectMapper<Schedule> scheduleBaseObjectMapper(){
        return new ScheduleMapper();
    }
    @Bean
    public BaseObjectMapper<Task> taskBaseObjectMapper(){
        return new TaskMapper();
    }
    @Bean
    public BaseObjectMapper<User> userBaseObjectMapper(){
        return new UserMapper();
    }
    @Bean
    public BaseObjectMapper<UserGroup> userGroupBaseObjectMapper(){
        return new UserGroupMapper();
    }
    @Bean
    public BaseObjectMapper<UserChat> userChatBaseObjectMapper(){
        return new UserChatMapper();
    }
    @Bean
    public BaseObjectMapper<CourseStatus> courseStatusBaseObjectMapper(){
        return new CourseStatusMapper();
    }
}