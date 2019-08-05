package training_chi.day17;

import java.util.List;

/**
 * @author WXJ
 * @version 1.0
 * @date 2019/8/5
 */
public interface StudentOperatable {
    void insert(Student student);
    int deleteById(String sno);
    int updateById(Student student);
    List<Student> select(Student student);
}
