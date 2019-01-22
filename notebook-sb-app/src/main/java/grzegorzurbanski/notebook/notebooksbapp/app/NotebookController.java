package grzegorzurbanski.notebook.notebooksbapp.app;

import grzegorzurbanski.notebook.notebooksbapp.app.viewmodel.NotebookViewModel;
import grzegorzurbanski.notebook.notebooksbapp.db.NotebookRepository;
import grzegorzurbanski.notebook.notebooksbapp.model.Notebook;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {

    private NotebookRepository notebookRepository;
    private  Mapper mapper;


    public NotebookController(NotebookRepository notebookRepository, Mapper mapper){
        this.notebookRepository = notebookRepository;
        this.mapper = mapper;
    }

    @GetMapping("/all")
    public List<Notebook> notebooks(){
        return  this.notebookRepository.findAll();
    }


    @PostMapping
    public Notebook save(@RequestBody NotebookViewModel notebookViewModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }

        Notebook notebook = mapper.convertNotebookViewModelToNotebookEntity(notebookViewModel);
        this.notebookRepository.save(notebook);
        return notebook;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id){
        this.notebookRepository.deleteById(UUID.fromString(id));
    }
}
