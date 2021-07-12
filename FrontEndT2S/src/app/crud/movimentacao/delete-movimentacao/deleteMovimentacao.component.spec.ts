import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeleteMovimentacaoComponent } from './deleteMovimentacao.component';

describe('DeleteComponent', () => {
  let component: DeleteMovimentacaoComponent;
  let fixture: ComponentFixture<DeleteMovimentacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeleteMovimentacaoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DeleteMovimentacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
