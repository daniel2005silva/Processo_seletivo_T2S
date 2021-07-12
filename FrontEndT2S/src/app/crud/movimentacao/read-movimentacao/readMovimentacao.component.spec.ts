import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadMovimentacaoComponent } from './readMovimentacao.component';

describe('ReadMovimentacaoComponent', () => {
  let component: ReadMovimentacaoComponent;
  let fixture: ComponentFixture<ReadMovimentacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReadMovimentacaoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReadMovimentacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
