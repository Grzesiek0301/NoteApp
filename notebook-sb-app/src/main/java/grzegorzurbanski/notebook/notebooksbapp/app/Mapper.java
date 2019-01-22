package grzegorzurbanski.notebook.notebooksbapp.app;

import grzegorzurbanski.notebook.notebooksbapp.app.viewmodel.NoteViewModel;
import grzegorzurbanski.notebook.notebooksbapp.app.viewmodel.NotebookViewModel;
import grzegorzurbanski.notebook.notebooksbapp.model.Note;
import grzegorzurbanski.notebook.notebooksbapp.model.Notebook;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


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

}
