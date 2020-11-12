export interface IStreetlamp {
  id?: number;
  libstreetlamp?: string;
  modelestreetlamp?: string;
  dureeviestreetlamp?: number;
  uniteviestreetlamp?: string;
  materiaustreetlamp?: string;
  liblampe?: string;
  pwlampe?: number;
  formelampe?: string;
  modelelampe?: string;
  dureevielampe?: number;
  unitevielampe?: string;
  voltlampe?: number;
  templampe?: number;
  imagepathstreetlamp?: string;
  stockstreetlamp?: number;
}

export class Streetlamp implements IStreetlamp {
  constructor(
    public id?: number,
    public libstreetlamp?: string,
    public modelestreetlamp?: string,
    public dureeviestreetlamp?: number,
    public uniteviestreetlamp?: string,
    public materiaustreetlamp?: string,
    public liblampe?: string,
    public pwlampe?: number,
    public formelampe?: string,
    public modelelampe?: string,
    public dureevielampe?: number,
    public unitevielampe?: string,
    public voltlampe?: number,
    public templampe?: number,
    public imagepathstreetlamp?: string,
    public stockstreetlamp?: number
  ) {}
}
