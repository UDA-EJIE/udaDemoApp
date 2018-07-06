import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpModule }    from '@angular/http';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { BoardComponent } from './board/board.component';
import { ListComponent } from './list/list.component';

import { ListService }  from './list/list.service';
import { TaskService }  from './task/task.service';
import { HeaderComponent } from './shared/header/header.component';
import { MenuComponent } from './shared/menu/menu.component';
import { FooterComponent } from './shared/footer/footer.component';
import { FeedbackComponent } from './rup/feedback/feedback.component';
import { DialogComponent } from './rup/dialog/dialog.component';
import { ButtonComponent } from './rup/button/button.component';
import { DetailComponent } from './detail/detail.component';
import { TaskComponent } from './task/task.component';
import { MessageComponent } from './rup/message/message.component';
import { MessageService } from './rup/message/message.service';

@NgModule({
  declarations: [
    AppComponent,
    BoardComponent,
    ListComponent,
    HeaderComponent,
    MenuComponent,
    FooterComponent,
    FeedbackComponent,
    DialogComponent,
    ButtonComponent,
    DetailComponent,
    TaskComponent,
    MessageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpModule,
    FormsModule
  ],
  providers: [ListService, TaskService, MessageService],
  bootstrap: [AppComponent]
})
export class AppModule { }
