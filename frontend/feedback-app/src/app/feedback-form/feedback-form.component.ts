import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { FeedbackService } from '../services/feedback.service';
import { ContactType } from '../models/contact-type.model';

@Component({
  selector: 'app-feedback-form',
  templateUrl: './feedback-form.component.html',
  styleUrls: ['./feedback-form.component.css']
})
export class FeedbackFormComponent {

  form: FormGroup;
  submitted = false;
  toastVisible = false;

  contactTypes = Object.values(ContactType);

  constructor(
    private fb: FormBuilder,
    private feedbackService: FeedbackService
  ) {
    this.form = this.fb.group({
      name: ['', [Validators.maxLength(100)]],
      email: ['', [Validators.email, Validators.maxLength(100)]],
      contactType: [null, Validators.required],
      message: ['', [Validators.required, Validators.maxLength(1000)]]
    });
  }

  get f() {
    return this.form.controls;
  }

  submit() {
    this.submitted = true;

    if (this.form.invalid) {
      return;
    }

    this.feedbackService.submitFeedback(this.form.value).subscribe({
      next: () => {
        this.toastVisible = true;
        setTimeout(() => (this.toastVisible = false), 3000);

        this.form.reset();
        this.submitted = false;
      },
      error: (err) => console.error(err)
    });
  }


}
