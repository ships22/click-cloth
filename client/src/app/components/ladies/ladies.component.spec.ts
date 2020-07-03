import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LadiesComponent } from './ladies.component';

describe('LadiesComponent', () => {
  let component: LadiesComponent;
  let fixture: ComponentFixture<LadiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LadiesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LadiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
