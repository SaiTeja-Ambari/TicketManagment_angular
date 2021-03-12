import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminaddmemberComponent } from './adminaddmember.component';

describe('AdminaddmemberComponent', () => {
  let component: AdminaddmemberComponent;
  let fixture: ComponentFixture<AdminaddmemberComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminaddmemberComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminaddmemberComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
