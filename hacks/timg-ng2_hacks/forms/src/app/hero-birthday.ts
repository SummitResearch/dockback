import {bootstrap, Component, View} from 'angular2/angular2';

@Component({
    selector: 'hero-birthday',
    template: `
        <p>The hero's birthday is {{ (birthday | date:format) | uppercase }}</p>
        <button (click)="toggleFormat()" class="btn">Toggle Format</button>
    `
})
export class HeroBirthday {

    birthday = new Date(1968, 8, 20);

    get format() {
        return this.toggle ? 'shortDate' : 'fullDate'
    }

    toggle = true;

    toggleFormat() {
        this.toggle = !this.toggle
    }
}