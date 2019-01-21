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

        Notebook notebook = new Notebook("NotebookPierwszy");
        Notebook notebook2 = new Notebook("Notebook Drugi");
        this.notebookRepository.save(notebook);
        this.notebookRepository.save(notebook2);

        Note note = new Note("tytul", "tekst", notebook);
        Note note2 = new Note("note2", "tekst2", notebook);
        this.noteRepository.save(note);
        this.noteRepository.save(note2);

        Note note3 = new Note("tytul3", "tekst3", notebook2);
        Note note4 = new Note("note4", "tekst4", notebook2);
        this.noteRepository.save(note3);
        this.noteRepository.save(note4);

        System.out.println("Database initialized");

    }
}
