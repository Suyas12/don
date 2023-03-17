import { Component, OnInit } from '@angular/core';
import { FormGroup,FormBuilder } from '@angular/forms';
import { blogData } from '../dash/dash.model';
import { AuthService } from '../service/auth.service';

@Component({
  selector: 'app-admin-con',
  templateUrl: './admin-con.component.html',
  styleUrls: ['./admin-con.component.css']
})
export class AdminConComponent implements OnInit{
  formvalue!: FormGroup;
  blogModelobj:blogData = new blogData;
  // GetAlls:any;
  Getblog:any;
  constructor(private dis:AuthService, private form:FormBuilder)
  {
    dis.Getblog().subscribe((display)=>
    {
      // console.warn("display",display)
      console.log(display);
      this.Getblog=display;
    }
    );
  }
  ngOnInit(): void {
    this.formvalue = this.form.group({
    Username: [''],
    // role: [''],
    // Password: [''],
    title: [''],
    description: [''],
    url: ['']
  })

}
  refresh(){
    this.dis.Getblog().subscribe((data)=>{
      this.Getblog=data;
    });
  }

  addedBlog(){
    this.blogModelobj.Username = this.formvalue.value.Username;
    // this.blogModelobj.role = this.formvalue.value.role;
    // this.blogModelobj.Password = this.formvalue.value.Password;
    this.blogModelobj.title= this.formvalue.value.title;
    this.blogModelobj.description= this.formvalue.value.description;
    this.blogModelobj.url= this.formvalue.value.url;
    
    this.dis.postBlogs(this.blogModelobj).subscribe(res=>{
      console.log(res);
      alert("New Data added");
      this.formvalue.reset();
      this.refresh();
    })
  }

  editiBlogs(data:any){
    this.formvalue.controls['Username'].setValue(data.Username);
    // this.formvalue.controls['role'].setValue(data.role);
    // this.formvalue.controls['Password'].setValue(data.Password);
    this.formvalue.controls['title'].setValue(data.title);
    this.formvalue.controls['description'].setValue(data.description);
    this.formvalue.controls['url'].setValue(data.url);
  }

  delBlogs(data:any){
    this.dis.deleteBlogs(data.id).subscribe(res=>{
      console.log(res);
      alert("Data deleted");
      this.formvalue.reset();
      this.refresh();
    })
  }
}

