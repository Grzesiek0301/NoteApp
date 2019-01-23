package grzegorzurbanski.notebook.notebooksbapp.app;

import grzegorzurbanski.notebook.notebooksbapp.app.viewmodel.NoteViewModel;
import grzegorzurbanski.notebook.notebooksbapp.app.viewmodel.NotebookViewModel;
import grzegorzurbanski.notebook.notebooksbapp.db.NotebookRepository;
import grzegorzurbanski.notebook.notebooksbapp.model.Note;
import grzegorzurbanski.notebook.notebooksbapp.model.Notebook;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Mapper {

    private NotebookRepository notebookRepository;

    public Mapper(NotebookRepository notebookRepository){
        this.notebookRepository = notebookRepository;
    }


    public Notebook convertNotebookViewModelToNotebookEntity(NotebookViewModel notebookViewModel){
        return new Notebook(notebookViewModel.getId(), notebookViewModel.getName());
    }

    public NoteViewModel convertToNoteViewModel(Note entity) {
        NoteViewModel viewModel = new NoteViewModel();
        viewModel.setTitle(entity.getTitle());
        viewModel.setId(entity.getId().toString());
        viewModel.setLastModifiedOn(entity.getLastModyfication());
        viewModel.setText(entity.getText());
        viewModel.setNotebookId(entity.getNotebook().getId().toString());

        return viewModel;
    }

    public Note convertNoteViewModelToNoteEntity(NoteViewModel noteViewModel) {

        Notebook notebook = this.notebookRepository.findById(UUID.fromString(noteViewModel.getNotebookId())).get();
        Note note = new Note(noteViewModel.getId(), noteViewModel.getTitle(), noteViewModel.getText(), notebook);
    return note;
    }
}
