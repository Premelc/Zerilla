package com.premelc.zerilla.repositories;

import com.premelc.zerilla.models.Invitation;
import com.premelc.zerilla.models.Organiser;
import org.springframework.data.repository.CrudRepository;

public interface InvitationRepository extends CrudRepository<Invitation, Long> {
}
