import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { FeedbackRequest } from '../models/feedback-request.model';

@Injectable({ providedIn: 'root' })
export class FeedbackService {

  private api = 'http://localhost:8080/api/v1/feedback';

  constructor(private http: HttpClient) {}

  submitFeedback(payload: FeedbackRequest) {
    return this.http.post(this.api, payload);
  }

  getFeedback(page: number, size: number, contactType?: string, sort?: string) {
    const params: any = { page, size };

    if (contactType && contactType !== 'ALL') {
      params.contactType = contactType;
    }

    if (sort) {
      params.sort = sort;
    }

    return this.http.get<any>(this.api, { params });
  }


}
