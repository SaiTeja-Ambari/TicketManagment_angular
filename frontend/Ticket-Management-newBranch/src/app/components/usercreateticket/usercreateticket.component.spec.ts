import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UsercreateticketComponent } from './usercreateticket.component';

describe('UsercreateticketComponent', () => {
  let component: UsercreateticketComponent;
  let fixture: ComponentFixture<UsercreateticketComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UsercreateticketComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(UsercreateticketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
