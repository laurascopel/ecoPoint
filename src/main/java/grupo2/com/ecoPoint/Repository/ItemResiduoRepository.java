package grupo2.com.ecoPoint.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import grupo2.com.ecoPoint.Model.Entity.ItemResiduo;

@Repository
public interface ItemResiduoRepository extends JpaRepository<ItemResiduo, Long> {

public ItemResiduo findItemResiduoById(Long id);

}

