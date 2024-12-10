import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CardComponent } from "./event/card/card.component";
import { HeroSectionComponent } from "./home/hero-section/hero-section.component";
import { FeaturesComponent } from "./home/features/features.component";
import { HomeComponent } from "./home/home.component";
import { RouterModule } from '@angular/router';
import { EventComponent } from './event/event.component';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet,RouterModule,FormsModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'frontend';
}
