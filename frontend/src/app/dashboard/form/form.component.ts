import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-form',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterModule, HttpClientModule], // Include HttpClientModule here
  templateUrl: './form.component.html',
  styleUrl: './form.component.css'
})
export class FormComponent {
  constructor(private http: HttpClient) {}

  formData: any = {
    totalTickets: '', 
    maximumCapacity: '',
    ticketReleaseRate: '',
    customerRetrievalRate: '',
    numOfVendors: '',
    numOfCustomers: ''
  };

  isRunning = false;

  toggleStartStop(): void {
    this.isRunning = !this.isRunning;
  }

  onSubmit(): void {
    console.log('Form data:', this.formData);
    this.http.post('http://localhost:8080/loadconfig', this.formData).subscribe({
      next: (response) => {
        console.log('Configuration saved successfully!', response);
      },
      error: (error) => {
        console.error('Error saving configuration:', error);
      }
    });
  }
}
