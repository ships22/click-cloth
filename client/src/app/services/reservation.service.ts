import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
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

  constructor(private httpClient: HttpClient) {}

  get refresh$() {
    return this._refresh$;
  }
  getReservationsByShop(shopId: number): Observable<Reservation[]> {
    return this.httpClient.get<Reservation[]>(
      this.base_url + "reservationsByShop/" + shopId
    );
  }

  updateStatusAndStock(
    status: string,
    reservationId: number,
    stockId: number,
    quantity: number
  ): Observable<any> {
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
        null, {responseType: 'text' as 'json'}
      )
      .pipe(tap(() => this._refresh$.next()));
  }
}
