import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule }    from '@angular/http';

import { AppComponent } from './app.component';
import { BoardComponent } from './board/board.component';
import { ListComponent } from './list/list.component';

import { ListService }  from './list/list.service';
import { HeaderComponent } from './shared/header/header.component';
import { MenuComponent } from './shared/menu/menu.component';
import { FooterComponent } from './shared/footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    ListComponent,
    HeaderComponent,
    MenuComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    HttpModule
  ],
  providers: [ListService],
  bootstrap: [AppComponent]
})
export class AppModule { }
