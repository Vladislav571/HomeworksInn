package attestation.attestation01.repositories;

import attestation.attestation01.model.User;

import java.util.List;

public interface UsersRepository {

    void create(User user);
    User findById(String id);
    List<User> findAll();
    void update(User user);
    void deleteById(String id);
    void deleteAll();
}
