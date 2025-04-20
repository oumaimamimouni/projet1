import { TestBed } from '@angular/core/testing';

import { ApiHistoryService } from './api-history.service';

describe('ApiHistoryService', () => {
  let service: ApiHistoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ApiHistoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
