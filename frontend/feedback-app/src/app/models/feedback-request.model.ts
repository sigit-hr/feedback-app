import { ContactType } from './contact-type.model';

export interface FeedbackRequest {
  name?: string;
  email?: string;
  contactType: ContactType;
  message: string;
}
