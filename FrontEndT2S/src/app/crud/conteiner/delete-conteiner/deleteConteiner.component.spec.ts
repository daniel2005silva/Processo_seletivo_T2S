import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteConteinerComponent } from './deleteConteiner.component';

describe('DeleteConteinerComponent', () => {
  let component: DeleteConteinerComponent;
  let fixture: ComponentFixture<DeleteConteinerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteConteinerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteConteinerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
