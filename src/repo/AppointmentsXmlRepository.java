package repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import domain.Appointment;
import domain.Dentist;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AppointmentsXmlRepository extends FileRepository<Integer, Appointment> {
    public AppointmentsXmlRepository(String filename) throws ExceptionRepository {
        super(filename);
    }

    @Override
    protected void readFromFile() throws ExceptionRepository {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            File xmlFileToReadFrom = new File(this.fileName);

            // create the type reference of the xml Appointment list
            TypeReference<List<Appointment>> listTypeReference =
                    new com.fasterxml.jackson.core.type.TypeReference<List<Appointment>>() {};

            ////deseralize the entire xml array into a list of Appointment objects
            List<Appointment> appointments = xmlMapper.readValue(xmlFileToReadFrom, listTypeReference);
            for (Appointment appointment : appointments) {
                super.add(appointment.getId(), appointment);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    protected void writeToFile() {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            File xmlFileToWriteIn = new File(this.fileName);
            xmlMapper.writeValue(xmlFileToWriteIn, super.getAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

