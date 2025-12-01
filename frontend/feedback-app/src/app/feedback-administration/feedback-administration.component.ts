import { Component, OnInit } from '@angular/core';
import { FeedbackService } from '../services/feedback.service';

@Component({
  selector: 'app-feedback-administration',
  templateUrl: './feedback-administration.component.html',
  styleUrls: ['./feedback-administration.component.css']
})
export class FeedbackAdministrationComponent implements OnInit {

  feedback: any[] = [];
  contactType: string = 'ALL';
  sort: string = 'submittedAt,desc';
  page = 0;
  size = 10;

  totalElements = 0;
  totalPages = 0;

  constructor(private service: FeedbackService) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.service
      .getFeedback(this.page, this.size, this.contactType, this.sort)
      .subscribe((res: any) => {
        this.feedback = res.content;
        this.totalElements = res.totalElements;
        this.totalPages = res.totalPages;
      });
  }

  onFilterChange(): void {
    this.page = 0;
    this.load();
  }

  onSortChange(): void {
    this.page = 0;
    this.load();
  }

  changePage(delta: number): void {
    const newPage = this.page + delta;
    if (newPage >= 0 && newPage < this.totalPages) {
      this.page = newPage;
      this.load();
    }
  }

}
