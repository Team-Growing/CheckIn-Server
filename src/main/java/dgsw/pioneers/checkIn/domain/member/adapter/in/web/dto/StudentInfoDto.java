package dgsw.pioneers.checkIn.domain.member.adapter.in.web.dto;

import dgsw.pioneers.checkIn.domain.member.application.domain.model.StudentInfo;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Positive;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentInfoDto {

    @Positive
    int grade;
    @Positive
    int room;
    @Positive
    int number;

    public static StudentInfoDto convertToDTO(StudentInfo studentInfo) {
        return new StudentInfoDto(studentInfo.getGrade(), studentInfo.getRoom(), studentInfo.getNumber());
    }
}
