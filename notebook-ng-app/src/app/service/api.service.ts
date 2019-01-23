import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Notebook} from "../notes/model/notebook";
import {Observable} from "rxjs";
import {FeedbackViewModel} from "../feedback/feedback.component";
import {Note} from "../notes/model/note";

@Injectable({
  providedIn: 'root'
})
export class ApiService {

  private  BASE_URL = "http://localhost:8080//api";
  private ALL_NOTEBOOKS_URL = this.BASE_URL+'\\notebooks\\all';
  private SEND_FEEDBACK_URL = this.BASE_URL+'\\feedback';
  private SAVE_UPDATE_NOTEBOOK = this.BASE_URL+'\\notebooks';
  private DELETE_NOTEBOOK = this.BASE_URL+'\\notebooks\\';
  private ALL_NOTES_URL = this.BASE_URL+'\\notes\\all';
  private NOTES_BY_NOTEBOOK_ID = this.BASE_URL+'\\notes\\byNotebookId\\';
  private UPDATE_NOTE_BY_ID=this.BASE_URL+'\\notes';
  private DELETE_NOTE=this.BASE_URL+'\\notes\\';


  constructor(private http:HttpClient) { }

  getAllNotebooks(): Observable<Notebook[]>{
    return this.http.get<Notebook[]>(this.ALL_NOTEBOOKS_URL);
  }

  postFeedback(feedback: FeedbackViewModel): Observable<any> {
    return this.http.post(this.SEND_FEEDBACK_URL, feedback);
  }

  postNotebook(notebook: Notebook): Observable<Notebook>{
    return this.http.post<Notebook>(this.SAVE_UPDATE_NOTEBOOK, notebook);
  }

  deleteNotebook(notebookId: string): Observable<any>{
    return this.http.delete(this.DELETE_NOTEBOOK+notebookId);
  }

  getAllNotes(): Observable<Note[]>{
    return this.http.get<Note[]>(this.ALL_NOTES_URL);
  }

  getNotesByNotebookId(notebookId: string): Observable<Note[]>{
    return this.http.get<Note[]>(this.NOTES_BY_NOTEBOOK_ID +notebookId);
  }

  updateNote(updatedNote: Note): Observable<Note> {
    return this.http.post<Note>(this.UPDATE_NOTE_BY_ID, updatedNote);
  }

  deleteNote(note: Note): Observable<any>{
    return this.http.delete(this.DELETE_NOTE+note.id);
  }


}
