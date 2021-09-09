import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewquizsComponent } from './viewquizs.component';

describe('ViewquizsComponent', () => {
  let component: ViewquizsComponent;
  let fixture: ComponentFixture<ViewquizsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewquizsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ViewquizsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
