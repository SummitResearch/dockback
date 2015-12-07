import {bootstrap, Component, FORM_DIRECTIVES, CORE_DIRECTIVES} from 'angular2/angular2';
import {Hero} from './hero';
import {KeyUpComponent} from './keyup';
import {LoopbackComponent} from './loopback';
import {KeyUpComponentNG} from "./keyup2";
import {KeyUpComponentV3} from "./keyup3";
import {KeyUpComponentV4} from "./keyup4";

@Component({
	selector: 'my-app',
	template: `
		<h1>{{title}}</h1>
		<div *ng-if="selectedHero">
			<h2>My favorite hero is:  {{selectedHero.name}}
		</div>
		<p *ng-if="heroes.length > 3">There are a bunch of heroes!</p>
		<h2>My Heroes</h2>
		<ul class="heroes">
			<li *ng-for="#hero of heroes"
			[ng-class]="getSelectedClass(hero)" 
			(click)="onSelect(hero)">
			<span class="badge">{{hero.id}}</span> {{hero.name}}
			</li>
		</ul>
		<div *ng-if="selectedHero">
			<h2>{{selectedHero.name}} details!</h2>
			<div><label>id: </label>{{selectedHero.id}}</div>
			<div>
				<label>name: </label>
				<div><input [(ng-model)]="selectedHero.name" placeholder="name"></div>
			</div>
		</div>
		
		<input #new-hero
		(keyup.enter)="addHero(newHero)"
		(blur)="addHero(newHero)">
		<button (click)="addHero(newHero)">Add</button>

		<key-up>testing...</key-up>
		
		<loop-back>loopback...</loop-back>

		<key-up-ng>key testing 2...</key-up-ng>

		<key-up3>key testing 3...</key-up3>

        <key-up4>key testing 4...</key-up4>
		`,
	directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, KeyUpComponent, LoopbackComponent, KeyUpComponentNG, KeyUpComponentV3, KeyUpComponentV4],
	styles:[`
		.heroes {list-style-type: none; margin-left: 1em; padding: 0; width: 10em;}
		.heroes li { cursor: pointer; position: relative; left: 0; transition: all 0.2s ease; }
		.heroes li:hover {color: #369; background-color: #EEE; left: .2em;}
		.heroes .badge {
			font-size: small;
			color: white;
			padding: 0.1em 0.7em;
			background-color: #369;
			line-height: 1em;
			position: relative;
			left: -1px;
			top: -1px;
		}
		.selected { background-color: #EEE; color: #369; }
  `]
})
class AppComponent { 
	
	public selectedHero: Hero;
	
	public heroes = HEROES;
	public title = 'Tour of Heroes';
	
	constructor() {
		this.title = 'Awesome List of Heroes';	
	}
	
	public hero: Hero = {
		id: 1,
		name: 'Windstorm'	
	}
	
	onSelect(hero: Hero) {
		this.selectedHero = hero;
	}
	
	getSelectedClass(hero: Hero) {
		return { 'selected': hero === this.selectedHero };
	}
	
	onClickMe() {
		console.log("clicked!")
	}

    addHero( newHero ) {
        if( newHero.value ) {
            this.heroes.push( new Hero(this.heroes.length+1, newHero.value ));
            newHero.value = null;
        }
    }
}

bootstrap( AppComponent );

var HEROES: Hero[] = [
  new Hero(11, "Mr. Nicer"),
  { "id": 12, "name": "Narco" },
  { "id": 13, "name": "Bombasto" },
  { "id": 14, "name": "Celeritas" },
  { "id": 15, "name": "Magneta" },
  { "id": 16, "name": "RubberMan" },
  { "id": 17, "name": "Dynama" },
  { "id": 18, "name": "Dr IQ" },
  { "id": 19, "name": "Magma" },
  { "id": 20, "name": "Tornado" }
];