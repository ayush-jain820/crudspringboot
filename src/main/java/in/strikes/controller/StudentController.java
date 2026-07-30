package in.strikes.controller;

import in.strikes.entity.Student;
import in.strikes.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/students")
public class StudentController {

    private StudentService studentservice;

    StudentController(StudentService studentservice){
        this.studentservice = studentservice;
    }

    @PostMapping("/create")
    public ResponseEntity<Student>  createStudent(@RequestBody Student student){

//        System.out.println(student.getName());
//        System.out.println(student.getEmail());

      Student createdstudent =  studentservice.createstudent(student);

        System.out.println("inside student controller");

        return ResponseEntity.ok(createdstudent);




    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Student> getstudent( @PathVariable  Long id){
        Student studentresp = studentservice.getstudent(id);

        if(studentresp==null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body( null);
        }

;

    return  ResponseEntity.status(HttpStatus.OK).body(studentresp);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Student>> getAllStudent() {
        List<Student> studentList = studentservice.getAllStudent();

        if(studentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentList);
    }
    @PutMapping("/update")
    public ResponseEntity<Student> updateStudent(@RequestParam Long id,
                                                 @RequestBody Student studentReq) {
        Student studentResp = studentservice.updateStudent(id, studentReq);

        if(studentResp == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentResp);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteStudent(@RequestParam Long id) {
        Boolean isDeleted = studentservice.deleteStudent(id);

        if(!isDeleted) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok("Record deleted");
    }


}
