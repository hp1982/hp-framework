package com.huipeng1982.utils.mapper.converter;

import com.huipeng1982.utils.mapper.model.Student;
import com.huipeng1982.utils.mapper.model.StudentVO;
import com.huipeng1982.utils.mapper.model.Teacher;
import com.huipeng1982.utils.mapper.model.TeacherVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MapStructConverter {

    MapStructConverter INSTANCE = Mappers.getMapper(MapStructConverter.class);

    TeacherVO teacherToTeacherVO(Teacher teacher);

    StudentVO studentToStudentVO(Student student);

    Teacher teacherVoToTeacher(TeacherVO teacherVo);

    Student studentVoToStudent(StudentVO studentVo);
}
