import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateMovimentacaoComponent } from './createMovimentacao.component';

describe('CreateMovimentacaoComponent', () => {
  let component: CreateMovimentacaoComponent;
  let fixture: ComponentFixture<CreateMovimentacaoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateMovimentacaoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateMovimentacaoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
