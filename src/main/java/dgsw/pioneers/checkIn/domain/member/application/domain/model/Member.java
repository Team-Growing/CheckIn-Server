package dgsw.pioneers.checkIn.domain.member.application.domain.model;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.enums.MemberRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import java.util.Objects;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private final MemberId memberId;

    private final String name;

    private String pw;

    private String email;

    private final MemberRole memberRole;
    private StudentInfo studentInfo;

    private String subject; //선생님이면 자기 담당 역할 ex) '나르샤', 학생이면 '학생'이라 저장

    @Value
    public static class MemberId {
        String value;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            MemberId memberId = (MemberId) o;
            return Objects.equals(value, memberId.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }

    public static Member withId(
            MemberId memberId,
            String name,
            String pw,
            String email,
            MemberRole memberRole,
            StudentInfo studentInfo,
            String subject) {
        return new Member(memberId, name, pw, email, memberRole, studentInfo, subject);
    }

    public static Member studentWithId(
            MemberId memberId,
            String name,
            String pw,
            String email,
            StudentInfo studentInfo) {
        return new Member(memberId, name, pw, email, MemberRole.STUDENT, studentInfo, "학생");
    }

    public static Member teacherWithId(
            MemberId memberId,
            String name,
            String pw,
            String email,
            StudentInfo studentInfo,
            String subject) {
        return new Member(memberId, name, pw, email, MemberRole.TEACHER, studentInfo, subject);
    }

    public void encodePw(String encodedPw) {
        this.pw = encodedPw;
    }

    public void updateEmail(String email) {
        this.email = email;
    }

    public void updateSubject(String subject) {
        this.subject = subject;
    }

    public void updateStudentInfo(int year, int grade, int room, int number) {
        this.studentInfo = StudentInfo.builder()
                .year(year)
                .grade(grade)
                .room(room)
                .number(number)
                .build();
    }

    public void modifyInfoYear(int year) {
        this.studentInfo = StudentInfo.builder()
                .year(year)
                .grade(this.studentInfo.getGrade())
                .room(this.studentInfo.getRoom())
                .number(this.studentInfo.getNumber())
                .build();
    }

    public boolean checkStudentRole() {
        return this.memberRole.equals(MemberRole.STUDENT);
    }

    public boolean checkTeacherRole() {
        return this.memberRole.equals(MemberRole.TEACHER);
    }
}
