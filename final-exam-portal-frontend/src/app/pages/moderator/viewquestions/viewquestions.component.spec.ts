import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewquestionsComponent } from './viewquestions.component';

describe('ViewquestionsComponent', () => {
  let component: ViewquestionsComponent;
  let fixture: ComponentFixture<ViewquestionsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewquestionsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewquestionsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
