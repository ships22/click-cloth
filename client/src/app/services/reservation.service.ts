import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Subject, Observable } from 'rxjs';
import { Reservation } from '../models/reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

  private base_url = environment.api_url;
  private _refresh$ = new Subject<void>();

  constructor(private httpClient: HttpClient) { }
  get refresh$() {
    return this._refresh$;
  }
  getReservationsByShop(shopId: number):Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(this.base_url + 'reservationsByShop/' + shopId);
  }
}
