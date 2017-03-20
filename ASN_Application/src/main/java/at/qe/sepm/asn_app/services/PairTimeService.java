package at.qe.sepm.asn_app.services;

import at.qe.sepm.asn_app.repositories.PairTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Created by Emanuel Striednig <emanuel.striednig@student.uibk.ac.at>
 * on 20.03.2017.
 */

@Component
@Scope("application")
public class PairTimeService {
	@Autowired
	private PairTimeRepository pairTimeRepository;
}
