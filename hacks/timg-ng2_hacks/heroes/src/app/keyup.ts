import {Component} from 'angular2/angular2';

@Component({
	selector: 'key-up',
	template: `
	<h4>Give me some keys!</h4>
	<div><input (keyup)="onKey($event)"></div>
	<div>{{values}}</div>
	`
})
export class KeyUpComponent {
	values = '';
	
	onKey(event) {
		this.values += event.target.value + ' | ';
	}
}