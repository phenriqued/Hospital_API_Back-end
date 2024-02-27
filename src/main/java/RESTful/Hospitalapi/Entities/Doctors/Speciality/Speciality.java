package RESTful.Hospitalapi.Entities.Doctors.Speciality;

import java.util.Arrays;

public enum Speciality {

    GENERAL_PRACTITIONER(1),
    CARDIOLOGIST(2),
    NUTRITIONIST(3),
    PEDIATRIC(4),
    DERMATOLOGIST(5),
    PSYCHOLOGIST(6);

    private final int value;

    Speciality(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public static Speciality intValueOf(int valueInt){
        return Arrays.stream(Speciality.values()).filter(speciality -> speciality.value==valueInt)
                .findFirst().orElseThrow();
    }


}
