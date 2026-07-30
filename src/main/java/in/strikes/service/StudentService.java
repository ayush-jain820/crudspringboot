package in.strikes.service;

import in.strikes.entity.Student;
import in.strikes.repository.StudentRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private StudentRepository studentrepository;

    StudentService( StudentRepository studentrepository){
        this.studentrepository = studentrepository;
    }

public Student createstudent( Student studentreq){


     Student studentres =   studentrepository.save(studentreq);

     System.out.println("inside studentservice");

        return studentres;






    }


    public Student getstudent( Long id){
        Optional<Student> studentresp1 = studentrepository.findById(id);

        if(studentresp1.isPresent()){
            return studentresp1.get();
        }
        return null;

    }



    public List<Student> getAllStudent() {
        List<Student> studentList = studentrepository.findAll();
        return studentList;
    }

    public Student updateStudent(Long id, Student studentReq) {
        Optional<Student> existingStudent = studentrepository.findById(id);

        if(existingStudent.isEmpty()) {
            return null;
        }

        Student studentToSave = existingStudent.get();

        studentToSave.setName(studentReq.getName());
        studentToSave.setRollNo(studentReq.getRollNo());
        studentToSave.setSubject(studentReq.getSubject());
        studentToSave.setEmail(studentReq.getEmail());
        studentToSave.setAge(studentReq.getAge());

        return studentrepository.save(studentToSave);
    }

    public Boolean deleteStudent(Long id) {
        Boolean isStudent = studentrepository.existsById(id);

        if(!isStudent) return false;

        studentrepository.deleteById(id);

        return true;
    }

}
