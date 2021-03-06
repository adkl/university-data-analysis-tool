package ba.unsa.etf.bp.udat.repositories;

import ba.unsa.etf.bp.udat.models.ExamFact;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public interface ExamFactRepository extends PagingAndSortingRepository<ExamFact, Long> {
    @Query("SELECT ex FROM ExamFact ex")
    Collection<ExamFact> findAll();

    @Query("SELECT ef.academicYearDim, AVG(ef.turnout), AVG(ef.averagePoints) FROM ExamFact ef" +
            " group by ef.academicYearDim order by ef.academicYearDim")
    List<Object[]> filterByAllYears();

    @Query("SELECT ef.departmentDim, AVG(ef.turnout), AVG(ef.averagePoints) FROM ExamFact ef" +
            " group by ef.departmentDim order by ef.departmentDim")
    List<Object[]> filterByAllDepartments();

    @Query("SELECT ef.courseDim, AVG(ef.turnout), AVG(ef.averagePoints) FROM ExamFact ef" +
            " group by ef.courseDim order by ef.courseDim")
    List<Object[]> filterByAllCourses();

    @Query("SELECT SUM(ef.turnout) FROM ExamFact ef, AcademicYearDim ayd, DepartmentDim depd, CourseDim cod" +
           " WHERE ((ef.academicYearDim = ayd AND ayd.id = :ay) OR (:ay = -1)) AND " +
           " ((ef.departmentDim = depd AND depd.id = :dep) OR (:dep = -1)) AND " +
           "((ef.courseDim = cod AND cod.id = :course) OR (:course = -1))")
    Integer turnoutByAcademicYearDepartmentCourse(@Param("ay") 
    Long ay, @Param("dep") Long dep, @Param("course") Long course);
    @Query("SELECT AVG(ef.averagePoints) FROM ExamFact ef, AcademicYearDim ayd, DepartmentDim depd, CourseDim cod" +
           " WHERE ((ef.academicYearDim = ayd AND ayd.id = :ay) OR (:ay = -1)) AND " +
           " ((ef.departmentDim = depd AND depd.id = :dep) OR (:dep = -1)) AND " +
           "((ef.courseDim = cod AND cod.id = :course) OR (:course = -1))")
    BigDecimal averagePointsByAcademicYearDepartmentCourse(@Param("ay")
    Long ay, @Param("dep") Long dep, @Param("course") Long course);

}

