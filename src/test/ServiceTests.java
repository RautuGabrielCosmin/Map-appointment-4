package test;

import domain.Dentist;
import repo.ExceptionRepository;
import repo.FakeRepository;
import repo.IRepository;
import service.ServiceDentists;

public class ServiceTests {

    public void testGetAll_correctSize_NoError() {

        IRepository<Integer, Dentist> fakeDentistsRepo = new FakeRepository<Integer, Dentist>();
        ServiceDentists dentistsService = new ServiceDentists(fakeDentistsRepo);

        Iterable<Dentist> dentists = null;
        try {
            dentists = dentistsService.getAll();
        } catch (ExceptionRepository e) {
            throw new RuntimeException(e);
        }
        int size = 0;
        for (Dentist dentist : dentists) {
            size++;
        }

        assert size == 2;


    }
}