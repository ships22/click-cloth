import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { environment } from "src/environments/environment";
import { Subject, Observable } from "rxjs";
import { Reservation } from "../models/reservation";
import { tap } from "rxjs/operators";

@Injectable({
  providedIn: "root",
})
export class ReservationService {
  private base_url = environment.api_url;
  private _refresh$ = new Subject<void>();
  private token;
  constructor(private httpClient: HttpClient) {
    this.token = 'Bearer ' + this.getToken();
  }

  get refresh$() {
    return this._refresh$;
  }
  getToken():string {
    return localStorage.getItem('token');
  }
  getReservationsByShop(shopId: number): Observable<Reservation[]> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient.get<Reservation[]>(
      this.base_url + "reservationsByShop/" + shopId, {headers}
    );
  }

  updateStatusAndStock(
    status: string,
    reservationId: number,
    stockId: number,
    quantity: number
  ): Observable<any> {
    const headers = new HttpHeaders().set("Authorization", this.token );
    return this.httpClient
      .post<any>(
        this.base_url +
          "update/" +
          status +
          "/reservation/" +
          reservationId +
          "/product/" +
          quantity +
          "/stock/" +
          stockId,
        null, { headers, responseType: 'text' as 'json'}
      )
      .pipe(tap(() => this._refresh$.next()));
  }
}
