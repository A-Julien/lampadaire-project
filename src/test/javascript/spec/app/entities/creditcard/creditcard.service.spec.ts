import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { CreditcardService } from 'app/entities/creditcard/creditcard.service';
import { ICreditcard, Creditcard } from 'app/shared/model/creditcard.model';

describe('Service Tests', () => {
  describe('Creditcard Service', () => {
    let injector: TestBed;
    let service: CreditcardService;
    let httpMock: HttpTestingController;
    let elemDefault: ICreditcard;
    let expectedResult: ICreditcard | ICreditcard[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(CreditcardService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Creditcard(0, 'AAAAAAA', currentDate, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            dateexpiration: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Creditcard', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            dateexpiration: currentDate.format(DATE_FORMAT),
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateexpiration: currentDate,
          },
          returnedFromService
        );

        service.create(new Creditcard()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Creditcard', () => {
        const returnedFromService = Object.assign(
          {
            numcarte: 'BBBBBB',
            dateexpiration: currentDate.format(DATE_FORMAT),
            code: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateexpiration: currentDate,
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Creditcard', () => {
        const returnedFromService = Object.assign(
          {
            numcarte: 'BBBBBB',
            dateexpiration: currentDate.format(DATE_FORMAT),
            code: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            dateexpiration: currentDate,
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Creditcard', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
