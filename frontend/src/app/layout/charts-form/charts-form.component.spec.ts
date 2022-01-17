import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ChartsFormComponent } from './charts-form.component';

describe('ChartsFormComponent', () => {
  let component: ChartsFormComponent;
  let fixture: ComponentFixture<ChartsFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ChartsFormComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ChartsFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
