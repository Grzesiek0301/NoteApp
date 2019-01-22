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
}
