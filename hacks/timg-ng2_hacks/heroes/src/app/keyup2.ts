import {Component} from 'angular2/angular2';

@Component({
	selector: 'key-up-ng',
	template: `
		<h4>Give me some more keys!</h4>
		<div><input #box (keyup)="onKey(box.value)"></div>
		<div>{{values}}</div>
	`
})
export class KeyUpComponentNG {
	values = '';
	onKey( value ) {
        this.values += value + ' | ';
    }
}