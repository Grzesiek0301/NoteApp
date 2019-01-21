package grzegorzurbanski.notebook.notebooksbapp.app.viewmodel;

import javax.validation.constraints.NotNull;

public class NotebookViewModel {

private String id;

private String name;

private int nbNotes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @NotNull
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbNotes() {
        return nbNotes;
    }

    public void setNbNotes(int nbNotes) {
        this.nbNotes = nbNotes;
    }
}
