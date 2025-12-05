package grupo2.com.ecoPoint.Controller;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import grupo2.com.ecoPoint.Model.Entity.ItemResiduo;
import grupo2.com.ecoPoint.Service.ItemResiduoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/itemresiduo")
public class ItemResiduoController {
    
    private final ItemResiduoService itemResiduoService;

    public ItemResiduoController(ItemResiduoService itemResiduoService) {
        this.itemResiduoService = itemResiduoService;
    }

    @GetMapping
    public List<ItemResiduo> getAllItemResiduo() {
        return itemResiduoService.getAllItemResiduo();
    }

    @GetMapping("/{id}")
    public ItemResiduo getItemResiduoById(@PathVariable Long id) {
        return itemResiduoService.getItemResiduoById(id);
    
    }

    @PostMapping
    public ItemResiduo salvarItemResiduo(@RequestBody ItemResiduo itemResiduo) {
        return itemResiduoService.salvarItemResiduo(itemResiduo);
    }


    @PutMapping("/{id}")
    public ItemResiduo atualizarItemResiduo(@PathVariable Long id, @RequestBody ItemResiduo itemResiduo) {
        return itemResiduoService.atualizarItemResiduo(id, itemResiduo);
    }

    @DeleteMapping("/{id}")
    public void deletarItemResiduo(@PathVariable Long id) {
        itemResiduoService.deletarItemResiduo(id);
    }

}
