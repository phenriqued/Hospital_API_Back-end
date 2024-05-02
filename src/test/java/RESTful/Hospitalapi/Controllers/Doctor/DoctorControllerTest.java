package RESTful.Hospitalapi.Controllers.Doctor;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.DTOs.Doctors.UpdateDoctorDTO;
import RESTful.Hospitalapi.DTOs.Patients.UpdatePatientDTO;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@Transactional @Rollback
@WithMockUser
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class DoctorControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JacksonTester<RegisterDoctorDTO> registerDoctorDTOJsonTest;
    @Autowired
    private JacksonTester<UpdateDoctorDTO> updateDoctorDTOJsonTest;



    @Test
    @DisplayName("should return the code \"201 created\" when information Doctor is valid")
    void createDoctor() throws Exception{
        var address = new AddressClientDTO("Rua test","21","","Test District","Test City","TC","00000000");
        var informationClient = new InformationClientDTO("695.417.430-05", "Test Da Silva", "test@gamil.com", "11090909000");
        mockMvc.perform(post("/Doctor")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerDoctorDTOJsonTest.write(
                        new RegisterDoctorDTO(informationClient, address, "NUTRITIONIST")
                ).getJson()))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("should return the code \"400 Bad Request\" when information doctor is not valid")
    void createDoctorScenarioOne() throws Exception{
        mockMvc.perform(post("/Doctor")).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should return the code \"204 No Content\" when information doctor is valid for updating")
    void updateDoctorScenarioOne() throws Exception{
        mockMvc.perform(put("/Patient/"+1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateDoctorDTOJsonTest.write(
                                new UpdateDoctorDTO(null ,null, null)
                        ).getJson()))
                .andExpect(status().isNoContent());
    }
    @Test
    @DisplayName("should return the code \"401 Bad Request\" when don't exist patient id")
    void updateDoctorScenarioTwo() throws Exception{
        mockMvc.perform(put("/Patient/"+999L)).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should return the code \"200 OK\" when there is a doctor ID for deactivation")
    void deleteDoctorScenarioOne() throws Exception{
        mockMvc.perform(delete("/Doctor/" + 1L))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("should return the code \"401 Bad Request\" when don't exist doctor id for deactivation")
    void deleteDoctorScenarioTwo() throws Exception{
        mockMvc.perform(delete("/Doctor/" + 9999L))
                .andExpect(status().isBadRequest());
    }
}