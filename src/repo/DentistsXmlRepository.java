package repo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import domain.Dentist;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class DentistsXmlRepository extends FileRepository<Integer, Dentist> {
    public DentistsXmlRepository(String filename) throws ExceptionRepository {
        super(filename);
    }

    @Override
    protected void readFromFile() throws ExceptionRepository {
        try {
        XmlMapper xmlMapper = new XmlMapper();
        File xmlFileToReadFrom = new File(this.fileName);

        // create the type reference of the xml Dentist list
        TypeReference<List<Dentist>> listTypeReference =
                new com.fasterxml.jackson.core.type.TypeReference<List<Dentist>>() {};

        ////deseralize the entire xml array into a list of Dentist objects
        List<Dentist> dentists = xmlMapper.readValue(xmlFileToReadFrom, listTypeReference);
            for (Dentist dentist : dentists) {
                super.add(dentist.getId(), dentist);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

    @Override
    protected void writeToFile()  {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            File xmlFileToWriteIn = new File(this.fileName);
            xmlMapper.writeValue(xmlFileToWriteIn, super.getAll());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
