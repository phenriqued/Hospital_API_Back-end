package RESTful.Hospitalapi.Controllers.Patient;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.DTOs.Patients.UpdatePatientDTO;
import com.jayway.jsonpath.JsonPath;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional @Rollback
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
@WithMockUser
class PatientControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    private JacksonTester<RegisterPatientDTO> registerPatientDTOJsonTest;
    @Autowired
    private JacksonTester<UpdatePatientDTO> updatePatientDTOJsonTest;

    @Test
    @DisplayName("should return the code \"201 created\" when information patient is valid")
    void registerPatientScenarioOne() throws Exception{
        var address = new AddressClientDTO("Rua test","21","","Test District","Test City","TC","00000000");
        var informationClient = new InformationClientDTO("695.417.430-05", "Test Da Silva", "test@gamil.com", "11090909000");

        mockMvc.perform(post("/Patient")
                .contentType(MediaType.APPLICATION_JSON)
                .content(registerPatientDTOJsonTest.write(
                        new RegisterPatientDTO(informationClient, address)
                ).getJson()))
                .andExpect(status().isCreated());
    }
    @Test
    @DisplayName("should return the code \"400 Bad Request\" when information patient is not valid")
    void registerPatientScenarioTwo() throws Exception{
        mockMvc.perform(post("/Patient")).andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should return the code \"204 No Content\" when information patient is valid for updating")
    void updatePatientScenarioOne() throws Exception{
        mockMvc.perform(put("/Patient/"+1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updatePatientDTOJsonTest.write(
                                new UpdatePatientDTO(null ,null)
                        ).getJson()))
                .andExpect(status().isNoContent());
    }
    @Test
    @DisplayName("should return the code \"401 Bad Request\" when don't exist patient id")
    void updatePatientScenarioTwo() throws Exception{
        mockMvc.perform(put("/Patient/"+999L))
                .andExpect(status().isBadRequest());
    }

    @Test
    @DisplayName("should return the code \"200 OK\" when there is a patient ID for deactivation")
    void deletePatientScenarioOne() throws Exception{
        mockMvc.perform(delete("/Patient/" + 1L))
                .andExpect(status().isOk());
    }
    @Test
    @DisplayName("should return the code \"401 Bad Request\" when don't exist patient id for deactivation")
    void deletePatientScenarioTwo() throws Exception{
        mockMvc.perform(delete("/Patient/" + 999L))
                .andExpect(status().isBadRequest());
    }
}