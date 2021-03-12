import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminticketComponent } from './adminticket.component';

describe('AdminticketComponent', () => {
  let component: AdminticketComponent;
  let fixture: ComponentFixture<AdminticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminticketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
