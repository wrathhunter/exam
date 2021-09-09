import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ApeearquizComponent } from './apeearquiz.component';

describe('ApeearquizComponent', () => {
  let component: ApeearquizComponent;
  let fixture: ComponentFixture<ApeearquizComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ApeearquizComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ApeearquizComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
