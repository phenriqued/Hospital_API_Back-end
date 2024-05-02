package RESTful.Hospitalapi.Controllers.Appointment;

import RESTful.Hospitalapi.DTOs.MedicalAppointment.AppointmentDTO;
import RESTful.Hospitalapi.Entities.Doctors.Speciality.Speciality;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalAdjusters;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional @Rollback
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser
class AppointmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JacksonTester<AppointmentDTO> appointmentJsonTest;

    @Test
    @DisplayName("should return the code \"200 Ok\" when information is different from null or is valid")
    void appointmentSchedulingScenarioOne() throws Exception{
        var date = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).withHour(13);
        mockMvc.perform(post("/ScheduleAppointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointmentJsonTest.write
                        (new AppointmentDTO(1L, 1L, Speciality.PEDIATRIC, date)).getJson()
                )).andExpect(status().isOk());

    }

    @Test
    @DisplayName("should return the code \"400 Bad Request\" when there is no information or it is invalid")
    void appointmentSchedulingScenarioTwo() throws Exception{
        mockMvc.perform(post("/ScheduleAppointment")).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should return the code \"400 Bad Request\" when time is outside clinic's opening hours")
    void appointmentSchedulingScenarioThree() throws Exception{
        var outOfHours = LocalDateTime.now().withHour(22);
        mockMvc.perform(post("/ScheduleAppointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointmentJsonTest.write
                        (new AppointmentDTO(1L, 1L, Speciality.PEDIATRIC, outOfHours)).getJson()
                )).andExpect(status().isBadRequest());

    }
    @Test
    @DisplayName("should return the code \"400 Bad Request\" when date is outside clinic's opening hours")
    void appointmentSchedulingScenarioFour() throws Exception{
        var outOfDay = LocalDateTime.now().with(DayOfWeek.SUNDAY);
        mockMvc.perform(post("/ScheduleAppointment")
                .contentType(MediaType.APPLICATION_JSON)
                .content(appointmentJsonTest.write
                        (new AppointmentDTO(1L, 1L, Speciality.PEDIATRIC, outOfDay)).getJson()
                )).andExpect(status().isBadRequest());

    }





}