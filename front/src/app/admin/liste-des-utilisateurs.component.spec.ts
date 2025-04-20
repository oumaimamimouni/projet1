import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeDesUtilisateursComponent } from './liste-des-utilisateurs.component';

describe('ListeDesUtilisateursComponent', () => {
  let component: ListeDesUtilisateursComponent;
  let fixture: ComponentFixture<ListeDesUtilisateursComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeDesUtilisateursComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListeDesUtilisateursComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});