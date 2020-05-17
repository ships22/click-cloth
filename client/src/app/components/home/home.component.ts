import { Component, OnInit } from '@angular/core';
import { OwlOptions } from 'ngx-owl-carousel-o';


import { HttpClient } from '@angular/common/http';
import { throwError } from 'rxjs';
export interface PhotosApi {
  albumId?: number;
  id?: number;
  title?: string;
  url?: string;
  thumbnailUrl?: string;
}

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.sass']
})
export class HomeComponent implements OnInit {

  constructor(
    private readonly http: HttpClient,) { }

  ngOnInit(): void {
    this.fetch()
  }
  apiData: PhotosApi;
  limit: number = 10; // <==== Edit this number to limit API results
  customOptions: OwlOptions = {
    loop: true,
    autoplay: true,
    center: true,
    dots: false,
    autoHeight: true,
    autoWidth: true,
    responsive: {
      0: {
        items: 1,
      },
      600: {
        items: 4,
      },
      1000: {
        items: 4,
      }
    }
  }
  fetch() {
    const api = `https://jsonplaceholder.typicode.com/albums/1/photos?_start=0&_limit=${this.limit}`;
    const http$ = this.http.get<PhotosApi>(api);

    http$.subscribe(
      res => this.apiData = res,
      err => throwError(err)
    )
  }
}
