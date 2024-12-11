import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class LogService {
  private apiLogsUrl = 'http://localhost:8080/logs'; // Replace with your actual API endpoint

  constructor(private http: HttpClient) {}

  // Method to get logs from the backend
  getLogs(): Observable<string[]> {
    return this.http.get<string[]>(this.apiLogsUrl);
  }
}
