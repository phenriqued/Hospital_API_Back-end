package RESTful.Hospitalapi.Services.Appointment;


import RESTful.Hospitalapi.Repositories.MedicalAppointments.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository repository;

    public void scheduleAppointment(){

    }


}
