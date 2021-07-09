import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SignupModeratorComponent } from './signup-moderator.component';

describe('SignupModeratorComponent', () => {
  let component: SignupModeratorComponent;
  let fixture: ComponentFixture<SignupModeratorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SignupModeratorComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SignupModeratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
