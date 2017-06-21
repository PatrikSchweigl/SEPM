package at.qe.sepm.asn_app.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import at.qe.sepm.asn_app.models.nursery.NurseryInformation;
import at.qe.sepm.asn_app.models.nursery.PrivateMessage;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
public interface NurseryInformationRepository extends AbstractRepository<NurseryInformation, Long> {

    @Query("SELECT p FROM NurseryInformation p WHERE :date = p.originDate")
    NurseryInformation getInformationByOriginDate(@Param("date") Date date);
}
