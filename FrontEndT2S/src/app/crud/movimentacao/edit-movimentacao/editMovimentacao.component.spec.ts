import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditMovimentacaoComponent } from './editMovimentacao.component';

describe('EditMovimentacaoComponent', () => {
  let component: EditMovimentacaoComponent;
  let fixture: ComponentFixture<EditMovimentacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditMovimentacaoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditMovimentacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
