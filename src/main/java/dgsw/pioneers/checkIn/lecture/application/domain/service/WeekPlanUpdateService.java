package dgsw.pioneers.checkIn.lecture.application.domain.service;

import dgsw.pioneers.checkIn.global.annotation.UseCase;
import dgsw.pioneers.checkIn.lecture.application.port.in.WeekPlanUpdateUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@UseCase
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WeekPlanUpdateService implements WeekPlanUpdateUseCase {

    @Override
    public void updateWeekPlan() {

    }
}
