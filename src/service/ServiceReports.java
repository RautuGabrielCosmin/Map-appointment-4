package service;

import domain.Appointment;
import domain.Dentist;
import domain.DentistAssignment;
import repo.DentistAssignmentsRepository;
import repo.IRepository;

import java.util.ArrayList;
import java.util.List;

public class ServiceReports {

    private IRepository<Integer, Dentist> dentistsRepository;
    private IRepository<Integer, Appointment> appointmentsRepository;
    private IRepository<Integer, DentistAssignment> dentistAssignmentsRepository;

    public ServiceReports(IRepository<Integer, Dentist> dentistsRepository,
                          IRepository<Integer, Appointment> appointmentsRepository,
                          IRepository<Integer, DentistAssignment> dentistAssignmentsRepository) {

        this.dentistsRepository = dentistsRepository;
        this.appointmentsRepository = appointmentsRepository;
        this.dentistAssignmentsRepository = dentistAssignmentsRepository;
    }

    public ServiceReports(IRepository<Integer, Dentist> dentistsRepository,
                          IRepository<Integer, Appointment> appointmentsRepository,
                          DentistAssignmentsRepository dentistAssignmentsRepository) {
    }

    public Iterable<Dentist> getDentists()  {
        return dentistsRepository.getAll();
    }
    public Iterable<Appointment> getAppointments()  {
        return appointmentsRepository.getAll();
    }
    public Iterable<DentistAssignment> getAssignments()  {
        return dentistAssignmentsRepository.getAll();
    }

    public List<Appointment> getAllAppointmentsByDentistId(Integer dentistId) {
        List<DentistAssignment> dentistAssignments = new ArrayList<>();
        for(DentistAssignment dentistAssignment: dentistAssignmentsRepository.getAll())
            dentistAssignments.add(dentistAssignment);

        List<Appointment> appointmentsHavingDentistId = dentistAssignments.stream()
                .filter(dentistAssignment -> dentistAssignment.getDentist().getId().equals(dentistId))
                .map(dentistAssignment -> dentistAssignment.getAppointment())
                .toList();
        return appointmentsHavingDentistId;
    }

    public List<Dentist>
    getAllDentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending(String patientName) {

        List<DentistAssignment> dentistAssignments = new ArrayList<>();
        for(DentistAssignment dentistAssignment: dentistAssignmentsRepository.getAll())
            dentistAssignments.add(dentistAssignment);

        List<Dentist> dentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending
                = dentistAssignments.stream()
                .filter(dentistAssignment -> dentistAssignment.getAppointment().getPatientName().equals(patientName))
                .map(dentistAssignment -> dentistAssignment.getDentist())
                .sorted((dentist1, dentist2)-> dentist2.getName().compareToIgnoreCase(dentist1.getName()))
                .sorted((dentist1, dentist2)-> Integer.compare(dentist1.getAge(),dentist2.getAge()))
                .toList();
        return dentistsByAppointmentPatientNameSortedByAgeAscendingAndNameDescending;
    }
    public List<Dentist> getDentistsHavingAppointmentAtHourSortedByName(String hour) {
        List<DentistAssignment> dentistAssignments = new ArrayList<>();
        for(DentistAssignment dentistAssignment: dentistAssignmentsRepository.getAll())
            dentistAssignments.add(dentistAssignment);

        List<Dentist> dentistsHavingAppointmentAtHourSortedByName
                = dentistAssignments.stream()
                .filter(dentistAssignment -> dentistAssignment.getAppointment().getHour().equals(hour))
                .map(dentistAssignment-> dentistAssignment.getDentist())
                .sorted((dentist1,dentist2)-> dentist1.getName().compareToIgnoreCase(dentist2.getName()))
                .toList();
        return dentistsHavingAppointmentAtHourSortedByName;

    }

    public List<Integer> getDentistIdsWithAppointmentsAtCertainDate(String date) {
        List<DentistAssignment> dentistAssignments = new ArrayList<>();
        for(DentistAssignment dentistAssignment: dentistAssignmentsRepository.getAll())
            dentistAssignments.add(dentistAssignment);

        List<Integer> dentistIdsWithAppointmentsAtCertainDate = dentistAssignments.stream()
                .filter(dentistAssignment -> dentistAssignment.getAppointment().getDate().equals(date))
                .map(dentistAssignment -> dentistAssignment.getDentist().getId())
                .toList();
        return dentistIdsWithAppointmentsAtCertainDate;
    }

    public List<Appointment> getAppointmentsStartingAtHourAndAtCertainDate(String hour, String certainDate) {
        List<Appointment> appointments = new ArrayList<>();
        for(Appointment appointment: appointmentsRepository.getAll())
            appointments.add(appointment);

        List<Appointment> appointmentsStartingAtHourAndAtCertainDate =  appointments.stream()
                .filter(appointment -> appointment.getDate().equals(certainDate))
                .filter(appointment -> appointment.getHour().equals(hour))
                .toList();
        return appointmentsStartingAtHourAndAtCertainDate;
    }

}
