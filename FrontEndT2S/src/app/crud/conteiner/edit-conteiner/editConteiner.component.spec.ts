import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditConteinerComponent } from './editConteiner.component';

describe('EditConteinerComponent', () => {
  let component: EditConteinerComponent;
  let fixture: ComponentFixture<EditConteinerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditConteinerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditConteinerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
