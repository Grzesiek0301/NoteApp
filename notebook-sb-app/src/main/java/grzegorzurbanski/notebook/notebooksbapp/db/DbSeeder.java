package grzegorzurbanski.notebook.notebooksbapp.db;

import grzegorzurbanski.notebook.notebooksbapp.model.Note;
import grzegorzurbanski.notebook.notebooksbapp.model.Notebook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;


@Component
@ConditionalOnProperty(name="noteit.db.recreate", havingValue="true")
public class DbSeeder implements CommandLineRunner {

    private NoteRepository noteRepository;
    private NotebookRepository notebookRepository;

    public DbSeeder(NoteRepository noteRepository, NotebookRepository notebookRepository) {
        this.noteRepository = noteRepository;
        this.notebookRepository = notebookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

        this.noteRepository.deleteAll();
        this.notebookRepository.deleteAll();

        Notebook notebook = new Notebook("NotebookName");
        this.notebookRepository.save(notebook);

        Note note = new Note("tytul", "tekst", notebook);
        this.noteRepository.save(note);

        System.out.println("Database initialized");

    }
}
