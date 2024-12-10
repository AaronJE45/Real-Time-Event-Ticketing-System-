import { Component,input,Input } from '@angular/core';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-card',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './card.component.html',
})
export class CardComponent {
  @Input() title: string ='';
  @Input() price: string ='';
  @Input() date: string='';
  @Input() image: string='';
  @Input() location: string='';
}
