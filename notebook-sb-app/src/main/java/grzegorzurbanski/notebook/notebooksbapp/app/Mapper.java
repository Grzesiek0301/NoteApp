package grzegorzurbanski.notebook.notebooksbapp.app;

import grzegorzurbanski.notebook.notebooksbapp.app.viewmodel.NotebookViewModel;
import grzegorzurbanski.notebook.notebooksbapp.model.Notebook;
import org.springframework.stereotype.Component;

@Component
public class Mapper {


    public Notebook convertNotebookViewModelToNotebookEntity(NotebookViewModel notebookViewModel){
        return new Notebook(notebookViewModel.getId(), notebookViewModel.getName());
    }

}
