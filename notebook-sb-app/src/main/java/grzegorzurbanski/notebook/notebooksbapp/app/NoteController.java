package grzegorzurbanski.notebook.notebooksbapp.app;


import grzegorzurbanski.notebook.notebooksbapp.db.NotebookRepository;
import grzegorzurbanski.notebook.notebooksbapp.model.Notebook;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NoteController {

    private NotebookRepository notebookRepository;


    public NoteController(NotebookRepository notebookRepository){
        this.notebookRepository = notebookRepository;

    }

    @GetMapping("/all")
    public List<Notebook> notebooks(){
        return  this.notebookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Notebook> getNotebookById(@PathVariable String id){
        return this.notebookRepository.findById(UUID.fromString(id));
    }

}
