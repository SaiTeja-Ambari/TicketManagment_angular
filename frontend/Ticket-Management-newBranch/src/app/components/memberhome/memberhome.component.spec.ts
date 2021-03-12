import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MemberhomeComponent } from './memberhome.component';

describe('MemberhomeComponent', () => {
  let component: MemberhomeComponent;
  let fixture: ComponentFixture<MemberhomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MemberhomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MemberhomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
