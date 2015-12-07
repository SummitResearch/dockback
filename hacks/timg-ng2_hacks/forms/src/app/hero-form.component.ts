import {Component, CORE_DIRECTIVES, FORM_DIRECTIVES} from 'angular2/angular2';

import { Hero } from "./hero";
import {HeroBirthday} from "./hero-birthday";
import {PowerBooster} from "./power-booster";
import {PowerBoostCalculator} from "./power-boost-calculator";
import {MyHeroAsyncMessageComponent} from "./my-hero";
import {HeroList} from "./hero-list";

@Component({
    selector: 'hero-form',
    templateUrl: 'app/hero-form.component.html',
    directives: [CORE_DIRECTIVES, FORM_DIRECTIVES, HeroBirthday, PowerBooster, PowerBoostCalculator, MyHeroAsyncMessageComponent, HeroList]
})
export class HeroFormComponent {

    powers = ['Really Smart', 'Super Flexible', 'Super Hot', 'Weather Changer'];

    model = new Hero(18, 'Dr IQ', this.powers[0], 'Chuck Overstreet');

    submitted = false;

    onSubmit() {
        this.submitted = true;
    }

    get diagnostic() {
        return JSON.stringify(this.model);
    }

}