package grupo2.com.ecoPoint.Service;
import java.util.List;
import org.springframework.stereotype.Service;
import grupo2.com.ecoPoint.Model.Entity.ItemResiduo;
import grupo2.com.ecoPoint.Repository.ItemResiduoRepository;

@Service
public class ItemResiduoService {
    
    private final ItemResiduoRepository itemResiduoRepository;

    public ItemResiduoService(ItemResiduoRepository itemResiduoRepository) {
        this.itemResiduoRepository = itemResiduoRepository;
    }

    public List<ItemResiduo> getAllItemResiduo() {
        return itemResiduoRepository.findAll();
    }

    public ItemResiduo getItemResiduoById(Long id) {
        return itemResiduoRepository.findItemResiduoById(id);       
    }

    public ItemResiduo salvarItemResiduo(ItemResiduo itemResiduo) {
        return itemResiduoRepository.save(itemResiduo);
    }

    public ItemResiduo atualizarItemResiduo(Long id, ItemResiduo itemResiduoAtualizado) {
        ItemResiduo itemResiduo = itemResiduoRepository.findItemResiduoById(id);

        itemResiduo.setNome(itemResiduoAtualizado.getNome());

        return itemResiduoRepository.save(itemResiduo);
        
    };

    public void deletarItemResiduo(Long id) {
        itemResiduoRepository.deleteById(id);
    }
}
