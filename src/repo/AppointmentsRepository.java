package repo;

import domain.Appointment;

import java.util.ArrayList;

// extend the memory repository and make a special repository for handling appointments
public class AppointmentsRepository extends MemoryRepository<Integer, Appointment>{
      public AppointmentsRepository(){
       super();
   }

    // Get the ids of the appointments of a certain doctor
    public ArrayList<Integer> getIdsOfAppointmentsOfDoctor(String doctorName) {
        // save the requested allAppointments in an arraylist
        ArrayList<Integer> idsAppointmentsOfDoctor = new ArrayList<>();

        Iterable<Appointment> allAppointments = this.getAll(); // we will work with this iterable list and traverse it
        for(Appointment appointment: allAppointments){
            if(appointment.getDoctorName().equals(doctorName)){
                idsAppointmentsOfDoctor.add(appointment.getId());
            }
        }
        return idsAppointmentsOfDoctor;
    }

    //Get the name of the doctor with the most appointments
    public Integer getNrOfAppointmentsOfDoctor(String doctorName)  {
        int nrAppointments = 0;

        Iterable<Appointment> allAppointments = this.getAll();
        for (Appointment appointment: allAppointments)
            if (appointment.getDoctorName().equals(doctorName)) {
                nrAppointments++;
            }
        return nrAppointments;

    }
    public String getNameOfDoctorWithMostAppointments()  {
        int maximNrAppointments=-1;
        Iterable<Appointment> allAppointments = this.getAll();
        String nameOfDoctorWithMostAppointments= new String();
        for(Appointment appointment: allAppointments){
            if(getNrOfAppointmentsOfDoctor(appointment.getDoctorName())>maximNrAppointments) {
                maximNrAppointments = getNrOfAppointmentsOfDoctor(appointment.getDoctorName());
                nameOfDoctorWithMostAppointments=appointment.getDoctorName();
            }
        }
        return nameOfDoctorWithMostAppointments;
    }

}
