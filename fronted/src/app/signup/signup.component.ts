import { Component, inject } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AccountService } from '../services/account.service';
import { ToastComponent } from '../toast/toast.component';

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [FormsModule, ToastComponent],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.css'
})

export class SignupComponent {
  accountSerivce = inject(AccountService);

  toastHeading = "";
  toastDescription = "";
  toastVisible = false;

  onSubmit(form: NgForm){
    if(form.valid){
      this.accountSerivce.createAccount(form.value)
      .subscribe({
        next : res => {
          this.generateToast("Success", "Account Created");
          form.reset()
        },
        error: error => {
          const err = error.error;
          this.generateToast(err['title'], err['detail'])
        }
      })
    }
  }

  generateToast(heading:string, description:string) {
    this.toastHeading = heading;
    this.toastDescription = description;

    this.toastVisible = true;
    setTimeout(() => {
      this.toastVisible = false;
    }, 5000)
  }
}
