import {Component} from 'angular2/angular2';

@Component({
    selector: 'key-up3',
    template: `
        <h4>Type away! Press [enter] when you're done.</h4>
        <div><input #box (keyup.enter)="values=box.value"></div>
        <div>{{values}}</div>
    `
})
export class KeyUpComponentV3 {
    values = '';
}