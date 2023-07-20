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

    private final String email;

    private final MemberRole memberRole;
    private StudentInfo studentInfo;

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
            StudentInfo studentInfo) {
        return new Member(memberId, name, pw, email, memberRole, studentInfo);
    }

    public static Member studentWithId(
            MemberId memberId,
            String name,
            String pw,
            String email,
            StudentInfo studentInfo) {
        return new Member(memberId, name, pw, email, MemberRole.STUDENT, studentInfo);
    }

    public static Member teacherWithId(
            MemberId memberId,
            String name,
            String pw,
            String email,
            StudentInfo studentInfo) {
        return new Member(memberId, name, pw, email, MemberRole.TEACHER, studentInfo);
    }

    public void encodePw(String encodedPw) {
        this.pw = encodedPw;
    }

    public void modifyInfo(int year) {
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