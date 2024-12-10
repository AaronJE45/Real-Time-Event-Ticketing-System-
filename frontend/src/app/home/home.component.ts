import { Component } from '@angular/core';
import { HeroSectionComponent } from "./hero-section/hero-section.component";
import { FeaturesComponent } from "./features/features.component";

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [HeroSectionComponent, FeaturesComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
