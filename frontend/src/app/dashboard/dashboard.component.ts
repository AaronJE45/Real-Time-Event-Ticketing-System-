import { Component, OnInit, OnDestroy } from '@angular/core';
import { LogService } from './log.service'; // Import the LogService
import { signal } from '@angular/core'; // Assuming signal is imported from Angular's signals API
import { Subscription } from 'rxjs';
import { FormComponent } from './form/form.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css'],
  imports: [FormComponent,CommonModule] // Import the LogService in the component
})
export class DashboardComponent implements OnInit, OnDestroy {
  logs = signal<string[]>([]); // signal for logs
  private logSubscription!: Subscription; // Subscription to stop polling when the component is destroyed

  constructor(private logService: LogService) {}

  ngOnInit(): void {
    this.startPolling(); // Start polling on component initialization
  }

  ngOnDestroy(): void {
    if (this.logSubscription) {
      this.logSubscription.unsubscribe(); // Clean up the subscription when the component is destroyed
    }
  }

  startPolling(): void {
    console.log('Starting polling...');
    // Poll the logs every 0.5 seconds 
    this.logSubscription = new Subscription();
    const intervalId = setInterval(() => {
      this.fetchLogs();
    }, 500);
    this.logSubscription.add(() => clearInterval(intervalId)); // You can adjust the interval to your desired polling frequency
  }

  fetchLogs(): void {
    console.log('Fetching logs...');
    this.logService.getLogs().subscribe({
      next: (data) => {
        this.logs.set(data); // Update logs with new data
      },
      error: (error) => {
        console.error('Error fetching logs:', error);
      },
      complete: () => {
        console.log('Log fetching completed');
      }
    });
  }

  clearLogs(): void {
    this.logs.set([]); 
  }

}
