import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-api-details',
  template: `
    <h2>API Details</h2>
    <p><strong>ID:</strong> {{ data.ID_ACM_TRANSVERS_HISTORY }}</p>
    <p><strong>Object:</strong> {{ data.OBJECT_VALUE }}</p>
    <p><strong>Method:</strong> {{ data.METHODE }}</p>
    <p><strong>URI:</strong> {{ data.URI }}</p>
    <p><strong>Status:</strong> {{ data.REPONSE_STATUS }}</p>
    <p><strong>Insertion Date:</strong> {{ data.DATE_INSERTION }}</p>
    <p><strong>Last Update:</strong> {{ data.DATE_LAST_UPDATE }}</p>
  `
})
export class ApiDetailsComponent {
  constructor(@Inject(MAT_DIALOG_DATA) public data: any) {}
}
