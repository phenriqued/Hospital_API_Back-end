package RESTful.Hospitalapi.Controllers.Appointment;

import RESTful.Hospitalapi.DTOs.MedicalAppointment.CancelAppointmentDTO;
import RESTful.Hospitalapi.DTOs.MedicalAppointment.CancelAppointmentDTO.Reason;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional @Rollback
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser
class AppointmentCancelControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    JacksonTester<CancelAppointmentDTO> cancelAppointmentJsonTest;

    @Test
    @DisplayName("should return the code \"400 Bad Request\" when there is no information or it is invalid for cancel appointment")
    void appointmentSchedulingCancelScenarioOne() throws Exception {
        mockMvc.perform(post("/ScheduleAppointment/cancel")).andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("should return the code \"200 Ok\" when information is valid for cancellation appointment")
    void appointmentSchedulingCancelScenarioTwo() throws Exception{
        mockMvc.perform(post("/ScheduleAppointment/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cancelAppointmentJsonTest.write(
                        new CancelAppointmentDTO(51L, Reason.PATIENT_GAVE_UP, null)).getJson()))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("should return the code \"400 Bad Request\" when the \"Reason\" is \"OTHERS\" and the explanation string is null.")
    void appointmentSchedulingCancelScenarioThree() throws Exception{
        mockMvc.perform(post("/ScheduleAppointment/cancel")
                .contentType(MediaType.APPLICATION_JSON)
                .content(cancelAppointmentJsonTest.write(
                        new CancelAppointmentDTO(1L, Reason.OTHERS, null )).getJson()))
                .andExpect(status().isBadRequest());
    }
    @Test
    @DisplayName("must return the code \"400 Bad Request\" when trying to cancel up to 24 hours before the medical appointment")
    void appointmentSchedulingCancelScenarioFour() throws Exception{
        mockMvc.perform(post("/ScheduleAppointment/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(cancelAppointmentJsonTest.write(
                                new CancelAppointmentDTO(61L, Reason.DOCTOR_CANCELED, null)).getJson()))
                .andExpect(status().isBadRequest());
    }

}