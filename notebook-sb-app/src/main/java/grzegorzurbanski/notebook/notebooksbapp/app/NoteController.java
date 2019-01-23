package grzegorzurbanski.notebook.notebooksbapp.app;


import grzegorzurbanski.notebook.notebooksbapp.app.viewmodel.NoteViewModel;
import grzegorzurbanski.notebook.notebooksbapp.db.NoteRepository;
import grzegorzurbanski.notebook.notebooksbapp.db.NotebookRepository;
import grzegorzurbanski.notebook.notebooksbapp.model.Note;
import grzegorzurbanski.notebook.notebooksbapp.model.Notebook;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/notes")
@CrossOrigin
public class NoteController {

    private NoteRepository noteRepository;
    private NotebookRepository notebookRepository;
    private Mapper mapper;


    public NoteController(NoteRepository noteRepository, Mapper mapper, NotebookRepository notebookRepository){
        this.noteRepository = noteRepository;
        this.mapper = mapper;
        this.notebookRepository = notebookRepository;

    }

    @GetMapping("/all")
    public List<NoteViewModel> notes(){

        List<Note> notes = this.noteRepository.findAll();

        List<NoteViewModel> notesViewModel = notes.stream().map(note -> this.mapper.convertToNoteViewModel(note)).collect(Collectors.toList());

        return  notesViewModel;
    }

    @GetMapping("byNotebookId/{id}")
    public List<NoteViewModel> notesByNotebookId(@PathVariable String id){
        List<Note> notes = new ArrayList<Note>();
        Optional<Notebook> byId = this.notebookRepository.findById(UUID.fromString(id));

      if(byId.isPresent()){
          notes = this.noteRepository.findAllByNotebook(byId.get());
      }

      List<NoteViewModel> notesViewModel = notes.stream().map(note -> this.mapper.convertToNoteViewModel(note)).collect(Collectors.toList());

        return notesViewModel;
    }

    @PostMapping
    public NoteViewModel updateNote(@RequestBody NoteViewModel noteViewModel, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new ValidationException();
        }

        Note note = this.mapper.convertNoteViewModelToNoteEntity(noteViewModel);
        this.noteRepository.save(note);

        return  noteViewModel;
    }

    @DeleteMapping("/{id}")
    public void deleteNote(@PathVariable String id){
        this.noteRepository.deleteById(UUID.fromString(id));
    }
}
