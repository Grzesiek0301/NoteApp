package grzegorzurbanski.notebook.notebooksbapp.app.viewmodel;

import javax.validation.constraints.NotNull;

public class NotebookViewModel {

private String id;

private String name;

private int nbOfNotes;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNbOfNotes() {
        return nbOfNotes;
    }

    public void setNbOfNotes(int nbOfNotes) {
        this.nbOfNotes = nbOfNotes;
    }
}
