import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Notebook} from "./model/notebook";
import {ApiService} from "../service/api.service";
import {Note} from "./model/note";

@Component({
  selector: 'app-notes',
  templateUrl: './notes.component.html',
  styleUrls: ['./notes.component.css']
})
export class NotesComponent implements OnInit {

  notebooks: Notebook[]=[];
  notes: Note[]=[];
  currentNotebook: Notebook = null;

  constructor(private apiService:ApiService) { }

  ngOnInit() {
    this.getAllNotebooks();
    this.getAllNotes();
  }

  public getAllNotebooks(){
   this.apiService.getAllNotebooks().subscribe(
      res => {
        this.notebooks= res;
      },
      error => {
        alert("Error has occured");
      }
    );
  }

  public getAllNotes(){
    this.apiService.getAllNotes().subscribe(
      res => {
        this.notes = res;
        this.currentNotebook = null;

      },
      error => {
        alert("Error occured get all notes");
      }
    )
  }

  public getNotesByNotebookId(notebook: Notebook){
    this.apiService.getNotesByNotebookId(notebook.id).subscribe(
      res => {
        this.notes = res;
        this.currentNotebook= notebook;
      },
      error => {
        alert("Error getNotesByNotebook ID");
      }
    )
  }

  createNotebook() {
    let newNotebook: Notebook = {
      name:'New notebook',
      id:null,
      nbOfNotes:0
    }
    this.apiService.postNotebook(newNotebook).subscribe(
      res=> {
        newNotebook.id = res.id;
        this.notebooks.push(newNotebook);
        this.getNotesByNotebookId(newNotebook);
        this.currentNotebook = newNotebook;
      },
      error => {
        alert("New notebook error ocured")
      }

    )
  }

  updateNotebook(updatedNotebook: Notebook) {
    this.apiService.postNotebook(updatedNotebook).subscribe(
      res =>{
      },
      error => {
        alert("Update error")
      }
    )
  }

  deleteNotebook(deleteNotebook: Notebook) {
    if(confirm("Are you sure ?")){
      this.apiService.deleteNotebook(deleteNotebook.id).subscribe(
        res => {
         let indexOfNotebook = this.notebooks.indexOf(deleteNotebook);
         this.notebooks.splice(indexOfNotebook,1);

        },
        error => {
          alert("Delete error occured");
        }
      )
    }
  }

  updateNote(updatedNote: Note) {
    this.apiService.updateNote(updatedNote).subscribe(
      res=> {

      },
      error => {
        alert("Update note error occured");
      }
    )
  }

  deleteNote(note: Note) {
    if(confirm("Are you sure?")){
      this.apiService.deleteNote(note).subscribe(
        res => {
          let indexOfNote = this.notes.indexOf(note);
          this.notes.splice(indexOfNote, 1);
        },
        error =>{
          alert("Error occured while delete note");
        }
      )
    }
  }

  createNewNote(currentNotebook: Notebook) {

    let note: Note ={
      id:null,
      title:"New Note Title",
      text:"New note",
      notebookId:currentNotebook.id,
      lastModifiedOn: null
    }
    this.apiService.updateNote(note).subscribe(
      res => {
        this.notes.push(res);
      },
      error => {
        alert("Error has occured while create new note");
      }
    )
  }
}
