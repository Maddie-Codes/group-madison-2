/*package org.launchcode.taskcrusher.models.data;

import org.launchcode.taskcrusher.models.Kid;
import org.launchcode.taskcrusher.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KidRepository extends CrudRepository<Kid,Integer> {

    List<Kid> findAllByParentId(Long parentId);
}
*/
package org.launchcode.taskcrusher.models.data;

import org.launchcode.taskcrusher.models.Kid;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface KidRepository extends CrudRepository<Kid,Integer> {

   //List<Kid> findByParentId(Long id);

 //   Iterable<Kid> findById(Long id);
   @Query("SELECT k FROM Kid k WHERE parent.id = :id")
   Iterable<Kid> findByParentId(Long id);

    //  Optional<Kid> findByUsername(String username);

}