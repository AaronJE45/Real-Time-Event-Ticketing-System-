import { Component, OnInit, Inject } from '@angular/core';
import { LogService } from '../log.service';

import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-log',
  standalone: true,
  templateUrl: './log.component.html',
  styleUrls: ['./log.component.css'],
  imports: [CommonModule],
})
export class LogComponent  {
  simulationRunning: boolean = false;
  logs: string[] = [];

  constructor(private logService: LogService) {}


}
