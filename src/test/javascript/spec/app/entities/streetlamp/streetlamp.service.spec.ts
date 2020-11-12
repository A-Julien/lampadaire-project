import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { StreetlampService } from 'app/entities/streetlamp/streetlamp.service';
import { IStreetlamp, Streetlamp } from 'app/shared/model/streetlamp.model';

describe('Service Tests', () => {
  describe('Streetlamp Service', () => {
    let injector: TestBed;
    let service: StreetlampService;
    let httpMock: HttpTestingController;
    let elemDefault: IStreetlamp;
    let expectedResult: IStreetlamp | IStreetlamp[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(StreetlampService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Streetlamp(
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        'AAAAAAA',
        0,
        'AAAAAAA',
        0,
        0,
        'AAAAAAA',
        0
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Streetlamp', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Streetlamp()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Streetlamp', () => {
        const returnedFromService = Object.assign(
          {
            libstreetlamp: 'BBBBBB',
            modelestreetlamp: 'BBBBBB',
            dureeviestreetlamp: 1,
            uniteviestreetlamp: 'BBBBBB',
            materiaustreetlamp: 'BBBBBB',
            liblampe: 'BBBBBB',
            pwlampe: 1,
            formelampe: 'BBBBBB',
            modelelampe: 'BBBBBB',
            dureevielampe: 1,
            unitevielampe: 'BBBBBB',
            voltlampe: 1,
            templampe: 1,
            imagepathstreetlamp: 'BBBBBB',
            stockstreetlamp: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Streetlamp', () => {
        const returnedFromService = Object.assign(
          {
            libstreetlamp: 'BBBBBB',
            modelestreetlamp: 'BBBBBB',
            dureeviestreetlamp: 1,
            uniteviestreetlamp: 'BBBBBB',
            materiaustreetlamp: 'BBBBBB',
            liblampe: 'BBBBBB',
            pwlampe: 1,
            formelampe: 'BBBBBB',
            modelelampe: 'BBBBBB',
            dureevielampe: 1,
            unitevielampe: 'BBBBBB',
            voltlampe: 1,
            templampe: 1,
            imagepathstreetlamp: 'BBBBBB',
            stockstreetlamp: 1,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Streetlamp', () => {
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
