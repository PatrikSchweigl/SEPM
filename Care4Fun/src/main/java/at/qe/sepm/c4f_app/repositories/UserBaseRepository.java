package at.qe.sepm.c4f_app.repositories;

import at.qe.sepm.c4f_app.models.UserData;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by Stefan Mattersberger <stefan.mattersberger@student.uibk.ac.at>
 * on 20.03.2017
 */
@NoRepositoryBean
public interface UserBaseRepository<T extends UserData> extends AbstractRepository<T, Long>{
    T findFirstByUsername(String username);
}
