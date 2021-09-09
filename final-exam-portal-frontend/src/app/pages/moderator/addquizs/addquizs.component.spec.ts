import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddquizsComponent } from './addquizs.component';

describe('AddquizsComponent', () => {
  let component: AddquizsComponent;
  let fixture: ComponentFixture<AddquizsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddquizsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddquizsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
