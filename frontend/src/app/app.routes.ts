import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { EventComponent } from './event/event.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { CardComponent } from './event/card/card.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
    title: 'Home Page',
  },
  {
    path: 'event',
    component: EventComponent,
    title: 'Event Page',
  },
  {
    path:'dashboard',
    component: DashboardComponent,
    title: 'Dashboard Page',
  },
  {
    path: 'card',
    component: CardComponent,
    title: 'Card Page',
  }
];
