import { Component, OnInit } from '@angular/core';
import { Categories } from 'src/app/models/products/categories';
import { CategoryService } from 'src/app/services/category.service';
import { MsgService } from 'src/app/services/msg.service';


@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.sass']
})
export class CategoryComponent implements OnInit {

  columns: string[] = ["id", "category", "options"];
  categoryList: Categories[];

  constructor(private cateoryService: CategoryService, public messageService: MsgService) { }

  ngOnInit(): void {
    this.getAllCategory();
    this.cateoryService.refresh$
    .subscribe(response => this.getAllCategory());
  }
  submit(category) {
    this.cateoryService.addCategory(category)
    .subscribe(data => {
      this.messageService.sendMessage(data.name + ' ajouté');
    })
  }
  getAllCategory() {
    this.cateoryService.getAllCategory()
    .subscribe(data => {this.categoryList = data;
       console.log('test category list', data)}
    )
  }
  deleteCategory(id) {
    this.cateoryService.deleteCategory(id)
    .subscribe(data => console.log('test category delete', data)
    )
  }

}
