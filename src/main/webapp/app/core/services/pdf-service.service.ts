import { Injectable } from '@angular/core';

import { Invoice } from '../../shared/model/Invoice.model';
import { Product } from '../../shared/model/ProductInvoice.model';
import { ProductOrder } from '../../shared/model/OrderProduct.model';

import * as pdfMake from 'pdfmake/build/pdfmake';
import * as pdfFonts from 'pdfmake/build/vfs_fonts';

@Injectable({
  providedIn: 'root',
})
export class PdfServiceService {
  private invoice = new Invoice();
  private billNum = '14MP4D3rUM';

  constructor() {
    (pdfMake as any).vfs = pdfFonts.pdfMake.vfs;
  }

  generatePDF(): void {
    pdfMake.createPdf(this.getDocumentDefinition()).open();
    this.invoice = new Invoice();
    //new Date();
  }

  addProduct(product: ProductOrder[]): void {
    product.forEach(p => {
      if (p.product.libstreetlamp !== undefined && p.product.pricestreetlamp !== undefined) {
        this.invoice.products.push(new Product(p.product.libstreetlamp, p.product.pricestreetlamp, p.quantity));
      }
    });
  }

  private getDocumentDefinition(): any {
    return {
      content: [
        {
          text: 'Lampaderum',
          fontSize: 26,
          alignment: 'center',
          color: '#df691a',
        },
        {
          text: 'INVOICE',
          fontSize: 20,
          bold: true,
          alignment: 'center',
          decoration: 'underline',
          color: '#868e96',
        },
        {
          text: 'Customer Details',
          style: 'sectionHeader',
        },
        {
          columns: [
            [
              {
                text: this.invoice.customerName,
                bold: true,
              },
              { text: this.invoice.address },
              { text: this.invoice.email },
              { text: this.invoice.contactNo },
            ],
            [
              {
                text: `Date: ${new Date().toLocaleString()}`,
                alignment: 'right',
              },
              {
                text: `Bill No : ${this.billNum}`,
                alignment: 'right',
              },
            ],
          ],
        },
        {
          text: 'Order Details',
          style: 'sectionHeader',
        },
        {
          table: {
            headerRows: 1,
            widths: ['*', 'auto', 'auto', 'auto'],
            body: [
              ['Product', 'Price', 'Quantity', 'Amount'],
              ...this.invoice.products.map(p => [p.name, p.price, p.qty, (p.price * p.qty).toFixed(2)]),
              [
                {
                  text: 'Total Amount',
                  colSpan: 3,
                },
                {},
                {},
                this.invoice.products.reduce((sum, p) => sum + p.qty * p.price, 0).toFixed(2),
              ],
            ],
          },
        },
        {
          text: 'Additional Details',
          style: 'sectionHeader',
        },
        {
          text: this.invoice.additionalDetails,
          margin: [0, 0, 0, 15],
        },
        {
          columns: [[{ qr: `${this.invoice.customerName}`, fit: '50' }], [{ text: 'Signature', alignment: 'right', italics: true }]],
        },
        {
          text: 'Terms and Conditions',
          style: 'sectionHeader',
        },
        {
          ul: [
            'Order can be return in max 10 days.',
            'Warrenty of the product will be subject to the manufacturer terms and conditions.',
            'This is system generated invoice.',
          ],
        },
      ],
      styles: {
        sectionHeader: {
          bold: true,
          decoration: 'underline',
          fontSize: 14,
          margin: [0, 15, 0, 15],
        },
      },
    };
  }
}
