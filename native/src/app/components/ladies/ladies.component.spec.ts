import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { IonicModule } from '@ionic/angular';

import { LadiesComponent } from './ladies.component';

describe('LadiesComponent', () => {
  let component: LadiesComponent;
  let fixture: ComponentFixture<LadiesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LadiesComponent ],
      imports: [IonicModule.forRoot()]
    }).compileComponents();

    fixture = TestBed.createComponent(LadiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  }));

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
