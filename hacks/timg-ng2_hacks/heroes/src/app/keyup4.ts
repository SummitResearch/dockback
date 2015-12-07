import {Component} from 'angular2/angular2';

@Component({
    selector: 'key-up4',
    template:`
    <h4>Press [enter] or mouse away...</h4>
    <div>
        <input #box
         (keyup.enter)="values=box.value"
         (blur)="values=box.value">
    </div>
    <div>{{values}}</div>
    `
})
export class KeyUpComponentV4 {
    values = '';
}