package org.launchcode.taskcrusher.models.data;

import org.launchcode.taskcrusher.models.Kid;
import org.launchcode.taskcrusher.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KidRepository extends CrudRepository<Kid,Integer> {

    List<Kid> findAllByParentId(Long parentId);
}
