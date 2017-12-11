package ba.unsa.etf.bp.udat.services;

import ba.unsa.etf.bp.udat.models.EnrollmentFact;
import ba.unsa.etf.bp.udat.repositories.EnrollmentFactRepository;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EnrollmentFactService extends BaseService<EnrollmentFact, EnrollmentFactRepository>{

    public EnrollmentFact save(EnrollmentFact model) throws ServiceException {
        Collection<EnrollmentFact> lista = repository.findAll();
        for(EnrollmentFact ef : lista )
        {
            if(ef.getAcademicYearDim().equals(model.getAcademicYearDim()) &&
                    ef.getDepartmentDim().equals(model.getDepartmentDim()) &&
                    ef.getSemesterDim().equals(model.getSemesterDim()) &&
                    ef.isBudget().equals(model.isBudget()) &&
                    ef.getIsRepeating().equals(model.getIsRepeating()) &&
                    ef.getEnrolledCount().equals(model.getEnrolledCount()))
                return null;
        }
        return super.save(model);
    }
    public Integer filterByAcademicYear(Integer ay) throws ServiceException
    {
        return repository.filterByAcademicYear(ay);
    }
    public Integer filterByDepartment(String dep) throws ServiceException
    {
        return repository.filterByDepartment(dep);
    }
    public Integer filterByActiveYear() throws ServiceException
    {
        return repository.filterByActiveYear();
    }



}