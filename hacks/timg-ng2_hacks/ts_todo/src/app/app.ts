import {bootstrap, Component} from 'angular2/angular2';

@Component({
	selector: 'todo-app',
	template: '<h1>My TODO App</h1>'
})
class TodoAppComponent {}

bootstrap(TodoAppComponent)