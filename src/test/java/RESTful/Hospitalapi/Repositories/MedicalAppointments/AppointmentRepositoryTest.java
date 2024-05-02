package RESTful.Hospitalapi.Repositories.MedicalAppointments;

import RESTful.Hospitalapi.DTOs.ClientsData.AddressClientDTO;
import RESTful.Hospitalapi.DTOs.ClientsData.InformationClientDTO;
import RESTful.Hospitalapi.DTOs.Doctors.RegisterDoctorDTO;
import RESTful.Hospitalapi.DTOs.Patients.RegisterPatientDTO;
import RESTful.Hospitalapi.Entities.Doctors.DoctorEntity;
import RESTful.Hospitalapi.Entities.MedicalAppointment.AppointmentEntity;
import RESTful.Hospitalapi.Entities.Patients.PatientEntity;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class AppointmentRepositoryTest{
    @Autowired
    private AppointmentRepository repository;
    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("The method must return a list of the Doctor's appointments on the day")
    void findByDoctorIdAndScheduleOfDayScenarioOne() {
        setupToFillDatabase();
        var dateTime = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        var doctorAppointmentList = repository.findByDoctorIdAndDate(1L, dateTime.getYear(),
                    dateTime.getMonthValue(), dateTime.getDayOfMonth());
        assertFalse(doctorAppointmentList.isEmpty());
    }


    @Test
    @DisplayName("Should return an empty list of medical appointments on the day that there are no appointments scheduled")
    void findByDoctorIdAndScheduleOfDayScenarioTwo() {
        setupToFillDatabase();
        var dateTime = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        var doctorAppointmentList = repository.findByDoctorIdAndDate(1L, dateTime.getYear(),
                dateTime.getMonthValue(), dateTime.getDayOfMonth());
        assertTrue(doctorAppointmentList.isEmpty());
    }


    private void setupToFillDatabase(){
        var allAddress = new AddressClientDTO("Rua test", "21", null,"test", "test", "te", "00000000" );
        var firstInformationPatient = new InformationClientDTO("086.645.662-75", "Jonas Test", "jonas@gmail.com","11998877665");
        var firstPatient = em.persist(new PatientEntity(new RegisterPatientDTO(firstInformationPatient,allAddress)));

        var firstInformationDoctor = new InformationClientDTO("501.246.870-83", "Doctor Test", "doctor@gmail.com","11998877665");
        var firstDoctor = em.persist(new DoctorEntity(new RegisterDoctorDTO(firstInformationDoctor, allAddress, "NUTRITIONIST")));

        var dateTime = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        em.persist(new AppointmentEntity(null, firstPatient, firstDoctor, dateTime,dateTime.plusHours(1)));

    }

}