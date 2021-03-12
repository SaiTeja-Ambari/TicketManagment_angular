import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminviewgroupComponent } from './adminviewgroup.component';

describe('AdminviewgroupComponent', () => {
  let component: AdminviewgroupComponent;
  let fixture: ComponentFixture<AdminviewgroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminviewgroupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminviewgroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
