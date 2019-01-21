package grzegorzurbanski.notebook.notebooksbapp.app;

import grzegorzurbanski.notebook.notebooksbapp.db.NotebookRepository;
import grzegorzurbanski.notebook.notebooksbapp.model.Notebook;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notebooks")
@CrossOrigin
public class NotebookController {

    private NotebookRepository notebookRepository;

    public NotebookController(NotebookRepository notebookRepository){
        this.notebookRepository = notebookRepository;
    }

    @GetMapping("/all")
    public List<Notebook> notebooks(){
        return  this.notebookRepository.findAll();
    }

}
