package com.huipeng1982.utils.mapper;

import com.huipeng1982.utils.collection.ListUtil;
import com.huipeng1982.utils.mapper.converter.MapStructConverter;
import com.huipeng1982.utils.mapper.model.Student;
import com.huipeng1982.utils.mapper.model.StudentVO;
import com.huipeng1982.utils.mapper.model.Teacher;
import ma.glasnost.orika.metadata.Type;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanMapperTest {

    @Test
    public void copySingleObjectByMapStructConverter() {
        Student student = new Student("zhang3", 20, new Teacher("li4"), ListUtil.newArrayList("chinese", "english"));
        StudentVO studentVo2 = MapStructConverter.INSTANCE.studentToStudentVO(student);

        assertThat(studentVo2.name).isEqualTo("zhang3");
        assertThat(studentVo2.getAge()).isEqualTo(20);
        assertThat(studentVo2.getTeacher().getName()).isEqualTo("li4");
        assertThat(studentVo2.getCourse()).containsExactly("chinese", "english");
    }

    @Test
    public void copySingleObject() {
        Student student = new Student("zhang3", 20, new Teacher("li4"), ListUtil.newArrayList("chinese", "english"));

        StudentVO studentVo = BeanMapper.map(student, StudentVO.class);

        assertThat(studentVo.name).isEqualTo("zhang3");
        assertThat(studentVo.getAge()).isEqualTo(20);
        assertThat(studentVo.getTeacher().getName()).isEqualTo("li4");
        assertThat(studentVo.getCourse()).containsExactly("chinese", "english");

        //////////
        Type<Student> studentType = BeanMapper.getType(Student.class);
        Type<StudentVO> studentVoType = BeanMapper.getType(StudentVO.class);
        studentVo = BeanMapper.map(student, studentType, studentVoType);

        assertThat(studentVo.name).isEqualTo("zhang3");
        assertThat(studentVo.getAge()).isEqualTo(20);
        assertThat(studentVo.getTeacher().getName()).isEqualTo("li4");
        assertThat(studentVo.getCourse()).containsExactly("chinese", "english");
    }

    @Test
    public void copyListObject() {
        Student student1 = new Student("zhang3", 20, new Teacher("li4"), ListUtil.newArrayList("chinese", "english"));
        Student student2 = new Student("zhang4", 30, new Teacher("li5"), ListUtil.newArrayList("chinese2", "english4"));
        Student student3 = new Student("zhang5", 40, new Teacher("li6"), ListUtil.newArrayList("chinese3", "english5"));
        List<Student> studentList = ListUtil.newArrayList(student1, student2, student3);

        List<StudentVO> studentVoList = BeanMapper.mapList(studentList, Student.class, StudentVO.class);
        assertThat(studentVoList).hasSize(3);
        StudentVO studentVo = studentVoList.get(0);

        assertThat(studentVo.name).isEqualTo("zhang3");
        assertThat(studentVo.getAge()).isEqualTo(20);
        assertThat(studentVo.getTeacher().getName()).isEqualTo("li4");
        assertThat(studentVo.getCourse()).containsExactly("chinese", "english");

        /////////
        Type<Student> studentType = BeanMapper.getType(Student.class);
        Type<StudentVO> studentVoType = BeanMapper.getType(StudentVO.class);
        studentVoList = BeanMapper.mapList(studentList, studentType, studentVoType);

        studentVoList = BeanMapper.mapList(studentList, Student.class, StudentVO.class);
        assertThat(studentVoList).hasSize(3);
        studentVo = studentVoList.get(0);

        assertThat(studentVo.name).isEqualTo("zhang3");
        assertThat(studentVo.getAge()).isEqualTo(20);
        assertThat(studentVo.getTeacher().getName()).isEqualTo("li4");
        assertThat(studentVo.getCourse()).containsExactly("chinese", "english");
    }

    @Test
    public void copyArrayObject() {
        Student student1 = new Student("zhang3", 20, new Teacher("li4"), ListUtil.newArrayList("chinese", "english"));
        Student student2 = new Student("zhang4", 30, new Teacher("li5"), ListUtil.newArrayList("chinese2", "english4"));
        Student student3 = new Student("zhang5", 40, new Teacher("li6"), ListUtil.newArrayList("chinese3", "english5"));
        Student[] studentList = new Student[]{student1, student2, student3};
        StudentVO[] studentVoList = new StudentVO[studentList.length];
        BeanMapper.mapArray(studentVoList, studentList, StudentVO.class);
        assertThat(studentVoList).hasSize(3);
        StudentVO studentVo = studentVoList[0];

        assertThat(studentVo.name).isEqualTo("zhang3");
        assertThat(studentVo.getAge()).isEqualTo(20);
        assertThat(studentVo.getTeacher().getName()).isEqualTo("li4");
        assertThat(studentVo.getCourse()).containsExactly("chinese", "english");

        /////////
        Type<Student> studentType = BeanMapper.getType(Student.class);
        Type<StudentVO> studentVoType = BeanMapper.getType(StudentVO.class);

        StudentVO[] studentVoList2 = new StudentVO[studentList.length];
        BeanMapper.mapArray(studentVoList2, studentList, studentType, studentVoType);
        assertThat(studentVoList).hasSize(3);
        studentVo = studentVoList2[0];

        assertThat(studentVo.name).isEqualTo("zhang3");
        assertThat(studentVo.getAge()).isEqualTo(20);
        assertThat(studentVo.getTeacher().getName()).isEqualTo("li4");
        assertThat(studentVo.getCourse()).containsExactly("chinese", "english");
    }

}
