package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public interface NurseryInformationRepository extends AbstractRepository<NurseryInformation, Long> {

    @Query("SELECT p FROM NurseryInformation p WHERE :date = p.originDate")
    NurseryInformation getInformationByOriginDate(@Param("date") Date date);
}
