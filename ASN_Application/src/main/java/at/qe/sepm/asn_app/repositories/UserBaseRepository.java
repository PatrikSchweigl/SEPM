package at.qe.sepm.asn_app.repositories;

import at.qe.sepm.asn_app.models.User;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by stefa on 23.03.2017.
 */
@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends AbstractRepository<T, Long>{
    T findFirstByUsername(String username);
}
