import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateConteinerComponent } from './createConteiner.component';

describe('CreateComponent', () => {
  let component: CreateConteinerComponent;
  let fixture: ComponentFixture<CreateConteinerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateConteinerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateConteinerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
