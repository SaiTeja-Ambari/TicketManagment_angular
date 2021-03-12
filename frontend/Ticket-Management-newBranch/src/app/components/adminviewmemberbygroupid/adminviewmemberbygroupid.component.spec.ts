import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminviewmemberbygroupidComponent } from './adminviewmemberbygroupid.component';

describe('AdminviewmemberbygroupidComponent', () => {
  let component: AdminviewmemberbygroupidComponent;
  let fixture: ComponentFixture<AdminviewmemberbygroupidComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminviewmemberbygroupidComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminviewmemberbygroupidComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
