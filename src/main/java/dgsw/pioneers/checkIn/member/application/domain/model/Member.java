package dgsw.pioneers.checkIn.member.application.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

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

    public void encodePw(String encodedPw) {
        this.pw = encodedPw;
    }

    public void setInfoYear(int year) {
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
