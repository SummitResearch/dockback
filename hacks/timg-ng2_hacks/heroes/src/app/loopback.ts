import {Component} from 'angular2/angular2';

@Component({
	selector: 'loop-back',
	template: '<input #box (keyup)="0"> <p>{{box.value}}</p>'
})
export class LoopbackComponent {
	
}