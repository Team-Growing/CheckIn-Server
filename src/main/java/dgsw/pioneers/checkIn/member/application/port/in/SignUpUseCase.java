package dgsw.pioneers.checkIn.member.application.port.in;

public interface SignUpUseCase {

    void signUpTeacher(SignUpTeacherCommand signUpCommand);
    void signUpStudent(SignUpStudentCommand signUpCommand);
}
