import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ReadConteinerComponent } from './readConteiner.component';

describe('ReadConteinerComponent', () => {
  let component: ReadConteinerComponent;
  let fixture: ComponentFixture<ReadConteinerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ReadConteinerComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ReadConteinerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
