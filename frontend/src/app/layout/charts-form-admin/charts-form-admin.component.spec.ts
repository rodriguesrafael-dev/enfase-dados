import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartsFormAdminComponent } from './charts-form-admin.component';

describe('ChartsFormAdminComponent', () => {
  let component: ChartsFormAdminComponent;
  let fixture: ComponentFixture<ChartsFormAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChartsFormAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartsFormAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
