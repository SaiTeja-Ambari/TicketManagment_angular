import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdmingroupComponent } from './admingroup.component';

describe('AdmingroupComponent', () => {
  let component: AdmingroupComponent;
  let fixture: ComponentFixture<AdmingroupComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdmingroupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdmingroupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
